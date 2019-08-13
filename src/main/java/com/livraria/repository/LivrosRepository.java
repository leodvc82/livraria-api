package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.modelo.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{
	
}
