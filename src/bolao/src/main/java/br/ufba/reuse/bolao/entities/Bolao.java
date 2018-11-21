package br.ufba.reuse.bolao.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Bolao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    /** Adicionar notacao de relacionamento de banco de dados **/
    private List<Aposta> apostas;
    
    private Grupo grupo;
    
    private Campeonato campeonato;
    
    private Jogo jogo;
    
    private String nomeBolao;
    
    private Date dataCriacao;
    
    private Date dataFechamento;
    
    /** Adicionar notacao de relacionamento de banco de dados 
    private List<Jogo> jogos; **/

	public void setId(Long id) {
        this.id = id;
    }
	
	public Long getId() {
	       return id;
	}

    public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public String getNomeBolao() {
		return nomeBolao;
	}

	public void setNomeBolao(String nomeBolao) {
		this.nomeBolao = nomeBolao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bolao candidato = (Bolao) o;

        if (!id.equals(candidato.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
