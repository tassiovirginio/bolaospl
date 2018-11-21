package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.HibernateDAOGenerico;
import br.ufba.reuse.bolao.entities.Aposta;
import org.springframework.stereotype.Component;

@Component
public class ApostaDAO extends HibernateDAOGenerico<Aposta, Long> {}