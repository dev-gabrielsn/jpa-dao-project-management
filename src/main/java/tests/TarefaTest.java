package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import situacoes.SituacaoTarefa;
import dao.TarefaDAO;
import tarefas.Tarefa;


public class TarefaTest {
	public static void main(String[] args) throws ParseException {
		//Esse arquivo foi usado no trabalho de JPA1, está desatualizado, mas fica como referência.
		//Confira o arquivo Tests.Java
		
		//teste de inserção
		Tarefa t1 = new Tarefa("Minha tarefa", null,  null, new Date(), null, SituacaoTarefa.CANCELADA);
		t1.salvar();
		
		
		List<String> tags = new ArrayList<>();
		tags.add(":)");
		tags.add(":O");
		tags.add("XD");
		tags.add(";)");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //com inserção manual da data.
		Date data = sdf.parse("11/05/2026");
		Tarefa t2 = new Tarefa("Nova tarefa", "Descrição", tags, new Date(), data, SituacaoTarefa.PENDENTE);
		t2.salvar();
		
		t2.remover();
		
		TarefaDAO td = new TarefaDAO();
		System.out.println(td.buscarTodos());
	}
	
}
