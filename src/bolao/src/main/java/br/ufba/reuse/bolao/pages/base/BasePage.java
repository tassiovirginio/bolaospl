package br.ufba.reuse.bolao.pages.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.LoginPage;
import br.ufba.reuse.bolao.pages.DetalhesUsuarioPage;
import br.ufba.reuse.bolao.pages.ListGrupoPage;
import br.ufba.reuse.bolao.pages.Pagina02;
import br.ufba.reuse.bolao.pages.RankingPage;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    public BasePage(){
    	
    	Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
    	
    	add(new Label("usuarioLogado",usuarioLogado.getNome()));
    	
    	//#if Ranking
    	add(new Link("linkRanking") {
            @Override
            public void onClick() {
                setResponsePage(new RankingPage());
            }
    	});
    	//#endif
    	
    	add(new Link("linkGrupos") {
            @Override
            public void onClick() {
                setResponsePage(new ListGrupoPage());
            }
    	});
    	
    	add(new Link("linkDetalhes") {
            @Override
            public void onClick() {
                setResponsePage(new DetalhesUsuarioPage(usuarioLogado));
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

    	//#if Pagina01
//        Link pagina01 = new Link("pagina01") {
//Override
//            public void onClick() {
//                BasePage.this.setResponsePage(Pagina01.class);
//            }
//        };
//        add(pagina01);
        //#endif

      //#if Pagina02
//@        Link pagina02 = new Link("pagina02") {
//@            @Override
//@            public void onClick() {
//@                BasePage.this.setResponsePage(Pagina02.class);
//@            }
//@        };
//@        add(pagina02);
      //#endif
        
//        Link loginPage = new Link("loginPage") {
//Override
//            public void onClick() {
//                BasePage.this.setResponsePage(LoginPage.class);
//            }
//        };
//        add(loginPage);

    }

}
