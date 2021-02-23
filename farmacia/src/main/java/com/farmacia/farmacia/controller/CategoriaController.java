package com.farmacia.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController 
{
	@Autowired
	private CategoriaRepository categoria;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll()
	{
		return ResponseEntity.ok(categoria.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable long id)
	{
		return categoria.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeCategoria}")
	public ResponseEntity<List<Categoria>> GetbyTitulo(@PathVariable String nomeCategoria)
	{
		return ResponseEntity.ok(categoria.findAllByNomeCategoriaContainingIgnoreCase(nomeCategoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.categoria.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.categoria.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)
	{
		categoria.deleteById(id);
	}
}