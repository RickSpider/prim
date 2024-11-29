package com.prim.sistema.maestro;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.doxacore.components.finder.FinderModel;
import com.doxacore.modelo.Tipo;
import com.prim.modelo.Insumo;
import com.prim.util.ParamsLocal;
import com.prim.util.TemplateViewModelLocal;


public class InsumoVM extends TemplateViewModelLocal{
	
	private boolean opCrearInsumo;
	private boolean opEditarInsumo;
	private boolean opBorrarInsumo;
	
	private boolean editar = false;
	
	private List<Object[]> lInsumos;
	private List<Object[]> lInsumosOri;
	private Insumo insumoSelected;

	@Init(superclass = true)
	public void initInsumoVM() {

		
		inicializarFiltros();
		cargarInsumos();

	}

	@AfterCompose(superclass = true)
	public void afterComposeInsumoVM() {

	}
	

	@Override
	protected void inicializarOperaciones() {
	
		this.opCrearInsumo = this.operacionHabilitada(ParamsLocal.OP_CREAR_INSUMO);
		this.opEditarInsumo = this.operacionHabilitada(ParamsLocal.OP_EDITAR_INSUMO);
		this.opBorrarInsumo = this.operacionHabilitada(ParamsLocal.OP_BORRAR_INSUMO);
		
	}
	
	
	private String filtroColumns[];

	private void inicializarFiltros() {

		this.filtroColumns = new String[2];

		for (int i = 0; i < this.filtroColumns.length; i++) {

			this.filtroColumns[i] = "";

		}

	}

	@Command
	@NotifyChange("lInsumos")
	public void filtrarInsumo() {

		this.lInsumos = this.filtrarListaObject(this.filtroColumns, this.lInsumosOri);

	}
	
	
	private void cargarInsumos() {
		
		String sql = this.um.getSql("Insumo/listaInsumos.sql");
		

		System.out.println("=======================");
		System.out.println(sql);
		System.out.println("=======================");
		
		this.lInsumos = this.reg.sqlNativo(sql);
		this.lInsumosOri = this.lInsumos;
		
		
	}
	
	private Window modal;
	
	@Command
	public void modalInsumoAgregar() {

		if (!this.opCrearInsumo)
			return;

		this.editar = false;
		this.modalInsumo(-1);

	}

	@Command
	public void modalInsumo(@BindingParam("Insumoid") long Insumoid) {
		
		this.inicializarFinders();

		if (Insumoid != -1) {

			if (!this.opEditarInsumo)
				return;

			this.editar = true;

			this.insumoSelected = this.reg.getObjectById(Insumo.class.getName(), Insumoid);

		} else {

			this.insumoSelected = new Insumo();
			
		}

		modal = (Window) Executions.createComponents("/sistema/zul/maestro/insumoModal.zul", this.mainComponent, null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}
	
	@Command
	@NotifyChange("lInsumos")
	public void guardar() {

		this.insumoSelected = this.save(this.insumoSelected);

		this.cargarInsumos();

		this.modal.detach();

		if (editar) {

			Notification.show("Insumo Actualizado.");

			this.editar = false;

		} else {

			Notification.show("Los datos deL Insumo fueron agragados.");
		}

	}
	
	@Command
	public void borrarConfirmacion(@BindingParam("dato") long id) {

		if (!this.opBorrarInsumo)
			return;

		

		EventListener event = new EventListener () {

			@Override
			public void onEvent(Event evt) throws Exception {
				
				if (evt.getName().equals(Messagebox.ON_YES)) {
					
					Insumo p = reg.getObjectById(Insumo.class.getName(), id);
					borrar(p);
					
				}
				
			}

		};

		this.mensajeEliminar("El Insumo sera borrado permanentemente. \n Continuar?", event);

	}
	
	
	private void borrar(Insumo p) {

		System.out.println("=====Inicio Borrado=========");
		
		this.reg.deleteObject(p);
		
		this.cargarInsumos();
		
		BindUtils.postNotifyChange(null, null, this, "lInsumos");
		BindUtils.postNotifyChange(null, null, this, "lInsumosOri");
		
		System.out.println("=====Fin Borrado=========");
	}
	
	private FinderModel insumoTipoFinder;

	@NotifyChange("*")
	public void inicializarFinders() {

		String sqlInsumoTipo = this.um.getCoreSql("buscarTiposPorSiglaTipotipo.sql").replace("?1", ParamsLocal.SIGLA_TIPOTIPO_INSUMO );
		insumoTipoFinder = new FinderModel("Insumo", sqlInsumoTipo);

	}

	public void generarFinders(@BindingParam("finder") String finder) {

		if (finder.compareTo(this.insumoTipoFinder.getNameFinder()) == 0) {

			this.insumoTipoFinder.generarListFinder();
			BindUtils.postNotifyChange(null, null, this.insumoTipoFinder, "listFinder");

			return;
		}

	}

	@Command
	public void finderFilter(@BindingParam("filter") String filter, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.insumoTipoFinder.getNameFinder()) == 0) {

			this.insumoTipoFinder.setListFinder(this.filtrarListaObject(filter, this.insumoTipoFinder.getListFinderOri()));
			BindUtils.postNotifyChange(null, null, this.insumoTipoFinder, "listFinder");

			return;
		}

	}

	@Command
	@NotifyChange("*")
	public void onSelectetItemFinder(@BindingParam("id") Long id, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.insumoTipoFinder.getNameFinder()) == 0) {

			this.insumoSelected.setInsumoTipo(this.reg.getObjectById(Tipo.class.getName(), id));
			
			return;
		}

	}

	public List<Object[]> getlInsumos() {
		return lInsumos;
	}

	public void setlInsumos(List<Object[]> lInsumos) {
		this.lInsumos = lInsumos;
	}

	public boolean isOpCrearInsumo() {
		return opCrearInsumo;
	}

	public void setOpCrearInsumo(boolean opCrearInsumo) {
		this.opCrearInsumo = opCrearInsumo;
	}

	public boolean isOpEditarInsumo() {
		return opEditarInsumo;
	}

	public void setOpEditarInsumo(boolean opEditarInsumo) {
		this.opEditarInsumo = opEditarInsumo;
	}

	public boolean isOpBorrarInsumo() {
		return opBorrarInsumo;
	}

	public void setOpBorrarInsumo(boolean opBorrarInsumo) {
		this.opBorrarInsumo = opBorrarInsumo;
	}

	public String[] getFiltroColumns() {
		return filtroColumns;
	}

	public void setFiltroColumns(String[] filtroColumns) {
		this.filtroColumns = filtroColumns;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public Insumo getInsumoSelected() {
		return insumoSelected;
	}

	public void setInsumoSelected(Insumo insumoSelected) {
		this.insumoSelected = insumoSelected;
	}

	public FinderModel getInsumoTipoFinder() {
		return insumoTipoFinder;
	}

	public void setInsumoTipoFinder(FinderModel insumoTipoFinder) {
		this.insumoTipoFinder = insumoTipoFinder;
	}



}
