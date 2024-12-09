package com.prim.sistema.gestion;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.doxacore.components.finder.FinderModel;
import com.prim.modelo.Cliente;
import com.prim.modelo.Funcionario;
import com.prim.modelo.Presupuesto;
import com.prim.util.ParamsLocal;
import com.prim.util.TemplateViewModelLocal;


public class PresupuestoVM extends TemplateViewModelLocal{
	
	private boolean opCrearPresupuesto;
	private boolean opEditarPresupuesto;
	private boolean opBorrarPresupuesto;
	
	private boolean editar = false;
	

	private Presupuesto presupuestoSelected;

	@Init(superclass = true)
	public void initPresupuestoVM() {

		this.presupuestoSelected = new Presupuesto();
		this.inicializarFinders();
		
	
	}

	@AfterCompose(superclass = true)
	public void afterComposePresupuestoVM() {

	}
	

	@Override
	protected void inicializarOperaciones() {
	
		this.opCrearPresupuesto = this.operacionHabilitada(ParamsLocal.OP_CREAR_PRESUPUESTO);
		this.opEditarPresupuesto = this.operacionHabilitada(ParamsLocal.OP_EDITAR_PRESUPUESTO);
		this.opBorrarPresupuesto = this.operacionHabilitada(ParamsLocal.OP_BORRAR_PRESUPUESTO);
		
	}
	
	
	private FinderModel clienteFinder;
	private FinderModel funcionarioFinder;

	@NotifyChange("*")
	public void inicializarFinders() {

		String sqlCliente = this.um.getSql("cliente/buscarClientes.sql");
		clienteFinder = new FinderModel("Cliente", sqlCliente);
		
		String sqlFuncionario = this.um.getSql("funcionario/buscarFuncionarios.sql");
		funcionarioFinder = new FinderModel("Funcionario", sqlFuncionario);

	}

	public void generarFinders(@BindingParam("finder") String finder) {

		if (finder.compareTo(this.clienteFinder.getNameFinder()) == 0) {

			this.clienteFinder.generarListFinder();
			BindUtils.postNotifyChange(null, null, this.clienteFinder, "listFinder");

			return;
		}
		

		if (finder.compareTo(this.funcionarioFinder.getNameFinder()) == 0) {

			this.funcionarioFinder.generarListFinder();
			BindUtils.postNotifyChange(null, null, this.funcionarioFinder, "listFinder");

			return;
		}

	}

	@Command
	public void finderFilter(@BindingParam("filter") String filter, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.clienteFinder.getNameFinder()) == 0) {

			this.clienteFinder.setListFinder(this.filtrarListaObject(filter, this.clienteFinder.getListFinderOri()));
			BindUtils.postNotifyChange(null, null, this.clienteFinder, "listFinder");

			return;
		}
		
		if (finder.compareTo(this.funcionarioFinder.getNameFinder()) == 0) {

			this.funcionarioFinder.setListFinder(this.filtrarListaObject(filter, this.funcionarioFinder.getListFinderOri()));
			BindUtils.postNotifyChange(null, null, this.funcionarioFinder, "listFinder");

			return;
		}

	}

	@Command
	@NotifyChange("*")
	public void onSelectetItemFinder(@BindingParam("id") Long id, @BindingParam("finder") String finder) {

		if (finder.compareTo(this.clienteFinder.getNameFinder()) == 0) {

			this.presupuestoSelected.setCliente(this.reg.getObjectById(Cliente.class.getName(), id));
			
			return;
		}
		
		if (finder.compareTo(this.funcionarioFinder.getNameFinder()) == 0) {

			this.presupuestoSelected.setFuncionario(this.reg.getObjectById(Funcionario.class.getName(), id));
			
			return;
		}

	}


	public boolean isOpCrearPresupuesto() {
		return opCrearPresupuesto;
	}

	public void setOpCrearPresupuesto(boolean opCrearPresupuesto) {
		this.opCrearPresupuesto = opCrearPresupuesto;
	}

	public boolean isOpEditarPresupuesto() {
		return opEditarPresupuesto;
	}

	public void setOpEditarPresupuesto(boolean opEditarPresupuesto) {
		this.opEditarPresupuesto = opEditarPresupuesto;
	}

	public boolean isOpBorrarPresupuesto() {
		return opBorrarPresupuesto;
	}

	public void setOpBorrarPresupuesto(boolean opBorrarPresupuesto) {
		this.opBorrarPresupuesto = opBorrarPresupuesto;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public Presupuesto getPresupuestoSelected() {
		return presupuestoSelected;
	}

	public void setPresupuestoSelected(Presupuesto presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}

	public FinderModel getClienteFinder() {
		return clienteFinder;
	}

	public void setClienteFinder(FinderModel clienteFinder) {
		this.clienteFinder = clienteFinder;
	}

	public FinderModel getFuncionarioFinder() {
		return funcionarioFinder;
	}

	public void setFuncionarioFinder(FinderModel funcionarioFinder) {
		this.funcionarioFinder = funcionarioFinder;
	}

	
	

}
