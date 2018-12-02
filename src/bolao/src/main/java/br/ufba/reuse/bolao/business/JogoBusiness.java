package br.ufba.reuse.bolao.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.JogoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Jogo;

@Component
@Transactional
public class JogoBusiness extends BusinessGeneric<JogoDAO, Jogo> {}