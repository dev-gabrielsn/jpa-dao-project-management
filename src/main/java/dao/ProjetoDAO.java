package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import projeto.Projeto;

public class ProjetoDAO {
	private EntityManager em;

	public ProjetoDAO() {	}
	
	public boolean salvar(Projeto projeto) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(projeto);
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

	public boolean atualizar(Projeto projeto) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(projeto);
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
			Projeto entity = em.find(Projeto.class, id);
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

	public Projeto buscarID(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			Projeto projeto = em.find(Projeto.class, id);
			return projeto;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Projeto> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Projeto> query = em.createQuery("SELECT obj FROM Projeto obj", Projeto.class);
			List<Projeto> projetos = query.getResultList();
			return projetos;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
