package com.algaworks.compras.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.compras.model.Fornecedor;
import com.algaworks.compras.util.jpa.EntityManagerProducer;

public class Fornecedores implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Inject
	private EntityManager manager;

	@Inject
	private EntityManagerProducer factory;
	
	public void adicionar(Fornecedor fornecedor) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(fornecedor);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Fornecedor> todosComCidadeEstado() {
		return manager.createQuery("from Fornecedor f inner join fetch f.cidade c inner join fetch c.estado"
				, Fornecedor.class).getResultList();
	}
}
