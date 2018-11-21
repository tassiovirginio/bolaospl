package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.BolaoDAO;
import br.ufba.reuse.bolao.entities.Bolao;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

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
