package tests;
import pessoas.Coordenador;
import projeto.Projeto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import atividades.Atividade;
import tarefas.Detalhe;
import tarefas.Tarefa;
import situacoes.SituacaoTarefa;
import dao.ProjetoDAO;
import dao.TarefaDAO;


public class Tests {

	public static void main(String[] args) throws ParseException {
		
		// instancia coordenadores
		Coordenador c1 = new Coordenador();
		c1.setEmail("sb@gmail.com");
		c1.setNome("Silvia B.");
		Coordenador c2 = new Coordenador("Andre P.","ap@gmail.com");
		Coordenador c3 = new Coordenador("Teste", "teste@teste.com");
		// Coordenador c4 = new Coordenador(null, null); // gera erro pois nome não pode ser null.
		
		
		// instancia projetos e adiciona os cordenadores a eles
		Projeto p1 = new Projeto();
		p1.setNome("Robotif");
		p1.setCoordenador(c1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //com inserção manual da data.
		Date data = sdf.parse("01/01/2025");
		p1.setDataInicio(data);
		p1.setDataFim(null);
		
		Projeto p2 = new Projeto(c2, "Poalab", new Date(), null);
		Projeto p3 = new Projeto(c3, "Teste", new Date(), new Date());
		// Projeto p4 = new Projeto(c1, "Teste", new Date(), new Date());
		
		//salva os projetos no banco
		p1.salvar();
		p2.salvar();
		p3.salvar();
		// p4.salvar();
		
		// instancia tarefas
		Tarefa t1 = new Tarefa();
		t1.setTitulo("Pesquisar Arduino");
		t1.setDescricao("Pesquisar e documentar estratégias para introduzir o pensamento computacional utilizando Arduino.");
		List<String> tags = new ArrayList<>();
		tags.add("Pensamento Computacional");
		tags.add("Ensino Básico");
		tags.add("Robótica");
		t1.setTags(tags);
		t1.setDataInicio(new Date());
		t1.setDataFim(null);
		t1.setSituacao(SituacaoTarefa.EM_ANDAMENTO);
		t1.setProjeto(p1);
		t1.setDetalhes(null);
		
		List<String> tags2 = new ArrayList<>();
		tags2.add("Sustentabilidade");
		tags2.add("Reciclagem");
		tags2.add("Criativo");
		Tarefa t2 = new Tarefa("Precious Plastic", 
				"Usar a máquina da precious plastic para fazer alguma coisa bacana :)", 
				tags2, new Date(), null, SituacaoTarefa.PENDENTE, p2, null);
		Tarefa t3 = new Tarefa("Teste", "Tarefa teste, só pra testar", null, new Date(), null, SituacaoTarefa.CANCELADA, p3, null);
		
		//Adiciona as tarefas em seus respectivos projetos.
		p1.getTarefas().add(t1);
		p2.getTarefas().add(t2);
		p3.getTarefas().add(t3);
		
		//Salva as tarefas no banco e atualiza os projetos
		t1.salvar();
		t2.salvar();
		t3.salvar();	
		p1.atualizar();
		p2.atualizar();
		p3.atualizar();
		
		
		
		
		//Instancia detalhes
		Detalhe d1 = new Detalhe();
		d1.setComentario("Pedro Wolff vai trabalhar nessa :)");
		
		List<String> anexos = new ArrayList<>();
		anexos.add("Tutorial_TinkerCAD.pdf");
		d1.setAnexo(anexos);
		
		List<String> anexos2 = new ArrayList<>();
		anexos2.add("Instruções_Precious_Plastic.pdf");
		Detalhe d2 = new Detalhe("Essa deixa pro Rafa ;)", anexos2);
		
		//Adiciona os detalhes nas tarefas t1 e t2.
		List<Detalhe> detalhes = new ArrayList<>();
		detalhes.add(d1);
		t1.setDetalhes(detalhes);
		List<Detalhe> detalhes2 = new ArrayList<>();
		detalhes2.add(d2);
		t2.setDetalhes(detalhes2);
		
		
		//atualiza t1 e t2
		t1.atualizar();
		t2.atualizar();
		
		
		//instancia atividades
		Atividade a1 = new Atividade();
		a1.setTitulo("Ir no MaisMovimento");
		a1.setDescricao("Vamos no MaisMovimento com o pessoal do grupinho <3");
		Atividade a2 = new Atividade("Noite dos tabuleiros", "Jogar tabuleiros e comer pizza, bom demais. XD");
		
		//salva atividades no banco
		a1.salvar();
		a2.salvar();
				
		//mostra no console todos os projetos:
		System.out.println("\n######### TODOS OS PROJETOS (COM O TESTE) ###########");
		ProjetoDAO pd = new ProjetoDAO();
		System.out.println(pd.buscarTodos());
		
		//Mostra todas as tarefas:
		System.out.println("\n######### TODAS AS TAREFAS ###########");
		TarefaDAO td = new TarefaDAO();
		System.out.println(td.buscarTodos());
		
		//remove o projeto teste e as suas tarefas vinculadas, mas o cordenador permanece no banco.
		pd.remover(p3.getIdProjeto());
		
		//mostra no console todos os projetos agora sem o projeto teste:
		System.out.println("\n######### TODOS OS PROJETOS (SEM O TESTE) ###########");
		System.out.println(pd.buscarTodos());
				
		
		
				
	}

}
