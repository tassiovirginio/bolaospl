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

@MountPath("signUp")
public class SignUpPage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	private String nome;

	private String email;

	private String senha;

	private Date dataNascimento;
	
	private String celular;

	public SignUpPage() {

		add(new FeedbackPanel("feedback"));

		add(new Link("LoginPage") {
			@Override
			public void onClick() {

				setResponsePage(new LoginPage());
			}
		});

		Form form = new Form("form") {
			protected void onSubmit() {

				Usuario usuarioLogado = usuarioBusiness.getByEmail(email);

				if (usuarioLogado != null) {
					error("Usuário já cadastrado. Por favor, faça o login!");
					nome = null;
					email = null;
					senha = null;
					dataNascimento = null;
					celular = null;
				} else {
					Usuario user = new Usuario();

					user.setNome(nome);
					user.setEmail(email);
					user.setSenha(senha);
					user.setCelular(celular);
					
					usuarioBusiness.save(user);

					Usuario usuario = usuarioBusiness.realizarLogin(email, senha);
					
					setResponsePage(Pagina01.class);
					getSession().setAttribute("usuario", usuario);
					setResponsePage(new Pagina01());
				}
			};
		};

		form.add(new TextField<String>("nome", new PropertyModel<String>(this, "nome")));

		form.add(new TextField<String>("email", new PropertyModel<String>(this, "email")));
		
		form.add(new TextField<String>("celular", new PropertyModel<String>(this, "celular")));

		form.add(new PasswordTextField("senha", new PropertyModel<String>(this, "senha")));

		form.add(new DateTextField("dataNascimento", new PropertyModel<Date>(this, "dataNascimento")));

		add(form);
	}

}
