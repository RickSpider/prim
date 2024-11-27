package com.prim.modelo;

import java.io.Serializable;

import com.doxacore.modelo.Modelo;
import com.doxacore.modelo.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Clientes")
public class Cliente extends Modelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 463767354776140145L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clienteid;
	
	private String RazonSocial;
	
	@ManyToOne
	@JoinColumn(name = "documentotipoid")
	private Tipo documentoTipoId;
	
	private String documentoNum;
	
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

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public String getRazonSocial() {
		return RazonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}

	public Tipo getDocumentoTipoId() {
		return documentoTipoId;
	}

	public void setDocumentoTipoId(Tipo documentoTipoId) {
		this.documentoTipoId = documentoTipoId;
	}

	public String getDocumentoNum() {
		return documentoNum;
	}

	public void setDocumentoNum(String documentoNum) {
		this.documentoNum = documentoNum;
	}
	
	
	
}
