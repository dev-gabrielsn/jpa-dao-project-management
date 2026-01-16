package tests;

import java.util.Date;

import dao.ProjetoDAO;
import dao.TarefaDAO;
import projeto.Projeto;
import situacoes.SituacaoTarefa;
import tarefas.Tarefa;

public class TarefaProjetoTest {

	public static void main(String[] args) {
		//Teste de remoção de tarefa desvinculando ela do projeto.
		Projeto p1 = new Projeto(null, "Projeto", new Date(), new Date());
		p1.salvar();
		Tarefa t1 = new Tarefa("Remover!", "Remova essa tarefa!", null, new Date(), null, SituacaoTarefa.CANCELADA);
		t1.salvar();
		t1.setProjeto(p1);
		t1.atualizar();
		p1.addTarefa(t1);
		p1.atualizar();
		ProjetoDAO pd = new ProjetoDAO();
		System.out.println(pd.buscarTodos());
		t1.remover();
		p1.atualizar();
		System.out.println(pd.buscarTodos());

	}

}
