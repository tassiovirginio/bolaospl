package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Time;
import org.springframework.stereotype.Component;

@Component
public class TimeDAO extends HibernateDAOGenerico<Time, Long> {}