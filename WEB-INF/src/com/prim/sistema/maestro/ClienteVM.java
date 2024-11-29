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
import com.prim.modelo.Cliente;
import com.prim.util.ParamsLocal;
import com.prim.util.TemplateViewModelLocal;


public class ClienteVM extends TemplateViewModelLocal{
	
	private boolean opCrearCliente;
	private boolean opEditarCliente;
	private boolean opBorrarCliente;
	
	private boolean editar = false;
	
	private List<Object[]> lClientes;
	private List<Object[]> lClientesOri;
	private Cliente clienteSelected;

	@Init(superclass = true)
	public void initClienteVM() {

		
		inicializarFiltros();
		cargarClientes();

	}

	@AfterCompose(superclass = true)
	public void afterComposeClienteVM() {

	}
	

	@Override
	protected void inicializarOperaciones() {
	
		this.opCrearCliente = this.operacionHabilitada(ParamsLocal.OP_CREAR_CLIENTE);
		this.opEditarCliente = this.operacionHabilitada(ParamsLocal.OP_EDITAR_CLIENTE);
		this.opBorrarCliente = this.operacionHabilitada(ParamsLocal.OP_BORRAR_CLIENTE);
		
	}
	
	
	private String filtroColumns[];

	private void inicializarFiltros() {

		this.filtroColumns = new String[5];

		for (int i = 0; i < this.filtroColumns.length; i++) {

			this.filtroColumns[i] = "";

		}

	}

	@Command
	@NotifyChange("lClientes")
	public void filtrarCliente() {

		this.lClientes = this.filtrarListaObject(this.filtroColumns, this.lClientesOri);

	}
	
	
	private void cargarClientes() {
		
		String sql = this.um.getSql("cliente/listaClientes.sql");
		

		System.out.println("=======================");
		System.out.println(sql);
		System.out.println("=======================");
		
		this.lClientes = this.reg.sqlNativo(sql);
		this.lClientesOri = this.lClientes;
		
		
	}
	
	private Window modal;
	
	@Command
	public void modalClienteAgregar() {

		if (!this.opCrearCliente)
			return;

		this.editar = false;
		this.modalCliente(-1);

	}

	@Command
	public void modalCliente(@BindingParam("clienteid") long clienteid) {
		
		this.inicializarFinders();

		if (clienteid != -1) {

			if (!this.opEditarCliente)
				return;

			this.editar = true;

			this.clienteSelected = this.reg.getObjectById(Cliente.class.getName(), clienteid);

		} else {

			this.clienteSelected = new Cliente();
			
		}

		modal = (Window) Executions.createComponents("/sistema/zul/maestro/clienteModal.zul", this.mainComponent, null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}
	
	@Command
	@NotifyChange("lClientes")
	public void guardar() {

		this.clienteSelected = this.save(this.clienteSelected);

		this.cargarClientes();

		this.modal.detach();

		if (editar) {

			Notification.show("Cliente Actualizado.");

			this.editar = false;

		} else {

			Notification.show("Los datos deL Cliente fueron agragados.");
		}

	}
	
	@Command
	public void borrarConfirmacion(@BindingParam("dato") long id) {

		if (!this.opBorrarCliente)
			return;

		

		EventListener event = new EventListener () {

			@Override
			public void onEvent(Event evt) throws Exception {
				
				if (evt.getName().equals(Messagebox.ON_YES)) {
					
					Cliente p = reg.getObjectById(Cliente.class.getName(), id);
					borrar(p);
					
				}
				
			}

		};

		this.mensajeEliminar("El Cliente sera borrado permanentemente. \n Continuar?", event);

	}
	
	
	private void borrar(Cliente p) {

		System.out.println("=====Inicio Borrado=========");
		
		this.reg.deleteObject(p);
		
		this.cargarClientes();
		
		BindUtils.postNotifyChange(null, null, this, "lClientes");
		BindUtils.postNotifyChange(null, null, this, "lClientesOri");
		
		System.out.println("=====Fin Borrado=========");
	}
	
	private FinderModel documentoTipoFinder;

	@NotifyChange("*")
	public void inicializarFinders() {

		String sqlDocumentoTipo = this.um.getCoreSql("buscarTiposPorSiglaTipotipo.sql").replace("?1", ParamsLocal.SIGLA_TIPOTIPO_DOCUMENTO );
		documentoTipoFinder = new FinderModel("Documento", sqlDocumentoTipo);

	}

	public void generarFinders(@BindingParam("finder") String finder) {

		if (finder.compareTo(this.documentoTipoFinder.getNameFinder()) == 0) {

			this.documentoTipoFinder.generarListFinder();
			BindUtils.postNotifyChange(null, null, this.documentoTipoFinder, "listFinder");

			return;
		}

	}

	@Command
	public void finderFilter(@BindingParam("filter") String filter, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.documentoTipoFinder.getNameFinder()) == 0) {

			this.documentoTipoFinder.setListFinder(this.filtrarListaObject(filter, this.documentoTipoFinder.getListFinderOri()));
			BindUtils.postNotifyChange(null, null, this.documentoTipoFinder, "listFinder");

			return;
		}

	}

	@Command
	@NotifyChange("*")
	public void onSelectetItemFinder(@BindingParam("id") Long id, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.documentoTipoFinder.getNameFinder()) == 0) {

			this.clienteSelected.setDocumentoTipo(this.reg.getObjectById(Tipo.class.getName(), id));
			
			return;
		}

	}
	
	public List<Object[]> getlClientes() {
		return lClientes;
	}

	public void setlClientes(List<Object[]> lClientes) {
		this.lClientes = lClientes;
	}

	public boolean isOpCrearCliente() {
		return opCrearCliente;
	}

	public void setOpCrearCliente(boolean opCrearCliente) {
		this.opCrearCliente = opCrearCliente;
	}

	public boolean isOpEditarCliente() {
		return opEditarCliente;
	}

	public void setOpEditarCliente(boolean opEditarCliente) {
		this.opEditarCliente = opEditarCliente;
	}

	public boolean isOpBorrarCliente() {
		return opBorrarCliente;
	}

	public void setOpBorrarCliente(boolean opBorrarCliente) {
		this.opBorrarCliente = opBorrarCliente;
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

	public Cliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(Cliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public FinderModel getDocumentoTipoFinder() {
		return documentoTipoFinder;
	}

	public void setDocumentoTipoFinder(FinderModel documentoTipoFinder) {
		this.documentoTipoFinder = documentoTipoFinder;
	}
	
	

}
