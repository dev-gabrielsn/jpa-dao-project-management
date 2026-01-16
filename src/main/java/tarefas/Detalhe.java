package tarefas;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Detalhe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalhe;
	
	@Column(nullable=false) //deve conter um comentário, mas não necessariamente anexos.
	private String comentario;
	
	@ElementCollection(fetch = FetchType.EAGER) // Eager para carregar os dados dos anexos em tempo de execução assim evitando NullPointerException. 
	@CollectionTable(name="detalhe_tem_anexo") //Aqui é criado uma tabela auxiliar que guarda o relacionamento dos anexos com seus detalhes.
	private List<String> anexo = new ArrayList<>();
	
	public Detalhe() {
		
	}

	public Detalhe(String comentario, List<String> anexo) {
		this.comentario = comentario;
		this.anexo = anexo;
	}


	@Override
	public String toString() {
		return "Detalhe [idDetalhe=" + idDetalhe + ", comentario=" + comentario + ", anexo=" + anexo + "]";
	}

	public void setIdDetalhe(Long idDetalhe) {
		this.idDetalhe = idDetalhe;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<String> getAnexo() {
		return anexo;
	}

	public void setAnexo(List<String> anexo) {
		this.anexo = anexo;
	}
	
	
}
