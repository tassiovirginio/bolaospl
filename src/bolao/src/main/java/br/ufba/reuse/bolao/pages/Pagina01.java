package br.ufba.reuse.bolao.pages;

import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.pages.base.BasePage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("pagina01")
public class Pagina01 extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;

    public Pagina01() {
        System.out.println(usuarioBusiness.size());
    }

}
