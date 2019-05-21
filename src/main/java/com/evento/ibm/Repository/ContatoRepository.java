package com.evento.ibm.Repository;

import org.springframework.data.repository.CrudRepository;

import com.evento.ibm.Model.Contato;;

public interface ContatoRepository extends CrudRepository<Contato, String>{
	Contato findByCodigo(long codigo);

}
