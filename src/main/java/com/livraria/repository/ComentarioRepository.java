package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria.modelo.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
}
