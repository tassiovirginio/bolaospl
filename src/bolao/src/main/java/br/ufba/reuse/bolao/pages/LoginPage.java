package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("login")
public class LoginPage extends WebPage{
	private static final long serialVersionUID = 1L;

	public LoginPage() {
		
//		private String login;
		
		Link realizarLogin = new Link("realizarLogin") {
            @Override
            public void onClick() {
                setResponsePage(Pagina01.class);
            }
        };
        add(realizarLogin);


	}

}
