package br.ufba.reuse.bolao.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.JogoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Jogo;

@Component
@Transactional
public class JogoBusiness extends BusinessGeneric<JogoDAO, Jogo> {

    public List<Jogo> listBy(Campeonato campeonato) {
        List<Jogo> lista = dao.listAll();
        List<Jogo> listaResult = new ArrayList<Jogo>();
		for (Jogo jogo : lista) {
            if(jogo.getCampeonato().equals(campeonato)){
                listaResult.add(jogo);
            }
		}
        return listaResult;
	}


}