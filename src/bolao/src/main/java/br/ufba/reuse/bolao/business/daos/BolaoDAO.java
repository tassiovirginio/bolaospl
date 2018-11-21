package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Bolao;
import org.springframework.stereotype.Component;

@Component
public class BolaoDAO extends HibernateDAOGenerico<Bolao, Long> {}