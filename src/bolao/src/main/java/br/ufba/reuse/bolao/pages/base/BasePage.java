package br.ufba.reuse.bolao.pages.base;

//#if Pagina02
//@import br.ufba.reuse.bolao.pages.Pagina02;
//#endif
//#if Pagina01
import br.ufba.reuse.bolao.pages.Pagina01;
//#endif
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void onBeforeRender() {
    	// TODO Auto-generated method stub
    	super.onBeforeRender();
    }

    public BasePage(){

    	//#if Pagina01
        Link pagina01 = new Link("pagina01") {
            @Override
            public void onClick() {
                BasePage.this.setResponsePage(Pagina01.class);
            }
        };
        add(pagina01);
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

    }

}
