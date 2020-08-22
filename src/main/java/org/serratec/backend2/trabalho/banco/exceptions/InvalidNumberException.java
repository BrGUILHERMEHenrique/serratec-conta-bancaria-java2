package org.serratec.backend2.trabalho.banco.exceptions;

public class InvalidNumberException extends Exception {

	private static final long serialVersionUID = 7447054568039313646L;
	private Integer numero;
	
	public InvalidNumberException() {
	}
	
	public InvalidNumberException(Integer numero) {
		this.numero = numero;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}