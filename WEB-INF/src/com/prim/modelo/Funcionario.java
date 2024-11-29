package com.prim.modelo;

import java.io.Serializable;

import com.doxacore.modelo.Modelo;
import com.doxacore.modelo.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Funcionarios")
public class Funcionario extends Modelo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1692967936118925465L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long funcionarioid;
	
	@ManyToOne
	@JoinColumn(name = "usuarioid", nullable = false)
	private Usuario usuario;

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

	public Long getFuncionarioid() {
		return funcionarioid;
	}

	public void setFuncionarioid(Long funcionarioid) {
		this.funcionarioid = funcionarioid;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
