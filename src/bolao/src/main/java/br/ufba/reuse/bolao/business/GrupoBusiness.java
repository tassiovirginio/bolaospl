package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.GrupoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Grupo;

@Component
@Transactional
public class GrupoBusiness extends BusinessGeneric<GrupoDAO, Grupo> {
	
	public List<Grupo> listAll() {
		List<Grupo> lista = dao.listAll();
		for (Grupo grupo : lista) {
			grupo.getParticipantes().size();
		}
        return lista;
    }
	
}
