package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Campeonato;
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

				if (campeonato == null) {
					Campeonato c = new Campeonato();
					c.setNome(nome);
					campeonatoBusiness.save(c);
					setResponsePage(new CampeonatoPage());
				} else {
					error("Campeonato já cadastrado!");
				}
					

			};
		};

		form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));

		add(form);

	}

}