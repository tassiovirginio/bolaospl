package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.CampeonatoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;

@Component
@Transactional
public class CampeonatoBusiness extends BusinessGeneric<CampeonatoDAO, Campeonato> {
	
	public List<Campeonato> listAll() {
		List<Campeonato> lista = dao.listAll();
		for (Campeonato campeonato : lista) {
			campeonato.getJogos().size();
			campeonato.getTimes().size();
		}
        return lista;
    }
	
}
