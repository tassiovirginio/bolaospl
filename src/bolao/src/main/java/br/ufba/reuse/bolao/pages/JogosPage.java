package br.ufba.reuse.bolao.pages;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.JogoBusiness;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Time;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("games")
public class JogosPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
	
	@SpringBean
	private CampeonatoBusiness campeonatoBusiness;

	@SpringBean
	private JogoBusiness jogoBusiness;

	public JogosPage() {

		List<Jogo> lista = jogoBusiness.listAll();

		add(new ListView<Jogo>("list", lista) {
			@Override
			protected void populateItem(ListItem<Jogo> item) {
				Jogo jogo = item.getModelObject();
				item.add(new Label("nome", jogo.getTime1().getNome() + " x " + jogo.getTime2().getNome()));
				item.add(new Label("data", jogo.getData()));
			}
		});



	}

}
