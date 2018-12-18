package br.ufba.reuse.bolao.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("details")
public class DetalhesUsuarioPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;

    public DetalhesUsuarioPage(Usuario usuario) {
        Form form = new Form("form"){
            protected void onSubmit() {
                setResponsePage(new DetalhesUsuarioPage(usuario));
            }
        };

        form.add(new TextField<String>("nome", new PropertyModel<String>(usuario, "nome")));
        form.add(new TextField<String>("email", new PropertyModel<String>(usuario, "email")));
        form.add(new TextField<String>("gravatar", new PropertyModel<String>(usuario, "gravatar")));
        form.add(new PasswordTextField("senha", new PropertyModel<String>(usuario, "senha")));
        form.add(new TextField<String>("celular", new PropertyModel<String>(usuario, "celular")));
        add(form);
    }

}
