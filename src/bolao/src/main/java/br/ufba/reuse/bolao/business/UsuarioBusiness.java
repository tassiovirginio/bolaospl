package br.ufba.reuse.bolao.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.UsuarioDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Usuario;

@Component
@Transactional
public class UsuarioBusiness extends BusinessGeneric<UsuarioDAO, Usuario> {}