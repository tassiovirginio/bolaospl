package br.ufba.reuse.bolao.pages;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("pagina01")
public class ListBolaoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private BolaoBusiness bolaoBusiness;

    public ListBolaoPage() {
    }

}