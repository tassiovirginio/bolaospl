package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("signUp")
public class BolaoPage extends WebPage{
	private static final long serialVersionUID = 1L;

	public BolaoPage() {
		
//		private String login;
		
		Link realizarCadastro = new Link("realizarCadastro") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        };
        add(realizarCadastro);


	}

}