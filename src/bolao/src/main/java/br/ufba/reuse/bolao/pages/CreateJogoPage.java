package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.JogoBusiness;
import br.ufba.reuse.bolao.business.TimeBusiness;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Time;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("createjogo")
public class CreateJogoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	@SpringBean
	private CampeonatoBusiness campeonatoBusiness;
	
	@SpringBean
	private TimeBusiness timeBusiness;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;
	
	@SpringBean
	private JogoBusiness jogoBusiness;
	

	private Time timeSelecionado;
	private Time timeSelecionado2;
	private Time vencedor;
	private Campeonato campeonatoSelecionado;

	private String nome;
	private String data;
	private String time1;
	private String time2;
	private String placar1;
	private String placar2;
	
	

	public CreateJogoPage() {

		add(new FeedbackPanel("feedback"));
		
		
		
		List<Time> times = timeBusiness.listAll();
		List<Campeonato> campeonato = campeonatoBusiness.listAll();
		
		ChoiceRenderer<Campeonato> choiceRendererCampeonato = new ChoiceRenderer<Campeonato>("nome", "id");
		final DropDownChoice<Campeonato> choiceCampeonato = new DropDownChoice<Campeonato>("choiceCampeonato",
				new PropertyModel<Campeonato>(this, "campeonatoSelecionado"), campeonato, choiceRendererCampeonato);
		choiceCampeonato.setOutputMarkupId(true);
		choiceCampeonato.setRequired(true);
		
		ChoiceRenderer<Time> choiceRendererTime = new ChoiceRenderer<Time>("nome", "id");
		final DropDownChoice<Time> choiceTime = new DropDownChoice<Time>("choiceTime1",
				new PropertyModel<Time>(this, "timeSelecionado"), times, choiceRendererTime);
		choiceTime.setOutputMarkupId(true);
		choiceTime.setRequired(true);
		
		ChoiceRenderer<Time> choiceRendererTime2 = new ChoiceRenderer<Time>("nome", "id");
		final DropDownChoice<Time> choiceTime2 = new DropDownChoice<Time>("choiceTime2",
				new PropertyModel<Time>(this, "timeSelecionado2"), times, choiceRendererTime2);
		choiceTime.setOutputMarkupId(true);
		choiceTime.setRequired(true);
		
		ChoiceRenderer<Time> choiceRenderervencedor = new ChoiceRenderer<Time>("nome", "id");
		final DropDownChoice<Time> choicevencedor = new DropDownChoice<Time>("vencedor",
				new PropertyModel<Time>(this, "vencedor"), times, choiceRenderervencedor);
		choiceTime.setOutputMarkupId(true);
		//choiceTime.setRequired(true);
		
		Form form = new Form("form") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				Jogo jogo = new Jogo();
				
				jogo.setPlacar1(Integer.parseInt(placar1));
				jogo.setPlacar2(Integer.parseInt(placar2));
				//jogo.setData(data);
				choiceTime.render();
				choiceTime2.render();
				choicevencedor.render();
				choiceCampeonato.render();
				
				jogo.setCampeonato(campeonatoSelecionado);
				jogo.setTime1(timeSelecionado);
				jogo.setTime2(timeSelecionado2);
				jogo.setVencedor(vencedor);
				
				jogoBusiness.save(jogo);
				
				setResponsePage(new JogosPage());
				
			};
		};

		//form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));
		form.add(new TextField<String>("data", new PropertyModel<String>(this, "data")));
		//form.add(new TextField<String>("time1", new PropertyModel<String>(this, "time1")));
		//form.add(new TextField<String>("time2", new PropertyModel<String>(this, "time2")));
		form.add(new TextField<String>("placar1", new PropertyModel<String>(this, "placar1")));
		form.add(new TextField<String>("placar2", new PropertyModel<String>(this, "placar2")));
		//form.add(new TextField<String>("vencedor", new PropertyModel<String>(this, "vencedor")));
		form.add(choiceTime);
		form.add(choiceTime2);
		form.add(choicevencedor);
		form.add(choiceCampeonato);
		add(form);	

	}

}