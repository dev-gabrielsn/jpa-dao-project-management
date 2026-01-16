package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tarefas.Detalhe;


public class DetalheDAO {
	private EntityManager em;

	public DetalheDAO() {	}
	
	public boolean salvar(Detalhe detalhe) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(detalhe);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	public boolean atualizar(Detalhe detalhe) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(detalhe);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public boolean remover(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Detalhe entity = em.find(Detalhe.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		} 
	}

	public Detalhe buscarID(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			Detalhe detalhe = em.find(Detalhe.class, id);
			return detalhe;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Detalhe> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Detalhe> query = em.createQuery("SELECT obj FROM Detalhes obj", Detalhe.class);
			List<Detalhe> detalhes = query.getResultList();
			return detalhes;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
