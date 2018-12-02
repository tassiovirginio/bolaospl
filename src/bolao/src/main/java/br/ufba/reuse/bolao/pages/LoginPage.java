package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Usuario;

@MountPath("login")
public class LoginPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	private String email;

	private String senha;

	public LoginPage() {
		
		add(new FeedbackPanel("feedback"));
		
		add(new Link("linkRecuperar") {
            @Override
            public void onClick() {
                setResponsePage(new RecuperarPage());
            }
    	});
		
		add(new Link("SignUp") {
            @Override
            public void onClick() {
                setResponsePage(new SignUpPage());
            }
    	});
		
		Form form = new Form("form") {
			protected void onSubmit() {
				System.out.println("loginForm: " + email + " - " + senha);
				
				Usuario usuarioLogado = usuarioBusiness.realizarLogin(email, senha);
				
				if(usuarioLogado != null) {
					setResponsePage(DetalhesUsuarioPage.class);
					getSession().setAttribute("usuario", usuarioLogado);
                    setResponsePage(new ListGrupoPage());
                } else {
                    error("Login Inválido");
				}
			};
		};
		
		form.add(new TextField<String>("email",new PropertyModel<String>(this, "email")));

		form.add(new PasswordTextField("senha",new PropertyModel<String>(this, "senha")));
		
		add(form);

	}

}
