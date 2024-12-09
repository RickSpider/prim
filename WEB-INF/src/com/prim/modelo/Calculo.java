package com.prim.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.doxacore.modelo.Modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Calculos")
public class Calculo extends Modelo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5071622350013605151L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long calculoid;
	
	@Column(nullable = false)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "presupuestoid", nullable = false)
	private Presupuesto presupuesto;
	
	@OneToMany(mappedBy = "calculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CalculoInsumo> detalles = new ArrayList<CalculoInsumo>();

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

	public long getCalculoid() {
		return calculoid;
	}

	public void setCalculoid(long calculoid) {
		this.calculoid = calculoid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	
}
