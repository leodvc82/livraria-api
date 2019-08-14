package com.livraria.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livraria.modelo.DetalhesErro;
import com.livraria.services.exceptions.AutorExistException;
import com.livraria.services.exceptions.AutorNotFoundException;
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
	
	@ExceptionHandler(AutorExistException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistException(AutorExistException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("O autor já existe!");
		erro.setMensagemDesenvolvedor("http://erros.livraria.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
		
	}
	
	@ExceptionHandler(AutorNotFoundException.class)
	public ResponseEntity<DetalhesErro> handleAutorNotFoundException(AutorNotFoundException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O Autor não foi encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.livraria.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.livraria.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
	}
	
	

}
