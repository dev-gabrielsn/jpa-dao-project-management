package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tarefas.Tarefa;

public class TarefaDAO {
	private EntityManager em;

	public TarefaDAO() {	}
	
	public boolean salvar(Tarefa tarefa) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(tarefa);
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

	public boolean atualizar(Tarefa tarefa) {
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(tarefa);
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
			Tarefa entity = em.find(Tarefa.class, id);
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

	public Tarefa buscarID(Long id) {
		try {
			em = JPAUtil.getEntityManager();
			Tarefa tarefa = em.find(Tarefa.class, id);
			return tarefa;
		} catch (RuntimeException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} 
	}

	public List<Tarefa> buscarTodos() {
		try {
			em = JPAUtil.getEntityManager();
			TypedQuery<Tarefa> query = em.createQuery("SELECT obj FROM Tarefa obj", Tarefa.class);
			List<Tarefa> tarefas = query.getResultList();
			return tarefas;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			return null;
		} 
	}
}
