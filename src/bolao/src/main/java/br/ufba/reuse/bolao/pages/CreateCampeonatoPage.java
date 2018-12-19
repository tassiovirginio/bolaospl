package br.ufba.reuse.bolao.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("createcampeonato")
public class CreateCampeonatoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	@SpringBean
	private CampeonatoBusiness campeonatoBusiness;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	private String nome;

	public CreateCampeonatoPage() {

		add(new FeedbackPanel("feedback"));
		
		Form form = new Form("form") {
			protected void onSubmit() {
						
				Campeonato campeonato = campeonatoBusiness.findCampeonato(nome);
				
				if(campeonato == null)
				{
					Campeonato c = new Campeonato();
					c.setNome(nome);
					campeonatoBusiness.save(c);
					setResponsePage(new DashboardPage());
				}
				else
					error("Campeonato já cadastrado!");
		
			};
		};

		form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));

		add(form);	

	}

}