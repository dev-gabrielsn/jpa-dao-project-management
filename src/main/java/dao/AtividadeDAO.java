package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import atividades.Atividade;

public class AtividadeDAO {
	private EntityManager em;

	public AtividadeDAO() {	}
	
	public boolean salvar(Atividade atividade) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(atividade);
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

	public boolean atualizar(Atividade atividade) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(atividade);
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
			Atividade entity = em.find(Atividade.class, id);
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

	public Atividade buscarID(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			Atividade atividade = em.find(Atividade.class, id);
			return atividade;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Atividade> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Atividade> query = em.createQuery("SELECT obj FROM Atividade obj", Atividade.class);
			List<Atividade> atividades = query.getResultList();
			return atividades;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
