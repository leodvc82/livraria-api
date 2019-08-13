package com.livraria.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.modelo.Comentario;
import com.livraria.modelo.Livro;
import com.livraria.repository.ComentarioRepository;
import com.livraria.repository.LivrosRepository;
import com.livraria.services.exceptions.LivroNotFoundException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Optional<Livro> op = livrosRepository.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		throw new LivroNotFoundException("O livro não foi encotrado!");
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);		
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNotFoundException("O livro não foi encontrado!");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentarioRepository.save(comentario);
	}
	
	public List<Comentario> listarComentarios(Long livroId) {
		Livro livro = buscar(livroId);
		return livro.getComentarios();
	}
	
}
