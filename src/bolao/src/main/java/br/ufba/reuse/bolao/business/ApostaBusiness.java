package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.ApostaDAO;
import br.ufba.reuse.bolao.entities.Aposta;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Component
@Transactional
public class ApostaBusiness {

    @Autowired
    private ApostaDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Aposta candidato){
        dao.save(candidato);
    }

    public List<Aposta> listAll(){
        return dao.listAll();
    }

    public Aposta findById(Long id){
        return dao.findById(id);
    }

}
