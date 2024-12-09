package com.prim.modelo;

import java.io.Serializable;
import java.util.Date;

import com.doxacore.modelo.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="Presupuestos")
public class Presupuesto extends Modelo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8304617408582348032L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long presupuestoid;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "clienteid", nullable = false)
	private Cliente cliente;

	
	@ManyToOne
	@JoinColumn(name = "funcionarioid", nullable = false)
	private Funcionario funcionario;
	
	private long numeroInterno;
	
	@ManyToOne
	@JoinColumn(name = "estadotipoid", nullable = false)
	private Funcionario estadoTipo;

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

	public Long getPresupuestoid() {
		return presupuestoid;
	}

	public void setPresupuestoid(Long presupuestoid) {
		this.presupuestoid = presupuestoid;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public long getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(long numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public Funcionario getEstadoTipo() {
		return estadoTipo;
	}

	public void setEstadoTipo(Funcionario estadoTipo) {
		this.estadoTipo = estadoTipo;
	}

	
	
	
}
