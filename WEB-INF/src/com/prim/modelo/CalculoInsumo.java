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
@Table(name="CalculosInsumos")
public class CalculoInsumo extends Modelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7432173303314307564L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long calculoinsumoid;
	
	@ManyToOne
	@JoinColumn(name = "calculoid", nullable = false)
	private Tipo calculo;
	
	@Column(nullable = false)
	private Long origeninsumoid;
	@Column(nullable = false)
	private String insumonombre;
	private double precioUnitario;
	private double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "insumotipoid", nullable = false)
	private Tipo insumotipo;

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

	

	public Long getCalculoinsumoid() {
		return calculoinsumoid;
	}

	public void setCalculoinsumoid(Long calculoinsumoid) {
		this.calculoinsumoid = calculoinsumoid;
	}

	public Tipo getCalculo() {
		return calculo;
	}

	public void setCalculo(Tipo calculo) {
		this.calculo = calculo;
	}

	public void setOrigeninsumoid(Long origeninsumoid) {
		this.origeninsumoid = origeninsumoid;
	}

	public long getOrigeninsumoid() {
		return origeninsumoid;
	}

	public void setOrigeninsumoid(long origeninsumoid) {
		this.origeninsumoid = origeninsumoid;
	}

	public String getInsumonombre() {
		return insumonombre;
	}

	public void setInsumonombre(String insumonombre) {
		this.insumonombre = insumonombre;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Tipo getInsumotipo() {
		return insumotipo;
	}

	public void setInsumotipo(Tipo insumotipo) {
		this.insumotipo = insumotipo;
	}

	

}
