package com.livraria.services.exceptions;

public class AutorExistException extends RuntimeException {

	private static final long serialVersionUID = 8728265012540972937L;

	public AutorExistException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
