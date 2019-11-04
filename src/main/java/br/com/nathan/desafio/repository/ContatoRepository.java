package br.com.nathan.desafio.repository;

import br.com.nathan.desafio.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContatoRepository extends JpaRepository<Contato, Integer>{

}
