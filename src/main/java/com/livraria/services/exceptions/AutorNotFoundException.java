package com.livraria.services.exceptions;

public class AutorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8728265012540972937L;
	
	public AutorNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
