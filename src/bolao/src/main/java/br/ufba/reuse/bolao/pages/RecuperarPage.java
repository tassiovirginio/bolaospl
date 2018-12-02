package br.ufba.reuse.bolao.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;

@MountPath("recuperar")
public class RecuperarPage extends WebPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;

    public RecuperarPage() {
    }

}
