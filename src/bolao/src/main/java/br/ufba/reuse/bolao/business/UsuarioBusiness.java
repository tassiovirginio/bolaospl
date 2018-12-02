package br.ufba.reuse.bolao.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.UsuarioDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Usuario;

import static org.hibernate.criterion.Restrictions.eq;

@Component
@Transactional
public class UsuarioBusiness extends BusinessGeneric<UsuarioDAO, Usuario> {

	public Usuario realizarLogin(String email, String senha) {
		return dao.findByCriteriaReturnUniqueResult(eq("email", email), eq("senha", senha));
	}

	public Usuario getByEmail(String email) {
		return dao.findByCriteriaReturnUniqueResult(eq("email", email));
	}

}