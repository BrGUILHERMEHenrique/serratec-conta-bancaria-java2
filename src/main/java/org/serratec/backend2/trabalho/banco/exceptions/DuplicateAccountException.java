package org.serratec.backend2.trabalho.banco.exceptions;

public class DuplicateAccountException extends Exception{
	
	private static final long serialVersionUID = -3500022764688391387L;
	private String msg = "o número da conta já existe";
	
	public DuplicateAccountException() {
		super();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
