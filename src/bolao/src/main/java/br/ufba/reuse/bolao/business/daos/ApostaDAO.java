package br.ufba.reuse.bolao.business.daos;

import br.ufba.reuse.bolao.business.daos.util.DAOGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import org.springframework.stereotype.Component;

@Component
public class ApostaDAO extends DAOGeneric<Aposta, Long> {}