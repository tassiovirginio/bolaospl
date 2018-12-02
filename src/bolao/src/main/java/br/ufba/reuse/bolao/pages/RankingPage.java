package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("ranking")
public class RankingPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;

    public RankingPage() {
    }

}
