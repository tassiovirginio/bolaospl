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
    
    private int placar_01;
    
    private int placar_02;
    
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

	public int getPlacar_01() {
		return placar_01;
	}

	public void setPlacar_01(int placar_01) {
		this.placar_01 = placar_01;
	}

	public int getPlacar_02() {
		return placar_02;
	}

	public void setPlacar_02(int placar_02) {
		this.placar_02 = placar_02;
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
