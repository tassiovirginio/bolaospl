package br.ufba.reuse.bolao.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.BolaoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;

@Component
@Transactional
public class BolaoBusiness extends BusinessGeneric<BolaoDAO, Bolao> {
	
	public List<Bolao> listAll() {
		List<Bolao> lista = dao.listAll();
		for (Bolao bolao : lista) {
			bolao.getApostas().size();
		}
        return lista;
	}

	public List<Bolao> listBy(Grupo grupo) {
		List<Bolao> lista = dao.listAll();
		List<Bolao> listaResult = new ArrayList<>();
		for (Bolao bolao : lista) {
			if(bolao.getGrupo().equals(grupo)){
				bolao.getApostas().size();
				listaResult.add(bolao);
			}
		}
        return listaResult;
	}
	

	
}
