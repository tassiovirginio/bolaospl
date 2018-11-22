package br.ufba.reuse.bolao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Campeonato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    
    /** Adicionar notacao de relacionamento de banco de dados **/
//    private List<Jogo> jogos;
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Jogo> getJogos() {
//		return jogos;
//	}
//
//	public void setJogos(List<Jogo> jogos) {
//		this.jogos = jogos;
//	}
	
	
  //private TipoCampeonato campeonato;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campeonato candidato = (Campeonato) o;

        if (!id.equals(candidato.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
