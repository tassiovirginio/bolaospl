package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.ApostaDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Time;
import br.ufba.reuse.bolao.entities.Usuario;


@Component
@Transactional
public class ApostaBusiness extends BusinessGeneric<ApostaDAO, Aposta> {
	
	/** Lista todas as apostas **/
	public List<Aposta> listAll() {
		
		List<Aposta> lista = dao.listAll();
		
		return lista;
	}
	
	/** Lista todas as apostas do usuário **/
	public List<Aposta> listApostasUsuario(Usuario user)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getDonoAposta().equals(user))
				lista.add(aposta);
		}
		
		return lista;
	}
	
	public List<Aposta> listApostasPorTime(Time time)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getTimeApostado().equals(time))
				lista.add(aposta);
		}
		
		return lista;
	}
	
	/** Lista aposta usando o filtro de usuário e de bolão **/	
	public Aposta listApostasUsuarioBolao(Usuario user, Bolao bolao)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getDonoAposta().equals(user) && aposta.getBolao().equals(bolao))
				return aposta;	
		}
		
		return null;	
	}
	
	public List<Aposta> listApostasDoBolao(Bolao bolao)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getBolao().equals(bolao))
				lista.add(aposta);
			
		}
		
		return lista;	
	}
	
	/** Lista apostas do jogo**/
	public List<Aposta> listApostasPorJogo(Jogo jogo)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getJogo().equals(jogo))
				lista.add(aposta);
			
		}
		
		return lista;	
	}
	
	
	/** Lista apostas com placar01 iguais**/
	public List<Aposta> getApostaPorPlacar01(int placar)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getPlacar01() == placar)
				lista.add(aposta);
		}
		
		return lista;	
	}
	
	/** Lista apostas com placar02 iguais**/
	public List<Aposta> getApostaPorPlacar02(int placar)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getPlacar02() == placar)
				lista.add(aposta);
		}
		
		return lista;	
	}
	
	/** Lista apostas com os dois placares**/
	public List<Aposta> getApostaPorPlacares(int placar01, int placar02)
	{
		List<Aposta> lista = dao.listAll();
		
		for(Aposta aposta: lista)
		{
			if(aposta.getPlacar02() == placar01 && aposta.getPlacar02() == placar02)
				lista.add(aposta);
		}
		
		return lista;	
	}
	
	
	
	
	
}
