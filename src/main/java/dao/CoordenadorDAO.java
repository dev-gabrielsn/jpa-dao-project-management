package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pessoas.Coordenador;

public class CoordenadorDAO {
	private EntityManager em;

	public CoordenadorDAO() {	}
	
	public boolean salvar(Coordenador coordenador) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(coordenador);
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

	public boolean atualizar(Coordenador coordenador) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(coordenador);
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
			Coordenador entity = em.find(Coordenador.class, id);
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

	public Coordenador buscarID(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			Coordenador coordenador = em.find(Coordenador.class, id);
			return coordenador;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Coordenador> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Coordenador> query = em.createQuery("SELECT obj FROM Coordenador obj", Coordenador.class);
			List<Coordenador> coordenadores = query.getResultList();
			return coordenadores;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
