package br.ufba.reuse.bolao.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Aposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private Aposta aposta;
    
    private Jogo jogo;
    
    private Integer placar01;
    
    private Integer placar02;
    
    private Time timeApostado;
    
    //private Bolao bolao;
    
    public Long getId() {
        return id;
    }

    public Aposta getAposta() {
		return aposta;
	}

	public void setAposta(Aposta aposta) {
		this.aposta = aposta;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public Integer getPlacar01() {
		return placar01;
	}

	public void setPlacar01(Integer placar01) {
		this.placar01 = placar01;
	}

	public Integer getPlacar02() {
		return placar02;
	}

	public void setPlacar02(Integer placar02) {
		this.placar02 = placar02;
	}

	public Time getTimeApostado() {
		return timeApostado;
	}

	public void setTimeApostado(Time timeApostado) {
		this.timeApostado = timeApostado;
	}

	public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aposta candidato = (Aposta) o;

        if (!id.equals(candidato.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
