package br.ufba.reuse.bolao.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.GrupoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Grupo;

@Component
@Transactional
public class GrupoBusiness extends BusinessGeneric<GrupoDAO, Grupo> {}
