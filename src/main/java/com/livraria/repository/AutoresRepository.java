package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.modelo.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {
	
}
