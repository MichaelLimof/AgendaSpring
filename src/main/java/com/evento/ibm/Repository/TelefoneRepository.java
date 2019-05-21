package com.evento.ibm.Repository;

import org.springframework.data.repository.CrudRepository;


import com.evento.ibm.Model.Contato;

import com.evento.ibm.Model.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, String>{

Iterable<Telefone> findByContato(Contato contato);
}
