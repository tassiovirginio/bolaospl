package br.ufba.reuse.bolao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private Time time_01;
    
    private Time time_02;
    
    private Campeonato campeonato;
    
    public Time getTime_01() {
		return time_01;
	}

	public void setTime_01(Time time_01) {
		this.time_01 = time_01;
	}

	public Time getTime_02() {
		return time_02;
	}

	public void setTime_02(Time time_02) {
		this.time_02 = time_02;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
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

        Jogo candidato = (Jogo) o;

        if (!id.equals(candidato.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
