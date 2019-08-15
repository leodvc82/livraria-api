package com.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.livraria.modelo.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{
	
	@Query("select l from Livro l where LOWER(l.nome) LIKE LOWER(concat('%', :keyword,'%')) or LOWER(l.autor.nome) LIKE LOWER(concat('%', :keyword,'%')) ")
	List<Livro> findKeyWord(@Param("keyword") String keyword);
	
} 
