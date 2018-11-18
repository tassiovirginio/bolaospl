package br.ufba.reuse.bolao.business;

import br.ufba.reuse.bolao.business.daos.UsuarioDAO;
import br.ufba.reuse.bolao.entities.Usuario;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Component
@Transactional
public class UsuarioBusiness {

    @Autowired
    private UsuarioDAO dao;

    public int size(){
        return dao.size();
    }

    public void save(Usuario candidato){
        dao.save(candidato);
    }

    public List<Usuario> listAll(){
        return dao.listAll();
    }

    public Usuario findById(Long id){
        return dao.findById(id);
    }

}
