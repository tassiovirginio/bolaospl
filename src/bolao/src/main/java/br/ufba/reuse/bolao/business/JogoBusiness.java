package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.JogoDAO;
import br.ufba.reuse.bolao.entities.Jogo;

@Component
@Transactional
public class JogoBusiness {

    @Autowired
    private JogoDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Jogo candidato){
        dao.save(candidato);
    }

    public List<Jogo> listAll(){
        return dao.listAll();
    }

    public Jogo findById(Long id){
        return dao.findById(id);
    }

}
