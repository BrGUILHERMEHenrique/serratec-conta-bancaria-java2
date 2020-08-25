package org.serratec.backend2.trabalho.banco.exceptions;

public class OperationNotFoundException extends Exception{

	
	private static final long serialVersionUID = 7779405797163813818L;
	private String operacao;
	
	public OperationNotFoundException(String operacao) {
		super();
		this.operacao = operacao;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	
}
