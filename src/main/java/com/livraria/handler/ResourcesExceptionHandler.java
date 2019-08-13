package com.livraria.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livraria.modelo.DetalhesErro;
import com.livraria.services.exceptions.LivroNotFoundException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(LivroNotFoundException.class)
	public ResponseEntity<DetalhesErro> handleLivroNotFoundException(LivroNotFoundException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não foi encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.livraria.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}

}
