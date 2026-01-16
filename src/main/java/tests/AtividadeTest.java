package tests;
import atividades.Atividade;
import dao.AtividadeDAO;

public class AtividadeTest {

	public static void main(String[] args) {

		Atividade a1 = new Atividade("123", null);
		Atividade a2 = new Atividade("ABC", "XYZ");
		Atividade a3 = new Atividade();

		//Teste de inserção
		a1.salvar();
		a2.salvar();
		a3.salvar(); //Esse vai dar erro no campo Título pois é not null
		
		//Teste de remoção
		a2.remover();
		
		//Teste de atualização
		a1.setTitulo("Novo Titulo");
		a1.atualizar();
		
		//teste para mostrar todas atividades
		AtividadeDAO ad = new AtividadeDAO();
		System.out.println(ad.buscarTodos());
		

	}

}
