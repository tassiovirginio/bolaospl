package br.ufba.reuse.bolao.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Bolao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Aposta> apostas;
    
    @ManyToOne
    private Grupo grupo;
    
    @ManyToOne
    private Campeonato campeonato;
    
    @ManyToOne
    private Jogo jogo;
    
    private String nome;
    
    private Date criacao;
    
    private Date fechamento;

	public void setId(Long id) {
        this.id = id;
    }
	
	public Long getId() {
	       return id;
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
	

	public List<Aposta> getApostas() {
		if(apostas == null) apostas = new ArrayList<>();
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bolao obj = (Bolao) o;

        if (!id.equals(obj.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
