package br.ufba.reuse.bolao.business;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.ApostaDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Time;
import br.ufba.reuse.bolao.entities.Usuario;

import static org.hibernate.criterion.Restrictions.eq;

@Component
@Transactional
public class ApostaBusiness extends BusinessGeneric<ApostaDAO, Aposta> {

	public List<Aposta> listApostasUsuario(Usuario user) {
		return dao.findByCriteriaReturnList(eq("apostador", user));
	}

	//#ifdef TimeWinner
	public List<Aposta> listApostasPorTime(Time time) {
		return dao.findByCriteriaReturnList(eq("timeApostado", time));
	}
	//#endif

	public List<Aposta> listApostasUsuarioBolao(Usuario user, Bolao bolao) {
		return dao.findByCriteriaReturnList(eq("bolao", bolao),eq("apostador",user));
	}

	public List<Aposta> listApostasDoBolao(Bolao bolao) {
		return dao.findByCriteriaReturnList(eq("bolao", bolao));
	}

	public List<Aposta> listApostasDoBolaoOrderByPontos(Bolao bolao) {
		return dao.findByCriteria(Order.desc("pontos"),eq("bolao", bolao));
	}

	public List<Aposta> listApostasPorJogo(Jogo jogo) {
		return dao.findByCriteriaReturnList(eq("jogo", jogo));
	}

	public Long pontosTotais() {
		return dao.sumColunm("pontos");
	}



}
