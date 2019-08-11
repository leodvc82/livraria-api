package com.livraria.services.exceptions;

public class LivroNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 7500021566267749301L;

	public LivroNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public LivroNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	} 
}
