package com.prim.modelo;

import java.io.Serializable;

import com.doxacore.modelo.Modelo;
import com.doxacore.modelo.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Insumos")
public class Insumo extends Modelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987002568945594684L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long insumoid;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "insumotipoid", nullable = false)
	private Tipo insumoTipo;

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

	public Long getInsumoid() {
		return insumoid;
	}

	public void setInsumoid(Long insumoid) {
		this.insumoid = insumoid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Tipo getInsumoTipo() {
		return insumoTipo;
	}

	public void setInsumoTipo(Tipo insumoTipo) {
		this.insumoTipo = insumoTipo;
	}


	
	
}
