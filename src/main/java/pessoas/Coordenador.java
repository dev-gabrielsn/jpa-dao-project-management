package pessoas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import dao.CoordenadorDAO;


@Entity
public class Coordenador implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCoordenador;
	@Column(nullable=false) // Não pode haver coordenador com nome Nulo.
	private String nome;
	@Column(nullable=false) // Não pode haver coordenador com email Nulo.
	private String email;
	
	public Coordenador() {
		
	}

	public Coordenador(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public Long getIdCoordenador() {
		return idCoordenador;
	}

	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean salvar() {
		//manda salvar o objeto atual
		return new CoordenadorDAO().salvar(this);
	}
	public List<Coordenador> buscarTodos(){
		return new CoordenadorDAO().buscarTodos();
	}
	public boolean atualizar(){
		//manda editar/atualizar o objeto atual
		return new CoordenadorDAO().atualizar(this);
	}
	public boolean remover(){
		return new CoordenadorDAO().remover(this.getIdCoordenador());
	}
	public Coordenador buscarID(){
		return new CoordenadorDAO().buscarID(this.getIdCoordenador());
	}
	
   
}
