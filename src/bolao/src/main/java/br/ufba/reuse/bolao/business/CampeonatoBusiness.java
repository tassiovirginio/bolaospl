package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.CampeonatoDAO;
import br.ufba.reuse.bolao.entities.Campeonato;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

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

    public Grupo findById(Long id){
        return dao.findById(id);
    }

}
