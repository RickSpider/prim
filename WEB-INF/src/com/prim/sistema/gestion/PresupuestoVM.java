package com.prim.sistema.gestion;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Init;


import com.prim.modelo.Presupuesto;
import com.prim.util.ParamsLocal;
import com.prim.util.TemplateViewModelLocal;


public class PresupuestoVM extends TemplateViewModelLocal{
	
	private boolean opCrearPresupuesto;
	private boolean opEditarPresupuesto;
	private boolean opBorrarPresupuesto;
	
	private boolean editar = false;
	
	private List<Object[]> lPresupuestos;
	private List<Object[]> lPresupuestosOri;
	private Presupuesto prespuestoSelected;

	@Init(superclass = true)
	public void initPresupuestoVM() {

		
	
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
	
	
	


	public List<Object[]> getlPresupuestos() {
		return lPresupuestos;
	}

	public void setlPresupuestos(List<Object[]> lPresupuestos) {
		this.lPresupuestos = lPresupuestos;
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
		return prespuestoSelected;
	}

	public void setPresupuestoSelected(Presupuesto prespuestoSelected) {
		this.prespuestoSelected = prespuestoSelected;
	}
	
	

}
