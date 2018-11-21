package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.CampeonatoDAO;
import br.ufba.reuse.bolao.entities.Campeonato;

@Component
@Transactional
public class CampeonatoBusiness {

    @Autowired
    private CampeonatoDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Campeonato candidato){
        dao.save(candidato);
    }

    public List<Campeonato> listAll(){
        return dao.listAll();
    }

    public Campeonato findById(Long id){
        return dao.findById(id);
    }

}
