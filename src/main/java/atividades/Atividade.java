package atividades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import dao.AtividadeDAO;


@Entity
@Inheritance (strategy = InheritanceType.JOINED) //Não poderia ser Single_Table pois alguns campos da subclasse são not null e em joined se mantém a forma normalizada do banco.
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAtividade")
	private Long idAtividade;
	@Column(nullable=false) //Entende-se que o título é obrigatório, não pode ser nulo.
	private String titulo;
	private String descricao;
	
	public Atividade() { }
	
	public Atividade(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}
	

	public Atividade(Long id, String titulo, String descricao) {
		this.idAtividade = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "\nAtividade [idAtividade=" + idAtividade + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}
	
	public boolean salvar() {
		//manda salvar o objeto atual
		return new AtividadeDAO().salvar(this);
	}
	public List<Atividade> buscarTodos(){
		return new AtividadeDAO().buscarTodos();
	}
	public boolean atualizar(){
		//manda editar/atualizar o objeto atual
		return new AtividadeDAO().atualizar(this);
	}
	public boolean remover(){
		return new AtividadeDAO().remover(this.getIdAtividade());
	}
	public Atividade buscarID(){
		return new AtividadeDAO().buscarID(this.getIdAtividade());
	}
	
	
}
