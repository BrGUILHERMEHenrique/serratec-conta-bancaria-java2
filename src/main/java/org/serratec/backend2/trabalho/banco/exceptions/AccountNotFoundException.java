package org.serratec.backend2.trabalho.banco.exceptions;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 9094377705023050690L;
	
	private Integer numero;

	public AccountNotFoundException() {
	}

	public AccountNotFoundException(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}