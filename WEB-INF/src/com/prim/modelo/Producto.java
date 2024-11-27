package com.prim.modelo;

import java.io.Serializable;

import com.doxacore.modelo.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")
public class Producto extends Modelo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5674413516428823650L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productoid;
	private String nombre;
	
	@Override
	public Object[] getArrayObjectDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getStringDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getProductoid() {
		return productoid;
	}
	public void setProductoid(long productoid) {
		this.productoid = productoid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
