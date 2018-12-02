package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.ApostaDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Grupo;


@Component
@Transactional
public class ApostaBusiness extends BusinessGeneric<ApostaDAO, Aposta> {}
