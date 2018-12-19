package br.ufba.reuse.bolao.pages;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;
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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("championships")
public class CampeonatoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
	
	@SpringBean
	private CampeonatoBusiness campeonatoBusiness;

	public CampeonatoPage() {

		List<Campeonato> lista = campeonatoBusiness.listAll();

		add(new ListView<Campeonato>("list", lista) {
			@Override
			protected void populateItem(ListItem<Campeonato> item) {
				Campeonato campeonato = item.getModelObject();
				item.add(new Label("nome", campeonato.getNome()));
				// Link linkRemover = new Link("remover") {
				// 	@Override
				// 	public void onClick() {
				// 		grupoSelecionado.getParticipantes().remove(usuario);
				// 		grupoBusiness.save(grupoSelecionado);
				// 	}
				// };
				// item.add(linkRemover);
			}
		});
		
		add(new Link("addNovoCampeonato") {
            @Override
            public void onClick() {
                setResponsePage(new CreateCampeonatoPage());
            }
    	});


	}

}
