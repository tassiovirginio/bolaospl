package br.ufba.reuse.bolao.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.BolaoDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Jogo;

@Component
@Transactional
public class BolaoBusiness extends BusinessGeneric<BolaoDAO, Bolao> {


	@Autowired
	private ApostaBusiness apostaBusiness;

	public List<Bolao> listAll() {
		List<Bolao> lista = dao.listAll();
		for (Bolao bolao : lista) {
			bolao.getApostas().size();
		}
        return lista;
	}

	public List<Bolao> listBy(Grupo grupo) {
		List<Bolao> lista = dao.listAll();
		List<Bolao> listaResult = new ArrayList<>();
		for (Bolao bolao : lista) {
			if(bolao.getGrupo().equals(grupo)){
				bolao.getApostas().size();
				listaResult.add(bolao);
			}
		}
        return listaResult;
	}

	public void processarPontos(Bolao bolao){
		Jogo jogo = bolao.getJogo();
		List<Aposta> apostas = bolao.getApostas();
		for(Aposta aposta:apostas){
			if(aposta.getPlacar01().equals(jogo.getPlacar1())){
				aposta.setPontos(aposta.getPontos() + 5);
			}
			if(aposta.getPlacar02().equals(jogo.getPlacar2())){
				aposta.setPontos(aposta.getPontos() + 5);
			}
			if(aposta.getTimeApostado().equals(jogo.getVencedor())){
				aposta.setPontos(aposta.getPontos() + 5);
			}
			apostaBusiness.save(aposta);
		}
		bolao.setProcessado(new Date());
		save(bolao);
	}

	public void apagarPontos(Bolao bolao){
		List<Aposta> apostas = bolao.getApostas();
		for(Aposta aposta:apostas){
			aposta.setPontos(0);
			apostaBusiness.save(aposta);
		}
		bolao.setProcessado(null);
		save(bolao);
	}
	
}
