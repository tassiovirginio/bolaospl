package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.GrupoDAO;
import br.ufba.reuse.bolao.entities.Grupo;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Component
@Transactional
public class GrupoBusiness {

    @Autowired
    private GrupoDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Grupo candidato){
        dao.save(candidato);
    }

    public List<Grupo> listAll(){
        return dao.listAll();
    }

    public Grupo findById(Long id){
        return dao.findById(id);
    }

}
