package br.ufba.reuse.bolao.pages;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
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

@MountPath("signup")
public class SignUpPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	private Usuario usuario;

	public SignUpPage() {

		add(new FeedbackPanel("feedback"));

		usuario = new Usuario();

		add(new Link("LoginPage") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		});

		Form form = new Form("form") {
			protected void onSubmit() {

				Usuario usuarioExistente = usuarioBusiness.getByEmail(usuario.getEmail());
				
				if (usuarioExistente != null) {
					error("Usuário já cadastrado. Por favor, faça o login!");
				} else {
					usuario.setAdmin(false);
					usuarioBusiness.save(usuario);
					usuario = usuarioBusiness.realizarLogin(usuario.getEmail(), usuario.getSenha());
					setResponsePage(DetalhesUsuarioPage.class);
					getSession().setAttribute("usuario", usuario);
					setResponsePage(new DashboardPage());
				}
			};
		};

		form.add(new TextField<String>("nome", new PropertyModel<String>(usuario, "nome")));
		form.add(new TextField<String>("email", new PropertyModel<String>(usuario, "email")));
		form.add(new TextField<String>("celular", new PropertyModel<String>(usuario, "celular")));
		form.add(new PasswordTextField("senha", new PropertyModel<String>(usuario, "senha")));

		add(form);
	}

}
