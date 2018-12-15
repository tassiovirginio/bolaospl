package br.ufba.reuse.bolao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Time time1;
    
    @ManyToOne
    private Time time2;

    private Date data;

    private Integer placar1;

    private Integer placar2;

    private Time vencedor;
    
    @ManyToOne
    private Campeonato campeonato;

    public Integer getPlacar2(){
        return placar2;
    }

    public void setPlacar2(Integer placar2){
        this.placar2 = placar2;
    }

    public Integer getPlacar1(){
        return placar1;
    }

    public void setPlacar1(Integer placar1){
        this.placar1 = placar1;
    }

    public Time getVencedor(){
        return vencedor;
    }

    public void setVencedor(Time vencedor){
        this.vencedor = vencedor;
    }

    public Date getData(){
        return data;
    }

    public void setData(Date data){
        this.data = data;
    }

    public String getNome(){
        return time1.getNome() + " x " + time2.getNome() + " - " + data;
    }
    
	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
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

        Jogo obj = (Jogo) o;

        if (!id.equals(obj.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
