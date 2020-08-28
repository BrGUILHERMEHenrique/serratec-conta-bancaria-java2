package org.serratec.backend2.trabalho.banco.domain;


public class Operacao {
	
	// TODO Tipo de operação deveria ser um Enum FEITO
	private TipoOperacao tipo;
	private Double valor;
	public Operacao() {
		super();
	}
	public Operacao(TipoOperacao tipo, Double valor) {
		super();
		this.tipo = tipo;
		this.valor = valor;
	}
	public TipoOperacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoOperacao tipo) {
		this.tipo = tipo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	 
}
