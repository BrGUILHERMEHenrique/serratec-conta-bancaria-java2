package org.serratec.backend2.trabalho.banco.exceptions;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 3699771674383206215L;
	private Double saldo;
	private Double valor;
	
	public InsufficientFundsException() {
	}

	public InsufficientFundsException(Double saldo, Double valor) {
		this.saldo = saldo;
		this.valor = valor;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}