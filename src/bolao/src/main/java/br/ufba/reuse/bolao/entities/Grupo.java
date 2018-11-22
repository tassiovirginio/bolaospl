package br.ufba.reuse.bolao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    
    private String descricao;
    
    /** Adicionar notacao de relacionamento de banco de dados **/
//    private List<Usuario> participantes;
    
    private Usuario dono;

    public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

//	public List<Usuario> getParticipantes() {
//		return participantes;
//	}
//
//	public void setParticipantes(List<Usuario> participantes) {
//		this.participantes = participantes;
//	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo candidato = (Grupo) o;

        if (!id.equals(candidato.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
