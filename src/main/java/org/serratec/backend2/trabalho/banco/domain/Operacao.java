package org.serratec.backend2.trabalho.banco.domain;

public class Operacao {
	
	private String tipo;
	private Double valor;
	public Operacao() {
		super();
	}
	public Operacao(String tipo, Double valor) {
		super();
		this.tipo = tipo;
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	 
}
