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

		Usuario usuarioRetorno = dao.findByCriteriaReturnUniqueResult(eq("email", email), eq("senha", senha));

		System.out.println("email: " + email + " - senha: " + senha + " - usuario: " + usuarioRetorno);

		return usuarioRetorno;
	
	}
	
	public Usuario encontraUsuarioCadastrado(String email)
	{
		Usuario usuarioRetorno = dao.findByCriteriaReturnUniqueResult(eq("email", email));
		return usuarioRetorno;
	}
	
	public Usuario getByEmail(String email) {
		return dao.findByCriteriaReturnUniqueResult(eq("email", email));
	}

}