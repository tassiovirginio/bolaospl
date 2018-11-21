package br.ufba.reuse.bolao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.UsuarioDAO;
import br.ufba.reuse.bolao.entities.Usuario;

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
