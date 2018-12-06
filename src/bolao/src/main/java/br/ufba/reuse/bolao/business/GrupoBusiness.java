package br.ufba.reuse.bolao.business;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.GrupoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;

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
	
	public Grupo searchByName(String name)
	{
		return dao.findByCriteriaReturnUniqueResult(eq("nome", name));
	}
	
	public Grupo searchByNameAndDescription(String name, String desc)
	{
		return dao.findByCriteriaReturnUniqueResult(eq("nome", name), eq("descricao", desc));
	}
	
	public List<Grupo> findAllParticipantGroups(Usuario user)
	{
		List<Grupo> list = dao.listAll();
	
		List<Grupo> userGroups = new ArrayList<Grupo>();
	
		for(Grupo l:list)
		{
			if(l.getDono().equals(user))
				userGroups.add(l);
			
			for(Usuario usr:l.getParticipantes())
			{
				if(usr.equals(user))				
					userGroups.add(l);
			}	
		}
				
		return userGroups;
	}
	
	public Usuario verifyParticipants(String name, Usuario user)
	{
		Usuario usr = null;
		
		Grupo gr = new Grupo();
		
		gr = dao.findByCriteriaReturnUniqueResult(eq("nome", name));
		
		if(gr.getDono().equals(user))
			return gr.getDono();
		
		for(Usuario u: gr.getParticipantes())
		{
			if(u.equals(user))
				return u;
		}
		
		return usr;
	}
	
}
