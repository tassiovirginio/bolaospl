package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.TimeDAO;
import br.ufba.reuse.bolao.entities.Time;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Component
@Transactional
public class TimeBusiness {

    @Autowired
    private TimeDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Time candidato){
        dao.save(candidato);
    }

    public List<Time> listAll(){
        return dao.listAll();
    }

    public Time findById(Long id){
        return dao.findById(id);
    }

}
