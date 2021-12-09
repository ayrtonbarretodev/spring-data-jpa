package br.com.ayrton.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ayrton.spring.data.orm.Unidade;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<Unidade,Integer>{

}
