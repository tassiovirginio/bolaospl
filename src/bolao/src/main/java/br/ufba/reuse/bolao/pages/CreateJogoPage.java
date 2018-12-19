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
import br.ufba.reuse.bolao.business.TimeBusiness;
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
	

	private Time timeSelecionado;
	private Time timeSelecionado2;
	private Time vencedor;

	private String nome;
	private String data;
	private String time1;
	private String time2;
	private String placar1;
	private String placar2;
	
	

	public CreateJogoPage() {

		add(new FeedbackPanel("feedback"));
		
		Form form = new Form("form") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
		
			};
		};
		
		List<Time> times = timeBusiness.listAll();
		
		ChoiceRenderer<Time> choiceRendererTime = new ChoiceRenderer<Time>("nome", "id");
		DropDownChoice<Time> choiceTime = new DropDownChoice<Time>("choiceTime1",
				new PropertyModel<Time>(this, "timeSelecionado"), times, choiceRendererTime);
		choiceTime.setOutputMarkupId(true);
		choiceTime.setRequired(true);
		
		ChoiceRenderer<Time> choiceRendererTime2 = new ChoiceRenderer<Time>("nome", "id");
		DropDownChoice<Time> choiceTime2 = new DropDownChoice<Time>("choiceTime2",
				new PropertyModel<Time>(this, "timeSelecionado2"), times, choiceRendererTime2);
		choiceTime.setOutputMarkupId(true);
		choiceTime.setRequired(true);
		
		ChoiceRenderer<Time> choiceRenderervencedor = new ChoiceRenderer<Time>("nome", "id");
		DropDownChoice<Time> choicevencedor = new DropDownChoice<Time>("vencedor",
				new PropertyModel<Time>(this, "vencedor"), times, choiceRenderervencedor);
		choiceTime.setOutputMarkupId(true);
		//choiceTime.setRequired(true);

		form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));
		form.add(new TextField<String>("data", new PropertyModel<String>(this, "data")));
		//form.add(new TextField<String>("time1", new PropertyModel<String>(this, "time1")));
		//form.add(new TextField<String>("time2", new PropertyModel<String>(this, "time2")));
		form.add(new TextField<String>("placar1", new PropertyModel<String>(this, "placar1")));
		form.add(new TextField<String>("placar2", new PropertyModel<String>(this, "placar2")));
		//form.add(new TextField<String>("vencedor", new PropertyModel<String>(this, "vencedor")));
		form.add(choiceTime);
		form.add(choiceTime2);
		form.add(choicevencedor);
		add(form);	

	}

}