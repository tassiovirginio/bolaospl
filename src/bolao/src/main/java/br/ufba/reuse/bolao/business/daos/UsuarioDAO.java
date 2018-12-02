package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.DAOGeneric;
import br.ufba.reuse.bolao.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDAO extends DAOGeneric<Usuario, Long> {}