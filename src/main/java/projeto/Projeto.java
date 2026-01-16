package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import tarefas.Tarefa;
import dao.ProjetoDAO;
import pessoas.Coordenador;


@Entity
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjeto;
	//Relacionamento unidirecional 1:1
	@OneToOne (cascade = CascadeType.PERSIST) //O projeto consegue acessar o coordenador, logo ele é o dominante nessa relação.
	@JoinColumn (name="idCoordenador")
	private Coordenador coordenador;
	
	@Column(nullable=false) //não pode ter projeto sem nome.
	private String nome;
	@Temporal(TemporalType.DATE) //Foi escolhido date já que no diagrama foi usado apenas Date e não DateTime.
	@Column(nullable=false) //Decidi que não faria sentido instanciar uma tarefa sem ter uma data de inicio, logo não pode ser nulo.
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dataFim;
	
	//Um projeto para muitas tarefas. 
	//O mappedBy = "projeto" indica que a Tarefa é o lado forte da relação (contém a chave estrangeira), 
	//e o relacionamento será gerido pela propriedade projeto na classe Tarefa.
	//CascadeType.ALL é usado para que o que for feito no projeto seja espelhado nas tarefas (Remoção, atualização, inserção).
	//o FetchType é EAGER para evitar LazyInitializationException.
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Tarefa> tarefas = new ArrayList<>();
	
	
	public Projeto() {	}

	
	public Projeto(Coordenador coordenador, String nome, Date dataInicio, Date dataFim) {
		this.coordenador = coordenador;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}


	@Override
	public String toString() {
		return "\nProjeto [idProjeto=" + idProjeto 
				+ ", coordenador=" + (coordenador != null ? coordenador.getNome() : "Desconhecido")
				+ ", nome=" + nome + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim 
				+ ", tarefas=" + tarefas + "]";
	}

	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
	//Caso precisemos adicionar tarefas ao projeto.
	public void addTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        tarefa.setProjeto(this);
    }

	//Caso precisemos remover tarfas do projeto.
    public void removeTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
        tarefa.setProjeto(null);
    }

	public boolean salvar() {
		//manda salvar o objeto atual
		return new ProjetoDAO().salvar(this);
	}
	public List<Projeto> buscarTodos(){
		return new ProjetoDAO().buscarTodos();
	}
	public boolean atualizar(){
		//manda editar/atualizar o objeto atual
		return new ProjetoDAO().atualizar(this);
	}
	public boolean remover(){
		return new ProjetoDAO().remover(this.getIdProjeto());
	}
	public Projeto buscarID(){
		return new ProjetoDAO().buscarID(this.getIdProjeto());
	}
	
}
