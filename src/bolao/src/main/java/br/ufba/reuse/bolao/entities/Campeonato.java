package br.ufba.reuse.bolao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Campeonato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;

    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Jogo> jogos;
    
    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Time> times;

    public Campeonato(){}

    public Campeonato(String nome){
        this.nome = nome;
    }
    
    public List<Time> getTimes() {
    	if(times == null) times = new ArrayList<>();
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    public List<Jogo> getJogos() {
    	if(jogos == null) jogos = new ArrayList<>();
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

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

        Campeonato obj = (Campeonato) o;

        if (!id.equals(obj.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
