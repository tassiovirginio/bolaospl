package br.ufba.reuse.bolao.pages;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
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

@MountPath("rankingsweepstake")
public class RankinBolaoPage extends BasePage {
	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	@SpringBean
	private ApostaBusiness apostaBusiness;

	@SpringBean
	private BolaoBusiness bolaoBusiness;

	public RankinBolaoPage(Bolao bolao) {

		List<Aposta> lista = apostaBusiness.listApostasDoBolaoOrderByPontos(bolao);

		add(new Label("bolao", bolao.getNome()));

		add(new ListView<Aposta>("listApostas",lista) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Aposta> item) {
				Aposta aposta = item.getModelObject();
				item.add(new Label("nome", aposta.getApostador().getNome()));
				item.add(new Label("pontos", aposta.getPontos()));
			}
		});

	}

}
