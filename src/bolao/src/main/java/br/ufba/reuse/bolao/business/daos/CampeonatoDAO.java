package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Campeonato;
import org.springframework.stereotype.Component;

@Component
public class CampeonatoDAO extends HibernateDAOGenerico<Campeonato, Long> {}