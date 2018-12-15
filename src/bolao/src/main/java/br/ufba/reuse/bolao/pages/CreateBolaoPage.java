package br.ufba.reuse.bolao.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
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
import br.ufba.reuse.bolao.business.JogoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
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
	private CampeonatoBusiness campeonatoBusiness;
    
	private Bolao bolaoSelecionado;

	private Grupo grupoDoBolao;

	private Campeonato campeonatoSelecionado;

	private Jogo jogoSelecionado;

	private DropDownChoice<Jogo> choiceJogo;

	private List<Jogo> jogos;

	public CreateBolaoPage(Grupo grupoDoBolao) {
		this(null,grupoDoBolao);
	}

    public CreateBolaoPage(Bolao bolao,Grupo grupoDoBolao) {
		this.bolaoSelecionado = bolao;
		this.grupoDoBolao = grupoDoBolao;

		if(this.bolaoSelecionado == null){
			this.bolaoSelecionado = new Bolao();
		}

		add(new FeedbackPanel("feedbackPanel"));

		List<Campeonato> campeonatos = campeonatoBusiness.listAll();

		ChoiceRenderer<Campeonato> choiceRenderer = new ChoiceRenderer<Campeonato>("nome", "id");
		DropDownChoice<Campeonato> choiceCampeonato = new DropDownChoice<Campeonato>("choiceCampeonato", new PropertyModel<Campeonato>(this,"campeonatoSelecionado"), campeonatos, choiceRenderer){
			@Override
			protected void onSelectionChanged(Campeonato campeonato) {
				super.onSelectionChanged(campeonato);
				jogos = jogoBusiness.listBy(campeonato);
				System.out.println(campeonato.getNome());
				System.out.println(campeonato.getJogos().size());
				choiceJogo.setChoices(jogos);
			}
			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}
		};
		choiceCampeonato.setOutputMarkupId(true);
		choiceCampeonato.setRequired(true);

		if(jogos == null){
			jogos = new ArrayList<>();
		}
		
		ChoiceRenderer<Jogo> choiceRendererJogo = new ChoiceRenderer<Jogo>("nome", "id");
		choiceJogo = new DropDownChoice<Jogo>("choiceJogo", new PropertyModel<Jogo>(this,"jogoSelecionado"), jogos, choiceRendererJogo);
		choiceJogo.setOutputMarkupId(true);
		choiceJogo.setRequired(true);


    	Form form = new Form("form") {
			protected void onSubmit() {
				bolaoSelecionado.setGrupo(grupoDoBolao);
				bolaoSelecionado.setCriacao(new Date());
				bolaoSelecionado.setJogo(jogoSelecionado);
				bolaoSelecionado.setCampeonato(campeonatoSelecionado);
				bolaoBusiness.save(bolaoSelecionado);
				setResponsePage(new CreateBolaoPage(bolaoSelecionado,grupoDoBolao));
			};
		};
    	
		form.add(new TextField<String>("nome", new PropertyModel<>(bolaoSelecionado, "nome")).setRequired(true));
		

		DateTextField dateTextField = new DateTextField("dataFechamento", "dd/MM/yyyy");
		dateTextField.setRequired(true);
		dateTextField.setModel(new PropertyModel<>(bolaoSelecionado, "fechamento"));

		form.add(dateTextField);
		form.add(choiceCampeonato);
		form.add(choiceJogo);
		
		add(form);
    	
    }

}