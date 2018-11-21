package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.BolaoDAO;
import br.ufba.reuse.bolao.entities.Bolao;

@Component
@Transactional
public class BolaoBusiness {

    @Autowired
    private BolaoDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Bolao candidato){
        dao.save(candidato);
    }

    public List<Bolao> listAll(){
        return dao.listAll();
    }

    public Bolao findById(Long id){
        return dao.findById(id);
    }

}
