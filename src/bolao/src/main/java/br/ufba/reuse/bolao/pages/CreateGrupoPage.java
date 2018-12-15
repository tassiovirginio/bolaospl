package br.ufba.reuse.bolao.pages;

import java.util.List;

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

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("grupo")
public class CreateGrupoPage extends BasePage {
    private static final long serialVersionUID = 1L;
    
    final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

    @SpringBean
	private GrupoBusiness grupoBusiness;
	
	@SpringBean
	private UsuarioBusiness usuarioBusiness;
    
	private Grupo grupoSelecionado;

	private Usuario usuarioSelecionado;

	public CreateGrupoPage() {
		this(null);
	}


    public CreateGrupoPage(Grupo grupo) {
		grupoSelecionado = grupo;

		if(grupoSelecionado == null){
			grupoSelecionado = new Grupo();
		}

    	Form form = new Form("form") {
			protected void onSubmit() {
				grupoSelecionado.setDono(usuarioLogado);
				grupoBusiness.save(grupoSelecionado);
				setResponsePage(new DashboardPage());
			};
		};
		
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(grupoSelecionado, "nome")));

		form.add(new TextField<String>("descricao", new PropertyModel<String>(grupoSelecionado, "descricao")));
    	
		add(form);


		Link linkCreateBolao = new Link("linkCreateBolao") {
			@Override
			public void onClick() {
				setResponsePage(new CreateBolaoPage(grupoSelecionado));
			}
		};
		add(linkCreateBolao);


		List<Usuario> listUsuario = usuarioBusiness.listAll();

		ChoiceRenderer<Usuario> choiceRendererJogo = new ChoiceRenderer<Usuario>("nome", "id");
		DropDownChoice<Usuario> choiceIntegrante = new DropDownChoice<Usuario>("choiceIntegrante", new PropertyModel<Usuario>(this,"usuarioSelecionado"), listUsuario, choiceRendererJogo);
		choiceIntegrante.setOutputMarkupId(true);
		choiceIntegrante.setRequired(true);

		Form formUsuario = new Form("formUsuario");
		formUsuario.add(choiceIntegrante);

		add(formUsuario);

		List<Usuario> integrantes = grupo.getParticipantes();

		add(new ListView<Usuario>("listIntegrantes", integrantes) {
			@Override
			protected void populateItem(ListItem<Usuario> item) {
				Usuario usuario = item.getModelObject();
				item.add(new Label("nome", usuario.getNome()));
			}
        });


    	
    }

}