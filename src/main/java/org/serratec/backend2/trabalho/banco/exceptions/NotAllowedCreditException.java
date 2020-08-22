package org.serratec.backend2.trabalho.banco.exceptions;

public class NotAllowedCreditException extends Exception{

	private static final long serialVersionUID = -4551380531352431917L;
	private Double valor;
	
	public NotAllowedCreditException() {}

	public NotAllowedCreditException(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}