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

@MountPath("group")
public class CreateGrupoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	@SpringBean
	private GrupoBusiness grupoBusiness;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	@SpringBean
	private BolaoBusiness bolaoBusiness;

	private Grupo grupoSelecionado;

	private Usuario usuarioSelecionado;

	public CreateGrupoPage(WebPage paginaAnterior) {
		this(null,paginaAnterior);
	}

	public CreateGrupoPage(Grupo grupo, WebPage paginaAnterior) {

		add(new Link("linkVoltar"){
			@Override
			public void onClick() {
				setResponsePage(paginaAnterior);
			}
		});

		grupoSelecionado = grupo;
		if (grupoSelecionado == null) {
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
				setResponsePage(new CreateBolaoPage(grupoSelecionado,CreateGrupoPage.this));
			}
		};
		add(linkCreateBolao);

		List<Usuario> integrantes = grupoSelecionado.getParticipantes();

		List<Usuario> listUsuario = usuarioBusiness.listAll();

		listUsuario.removeAll(integrantes);

		ChoiceRenderer<Usuario> choiceRendererJogo = new ChoiceRenderer<Usuario>("nome", "id");
		DropDownChoice<Usuario> choiceIntegrante = new DropDownChoice<Usuario>("choiceIntegrante",
				new PropertyModel<Usuario>(this, "usuarioSelecionado"), listUsuario, choiceRendererJogo);
		choiceIntegrante.setOutputMarkupId(true);
		choiceIntegrante.setRequired(true);

		Form formUsuario = new Form("formUsuario") {
			@Override
			protected void onSubmit() {
				grupoSelecionado.getParticipantes().add(usuarioSelecionado);
				listUsuario.remove(usuarioSelecionado);
				choiceIntegrante.render();
				grupoBusiness.save(grupoSelecionado);
			}
		};
		formUsuario.add(choiceIntegrante);

		add(formUsuario);

		if (integrantes == null)
			integrantes = new ArrayList<Usuario>();

		add(new ListView<Usuario>("listIntegrantes", integrantes) {
			@Override
			protected void populateItem(ListItem<Usuario> item) {
				Usuario usuario = item.getModelObject();
				item.add(new Label("nome", usuario.getNome()));
				Link linkRemover = new Link("remover") {
					@Override
					public void onClick() {
						grupoSelecionado.getParticipantes().remove(usuario);
						grupoBusiness.save(grupoSelecionado);
					}
				};
				item.add(linkRemover);
			}
		});

		List<Bolao> listaBolao = bolaoBusiness.listBy(grupoSelecionado);

		add(new ListView<Bolao>("listaBolao", listaBolao) {
			@Override
			protected void populateItem(ListItem<Bolao> item) {
				Bolao bolao = item.getModelObject();
				item.add(new Label("nome", bolao.getNome()));

				WebMarkupContainer image1 = new WebMarkupContainer("imgTime1");
				image1.add(new AttributeModifier("src", bolao.getJogo().getTime1().getImgUrl()));
				image1.add(new AttributeModifier("alt", bolao.getJogo().getTime1().getNome()));
				item.add(image1);

				WebMarkupContainer image2 = new WebMarkupContainer("imgTime2");
				image2.add(new AttributeModifier("src", bolao.getJogo().getTime2().getImgUrl()));
				image2.add(new AttributeModifier("alt", bolao.getJogo().getTime2().getNome()));
				item.add(image2);

				item.add(new Label("apostasSize", bolao.getApostas().size()));

				item.add(new Label("data", bolao.getJogo().getData()));

				item.add(new Label("vencedor", bolao.getJogo().getVencedor().getNome()));

				item.add(new Label("placar1", bolao.getJogo().getPlacar1()));

				item.add(new Label("placar2", bolao.getJogo().getPlacar2()));

				Link linkEditar = new Link("linkEditar") {
					@Override
					public void onClick() {
						setResponsePage(new CreateBolaoPage(bolao, grupoSelecionado, CreateGrupoPage.this));
					}
				};
				item.add(linkEditar);
			}
		});

	}

}