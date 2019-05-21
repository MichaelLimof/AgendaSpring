package com.evento.ibm.Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;




@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String numero_tel;
	@NotEmpty
	private String tipo_telefone;
	@NotEmpty
	private String ddd;
	@NotEmpty
	private String telefone;
	
	@ManyToOne
	private Contato contato;

	public String getNumero_tel() {
		return numero_tel;
	}

	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}

	public String getTipo_telefone() {
		return tipo_telefone;
	}

	public void setTipo_telefone(String tipo_telefone) {
		this.tipo_telefone = tipo_telefone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}  
	
		
	
	
	
}