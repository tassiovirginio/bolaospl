package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDAO extends HibernateDAOGenerico<Usuario, Long> {}