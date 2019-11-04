package br.com.nathan.desafio.controller;

import java.util.List;
import java.util.Optional;

import br.com.nathan.desafio.model.Contato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.nathan.desafio.repository.ContatoRepository;

@RestController
@RequestMapping({ "/contatos" })
public class ContatoController {

	private ContatoRepository repository;

	ContatoController(ContatoRepository contatoRepository) {
		this.repository = contatoRepository;
	}

	@GetMapping
	public List<?> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Contato create(@RequestBody Contato contato) {
		return repository.save(contato);
	}


	@PutMapping(value = "/{id}")
	public ResponseEntity<Contato> update(@PathVariable("id") Integer id, @RequestBody Contato contato) {

		return repository.findById(id).map(record -> {
			record.setNome(contato.getNome());
			record.setCanal(contato.getCanal());
			record.setValor(contato.getValor());
			record.setObs(contato.getObs());
			Contato updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
