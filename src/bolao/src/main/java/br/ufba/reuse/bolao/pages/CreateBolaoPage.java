package br.ufba.reuse.bolao.pages;

import java.util.Date;
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
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("bolao")
public class CreateBolaoPage extends BasePage {
    private static final long serialVersionUID = 1L;
    
    final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

    @SpringBean
    private BolaoBusiness bolaoBusiness;
    
	private Bolao bolaoSelecionado;

	private Grupo grupoDoBolao;

	public CreateBolaoPage(Grupo grupoDoBolao) {
		this(null,grupoDoBolao);
	}

    public CreateBolaoPage(Bolao bolao,Grupo grupoDoBolao) {
		this.bolaoSelecionado = bolao;
		this.grupoDoBolao = grupoDoBolao;

		if(bolao == null){
			bolao = new Bolao();
		}

    	Form form = new Form("form") {
			protected void onSubmit() {
				bolaoSelecionado.setGrupo(grupoDoBolao);
				bolaoSelecionado.setCriacao(new Date());
				bolaoBusiness.save(bolaoSelecionado);
				setResponsePage(new CreateBolaoPage(bolaoSelecionado,grupoDoBolao));
			};
		};
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(bolaoSelecionado, "nome")));
    	
		add(form);
    	
    }

}