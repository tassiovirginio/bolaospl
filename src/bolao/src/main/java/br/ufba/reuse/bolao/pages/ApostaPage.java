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
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("aposta")
public class ApostaPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");

	private String placar1;

	private String placar2;

	private Time vencedor;

	@SpringBean
	private ApostaBusiness apostaBusiness;

	@SpringBean
	private BolaoBusiness bolaoBusiness;

	public ApostaPage(Bolao bolao) {

		WebMarkupContainer image1 = new WebMarkupContainer("imgTime1");
		image1.add(new AttributeModifier("src", bolao.getJogo().getTime1().getImgUrl()));
		image1.add(new AttributeModifier("alt", bolao.getJogo().getTime1().getNome()));
		

		WebMarkupContainer image2 = new WebMarkupContainer("imgTime2");
		image2.add(new AttributeModifier("src", bolao.getJogo().getTime2().getImgUrl()));
		image2.add(new AttributeModifier("alt", bolao.getJogo().getTime2().getNome()));

		Form form = new Form("form"){
			@Override
			protected void onSubmit() {
				Aposta aposta = new Aposta();
				aposta.setApostador(usuarioLogado);
				aposta.setBolao(bolao);
				aposta.setJogo(bolao.getJogo());
				aposta.setPlacar01(Integer.parseInt(placar1));
				aposta.setPlacar02(Integer.parseInt(placar2));
				aposta.setTimeApostado(vencedor);
				bolao.getApostas().add(aposta);
				apostaBusiness.save(aposta);
				bolaoBusiness.save(bolao);
				setResponsePage(new DashboardPage());
			}
		};


		form.add(image1);
		form.add(image2);

		form.add(new TextField<String>("placar1", new PropertyModel<String>(this, "placar1")));
		form.add(new TextField<String>("placar2", new PropertyModel<String>(this, "placar2")));

		List<Time> timesJogo = new ArrayList<>();
		timesJogo.add(bolao.getJogo().getTime1());
		timesJogo.add(bolao.getJogo().getTime2());
		
		ChoiceRenderer<Time> choice = new ChoiceRenderer<Time>("nome", "id");
		DropDownChoice<Time> choiceTime = new DropDownChoice<Time>("vencedor", new PropertyModel<Time>(this,"vencedor"), timesJogo, choice);
		choiceTime.setOutputMarkupId(true);
		choiceTime.setRequired(true);

		form.add(choiceTime);

		add(form);

	}

}
