package br.ufba.reuse.bolao.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
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

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.JogoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("bolao")
public class CreateBolaoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	@SpringBean
	private BolaoBusiness bolaoBusiness;

	@SpringBean
	private JogoBusiness jogoBusiness;

	@SpringBean
	private ApostaBusiness apostaBusiness;

	@SpringBean
	private CampeonatoBusiness campeonatoBusiness;

	private Bolao bolaoSelecionado;

	private Grupo grupoDoBolao;

	private Campeonato campeonatoSelecionado;

	private Jogo jogoSelecionado;

	private DropDownChoice<Jogo> choiceJogo;

	private List<Jogo> jogos;

	public CreateBolaoPage(Grupo grupoDoBolao, WebPage paginaAnterior) {
		this(null, grupoDoBolao, paginaAnterior);
	}

	public CreateBolaoPage(Bolao bolao, Grupo grupoDoBolao, WebPage paginaAnterior) {

		add(new Link("linkVoltar"){
			@Override
			public void onClick() {
				setResponsePage(paginaAnterior);
			}
		});


		this.bolaoSelecionado = bolao;
		this.grupoDoBolao = grupoDoBolao;

		if (this.bolaoSelecionado == null) {
			this.bolaoSelecionado = new Bolao();
		}

		add(new FeedbackPanel("feedbackPanel"));

		List<Campeonato> campeonatos = campeonatoBusiness.listAll();

		ChoiceRenderer<Campeonato> choiceRenderer = new ChoiceRenderer<Campeonato>("nome", "id");
		DropDownChoice<Campeonato> choiceCampeonato = new DropDownChoice<Campeonato>("choiceCampeonato",
				new PropertyModel<Campeonato>(this, "campeonatoSelecionado"), campeonatos, choiceRenderer) {
			@Override
			protected void onSelectionChanged(Campeonato campeonato) {
				super.onSelectionChanged(campeonato);
				jogos = jogoBusiness.listBy(campeonato);
				choiceJogo.setChoices(jogos);
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}
		};
		choiceCampeonato.setOutputMarkupId(true);
		choiceCampeonato.setRequired(true);

		if (jogos == null) {
			jogos = new ArrayList<>();
		}

		ChoiceRenderer<Jogo> choiceRendererJogo = new ChoiceRenderer<Jogo>("nome", "id");
		choiceJogo = new DropDownChoice<Jogo>("choiceJogo", new PropertyModel<Jogo>(this, "jogoSelecionado"), jogos,
				choiceRendererJogo);
		choiceJogo.setOutputMarkupId(true);
		choiceJogo.setRequired(true);

		Form form = new Form("form") {
			protected void onSubmit() {
				bolaoSelecionado.setGrupo(grupoDoBolao);
				bolaoSelecionado.setCriacao(new Date());
				bolaoSelecionado.setJogo(jogoSelecionado);
				bolaoSelecionado.setCampeonato(campeonatoSelecionado);
				bolaoBusiness.save(bolaoSelecionado);
				setResponsePage(new CreateGrupoPage(grupoDoBolao,CreateBolaoPage.this));
			};
		};

		form.add(new TextField<String>("nome", new PropertyModel<>(bolaoSelecionado, "nome")).setRequired(true));

		DateTextField dateTextField = new DateTextField("dataFechamento", "dd/MM/yyyy");
		dateTextField.setRequired(true);
		dateTextField.setModel(new PropertyModel<>(bolaoSelecionado, "fechamento"));

		form.add(dateTextField);
		form.add(choiceCampeonato);
		form.add(choiceJogo);

		if(bolaoSelecionado.getJogo().getPlacar1() != null && bolaoSelecionado.getJogo().getPlacar2() != null){
			String resultado = bolaoSelecionado.getJogo().getPlacar1() + " x " + bolaoSelecionado.getJogo().getPlacar2();
			form.add(new Label("resultado", "Resultado: " + resultado + " - Ganhador: " + bolaoSelecionado.getJogo().getVencedor().getNome()));
		}else{
			form.add(new Label("resultado", "Jogo sem PLACAR FINAL !!!!"));
		}

		Link linkProcessarPontos = new Link("linkProcessarPontos") {
			@Override
			public void onClick() {
				bolaoBusiness.processarPontos(bolao);
				setResponsePage(new CreateBolaoPage(bolao,grupoDoBolao,paginaAnterior));
			}
		};
		form.add(linkProcessarPontos);

		Link linkApagarPontos = new Link("linkApagarPontos") {
			@Override
			public void onClick() {
				bolaoBusiness.apagarPontos(bolao);
				setResponsePage(new CreateBolaoPage(bolao,grupoDoBolao,paginaAnterior));
			}
		};
		form.add(linkApagarPontos);

		Link linkCancelar = new Link("linkCancelar") {
			@Override
			public void onClick() {
				setResponsePage(paginaAnterior);
			}
		};
		form.add(linkCancelar);

		add(form);

		if (this.bolaoSelecionado != null) {
			this.campeonatoSelecionado = bolao.getCampeonato();
			jogos = jogoBusiness.listBy(this.campeonatoSelecionado);
			choiceJogo.setChoices(jogos);
			this.jogoSelecionado = bolao.getJogo();
		}

		List<Aposta> listApostas = apostaBusiness.listApostasDoBolao(bolao);

		add(new ListView<Aposta>("listApostas", listApostas) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Aposta> item) {
				Aposta aposta = item.getModelObject();
				item.add(new Label("apostador", aposta.getApostador().getNome()));
				//#ifdef WinnerScore
				item.add(new Label("placar1", aposta.getPlacar01()));
				item.add(new Label("placar2", aposta.getPlacar02()));
				//#endif
				//#ifdef TimeWinner
				item.add(new Label("ganhador", aposta.getTimeApostado().getNome()));
				//#endif
				item.add(new Label("pontos", aposta.getPontos()));
			}
		});

	}

}