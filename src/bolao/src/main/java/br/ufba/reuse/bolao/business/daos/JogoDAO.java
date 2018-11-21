package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Jogo;
import org.springframework.stereotype.Component;

@Component
public class JogoDAO extends HibernateDAOGenerico<Jogo, Long> {}