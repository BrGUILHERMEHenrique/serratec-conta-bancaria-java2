package org.serratec.backend2.trabalho.banco.exceptions;

public class NotAlowedValueException extends Exception{

	private static final long serialVersionUID = -1281767208893242201L;
	private String msg = "O valor passado deve ser maior que 0(ZERO)";
	public NotAlowedValueException() {
		super();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
