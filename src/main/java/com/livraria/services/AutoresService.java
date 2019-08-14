package com.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.modelo.Autor;
import com.livraria.repository.AutoresRepository;
import com.livraria.services.exceptions.AutorExistException;
import com.livraria.services.exceptions.AutorNotFoundException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> op = autoresRepository.findById(autor.getId());
			if(op.isPresent()) {
				throw new AutorExistException("O autor já existe.");
			}
		}
		
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Optional<Autor> op = autoresRepository.findById(id);
		
		if(op.isPresent()==false) {
			throw new AutorNotFoundException("O autor não existe.");
		}
		
		return op.get();
	}
	
	

}
