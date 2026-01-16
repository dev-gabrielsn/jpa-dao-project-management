package tarefas;
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import atividades.Atividade;
import dao.TarefaDAO;
import projeto.Projeto;
import situacoes.SituacaoTarefa;


@Entity
@PrimaryKeyJoinColumn(name = "idTarefa", referencedColumnName = "idAtividade") 
public class Tarefa extends Atividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ElementCollection(fetch = FetchType.EAGER) // Eager para carregar os dados das tags em tempo de execução assim evitando null pointer exception.
	@CollectionTable(name="tarefa_tem_tags")//Aqui é criado uma tabela auxiliar que guarda o relacionamento das tags com a tarefa.
	private List<String> tags = new ArrayList<>();
	
	@Temporal(TemporalType.DATE) //Foi escolhido date já que no diagrama foi usado apenas Date e não DateTime.
	@Column(nullable=false) //Decidi que não faria sentido instanciar uma tarefa sem ter uma data de inicio, logo não pode ser nulo.
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dataFim;
	
	@Enumerated(EnumType.STRING) //O Enum tem como tipo String para manter o nome dos campos independente da ordem do ENUM, favorecendo a legibilidade das consultas.
	@Column(nullable=false) // Not null pois todas as tarefas devem ter uma situação.
	private SituacaoTarefa situacao;
	
	@ManyToOne // Muitas tarefas para um projeto.
	@JoinColumn(name="fk_idProjeto") // Tarefa é a fraca pois na relação um para muitos a FK fica na tabela dela criando assim uma dependencia com projeto.
	private Projeto projeto;
	
	//Relação é unidirecional, Tarefa aqui é a dominante, pois é quem consegue acessar a outra.
	//FetchType.EAGER para não evitar LazyInitializationException
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) 
	@JoinColumn(name = "fk_idTarefa") // FK ficará na tabela DETALHE
	private List<Detalhe> detalhes = new ArrayList<>();
	
	
	
	public Tarefa() {

	}

	public Tarefa(String titulo, String descricao, List<String> tags, Date dataInicio, Date dataFim, SituacaoTarefa situacao) {
		super(titulo, descricao);
		this.tags = tags;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.situacao = situacao;
	}
	
	
	
	public Tarefa(String titulo, String descricao, List<String> tags, Date dataInicio, Date dataFim, SituacaoTarefa situacao, Projeto projeto,
			List<Detalhe> detalhes) {
		super(titulo, descricao);
		this.tags = tags;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.situacao = situacao;
		this.projeto = projeto;
		this.detalhes = detalhes;
	}


	@Override
	public String toString() {
		return "\nTarefa [titulo=" + super.getTitulo() + ", descricao=" + super.getDescricao() + 
				", tags=" + tags + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + 
				", situacao=" + situacao + ", idProjeto=" + projeto.getIdProjeto() + ", detalhes=" + detalhes + "]";
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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

	public SituacaoTarefa getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoTarefa situacao) {
		this.situacao = situacao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Detalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<Detalhe> detalhes) {
		this.detalhes = detalhes;
	}
 
	public boolean remover() {
        // Garantir que a Tarefa seja removida do Projeto
        if (this.projeto != null) {
            this.projeto.removeTarefa(this);  // Remove a tarefa da lista de tarefas no Projeto
        }
        return new TarefaDAO().remover(this.getIdAtividade());  // Remove a Tarefa do banco
    }


   
}
