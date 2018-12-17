package br.ufba.reuse.bolao.pages.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.LoginPage;
import br.ufba.reuse.bolao.pages.DashboardPage;
import br.ufba.reuse.bolao.pages.DetalhesUsuarioPage;
import br.ufba.reuse.bolao.pages.Pagina02;
import br.ufba.reuse.bolao.pages.RankingPage;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    public BasePage(){
    	
    	Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
        
        Link linkDetalhes = new Link("linkDetalhes") {
            @Override
            public void onClick() {
                setResponsePage(new DetalhesUsuarioPage(usuarioLogado));
            }
        };
        
        linkDetalhes.add(new Label("usuarioLogado",usuarioLogado.getNome()));

        add(linkDetalhes);
    	
    	//#ifdef Ranking
    	add(new Link("linkRanking") {
            @Override
            public void onClick() {
                setResponsePage(new RankingPage());
            }
    	});
    	//#endif
    	
    	add(new Link("linkDashboard") {
            @Override
            public void onClick() {
                setResponsePage(new DashboardPage());
            }
    	});
    	
    	
    	
    	add(new Link("linkLogout") {
            @Override
            public void onClick() {
                getSession().clear();
                getSession().invalidateNow();
                setResponsePage(new LoginPage());
            }
    	});

    }

}
