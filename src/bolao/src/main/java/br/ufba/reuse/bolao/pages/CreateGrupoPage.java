package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("criagrupos")
public class CreateGrupoPage extends BasePage {
    private static final long serialVersionUID = 1L;
    
    final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

    @SpringBean
    private GrupoBusiness grupoBusiness;
    
    private String nome;

	private String descricao;

    public CreateGrupoPage() {
    	
    	Form form = new Form("form") {
			protected void onSubmit() {
				Grupo gr = new Grupo();
				
				gr.setNome(nome);
				gr.setDescricao(descricao);
				gr.setDono(usuarioLogado);
				
				grupoBusiness.save(gr);
				
				setResponsePage(DetalhesUsuarioPage.class);
				setResponsePage(new ListGrupoPage());
				
			};
    	};
        	
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));

		form.add(new TextField<String>("descricao", new PropertyModel<String>(this, "descricao")));
    	
		add(form);
    	
    }

}