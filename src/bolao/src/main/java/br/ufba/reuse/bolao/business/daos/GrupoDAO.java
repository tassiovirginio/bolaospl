package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Grupo;
import org.springframework.stereotype.Component;

@Component
public class GrupoDAO extends HibernateDAOGenerico<Grupo, Long> {}