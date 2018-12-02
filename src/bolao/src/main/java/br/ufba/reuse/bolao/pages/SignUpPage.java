package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;

@MountPath("signUp")
public class SignUpPage extends WebPage{
	private static final long serialVersionUID = 1L;
	
	public SignUpPage() {
		
		Link realizarCadastro = new Link("realizarCadastro") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        };
        add(realizarCadastro);


	}

}
