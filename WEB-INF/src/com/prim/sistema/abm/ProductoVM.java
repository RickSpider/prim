package com.prim.sistema.abm;

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

import com.prim.modelo.Producto;
import com.prim.util.ParamsLocal;
import com.prim.util.TemplateViewModelLocal;


public class ProductoVM extends TemplateViewModelLocal{
	
	private boolean opCrearProducto;
	private boolean opEditarProducto;
	private boolean opBorrarProducto;
	
	private boolean editar = false;
	
	private List<Object[]> lProductos;
	private List<Object[]> lProductosOri;
	private Producto productoSelected;

	@Init(superclass = true)
	public void initProductoVM() {

		
		inicializarFiltros();
		cargarProductos();

	}

	@AfterCompose(superclass = true)
	public void afterComposeProductoVM() {

	}
	

	@Override
	protected void inicializarOperaciones() {
	
		this.opCrearProducto = this.operacionHabilitada(ParamsLocal.OP_CREAR_PRODUCTO);
		this.opEditarProducto = this.operacionHabilitada(ParamsLocal.OP_EDITAR_PRODUCTO);
		this.opBorrarProducto = this.operacionHabilitada(ParamsLocal.OP_BORRAR_PRODUCTO);
		
	}
	
	
	private String filtroColumns[];

	private void inicializarFiltros() {

		this.filtroColumns = new String[2];

		for (int i = 0; i < this.filtroColumns.length; i++) {

			this.filtroColumns[i] = "";

		}

	}

	@Command
	@NotifyChange("lProductos")
	public void filtrarProducto() {

		this.lProductos = this.filtrarListaObject(this.filtroColumns, this.lProductosOri);

	}
	
	
	private void cargarProductos() {
		
		String sql = this.um.getSql("producto/listaProductos.sql");
		

		System.out.println("=======================");
		System.out.println(sql);
		System.out.println("=======================");
		
		this.lProductos = this.reg.sqlNativo(sql);
		this.lProductosOri = this.lProductos;
		
		
	}
	
	private Window modal;
	
	@Command
	public void modalProductoAgregar() {

		if (!this.opCrearProducto)
			return;

		this.editar = false;
		this.modalProducto(-1);

	}

	@Command
	public void modalProducto(@BindingParam("productoid") long productoid) {

		if (productoid != -1) {

			if (!this.opEditarProducto)
				return;

			this.editar = true;

			this.productoSelected = this.reg.getObjectById(Producto.class.getName(), productoid);

		} else {

			this.productoSelected = new Producto();
			
		}

		modal = (Window) Executions.createComponents("/sistema/zul/abm/productoModal.zul", this.mainComponent, null);
		Selectors.wireComponents(modal, this, false);
		modal.doModal();

	}
	
	@Command
	@NotifyChange("lProductos")
	public void guardar() {

		this.productoSelected = this.save(this.productoSelected);

		this.cargarProductos();

		this.modal.detach();

		if (editar) {

			Notification.show("Producto Actualizado.");

			this.editar = false;

		} else {

			Notification.show("Los datos deL Producto fueron agragados.");
		}

	}
	
	@Command
	public void borrarConfirmacion(@BindingParam("dato") long id) {

		if (!this.opBorrarProducto)
			return;

		

		EventListener event = new EventListener () {

			@Override
			public void onEvent(Event evt) throws Exception {
				
				if (evt.getName().equals(Messagebox.ON_YES)) {
					
					Producto p = reg.getObjectById(Producto.class.getName(), id);
					borrar(p);
					
				}
				
			}

		};

		this.mensajeEliminar("El Producto sera borrado permanentemente. \n Continuar?", event);

	}
	
	
	private void borrar(Producto p) {

		System.out.println("=====Inicio Borrado=========");
		
		this.reg.deleteObject(p);
		
		this.cargarProductos();
		
		BindUtils.postNotifyChange(null, null, this, "lProductos");
		BindUtils.postNotifyChange(null, null, this, "lProductosOri");
		
		System.out.println("=====Fin Borrado=========");
	}

	public List<Object[]> getlProductos() {
		return lProductos;
	}

	public void setlProductos(List<Object[]> lProductos) {
		this.lProductos = lProductos;
	}

	public boolean isOpCrearProducto() {
		return opCrearProducto;
	}

	public void setOpCrearProducto(boolean opCrearProducto) {
		this.opCrearProducto = opCrearProducto;
	}

	public boolean isOpEditarProducto() {
		return opEditarProducto;
	}

	public void setOpEditarProducto(boolean opEditarProducto) {
		this.opEditarProducto = opEditarProducto;
	}

	public boolean isOpBorrarProducto() {
		return opBorrarProducto;
	}

	public void setOpBorrarProducto(boolean opBorrarProducto) {
		this.opBorrarProducto = opBorrarProducto;
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

	public Producto getProductoSelected() {
		return productoSelected;
	}

	public void setProductoSelected(Producto productoSelected) {
		this.productoSelected = productoSelected;
	}
	
	

}
