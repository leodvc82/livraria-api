package com.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livraria.modelo.Comentario;
import com.livraria.modelo.Livro;
import com.livraria.services.LivrosService;

@CrossOrigin
@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livrosService;

	//@RequestMapping(value = "/livros", method = RequestMethod.GET)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@RequestMapping(method =  RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build(); //  retorn da uri para localizar o objeto criado 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro = livrosService.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	@RequestMapping(value = "/buscar/{keyword}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarByKeyWord(@PathVariable("keyword") String keyword) {
		List<Livro> livros = livrosService.listar(keyword);	
		return ResponseEntity.status(HttpStatus.OK).body(livros);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livrosService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosService.atualizar(livro);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @Valid @RequestBody Comentario comentario) {
		comentario = livrosService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long id){
		List<Comentario> comentarios = livrosService.listarComentarios(id);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
}
