package br.ufba.reuse.bolao.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;

@MountPath("recuperar")
public class RecuperarPage extends WebPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;
    
    private String email;

    public RecuperarPage() {
    	
    	add(new Link("linkLoginPage") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		});
    	
    	add(new FeedbackPanel("feedback"));
    	
    	Form form = new Form("form") {
			protected void onSubmit() {
				error("Feature ainda não implementada!!!");
			}
    	};
    	
    	add(form);
    	
    	form.add(new TextField<String>("email", new PropertyModel<String>(this, "email")));
    	
    }

}
