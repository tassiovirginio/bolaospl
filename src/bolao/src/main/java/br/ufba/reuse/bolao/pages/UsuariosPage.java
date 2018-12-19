package br.ufba.reuse.bolao.pages;

import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("users")
public class UsuariosPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;

	public UsuariosPage() {

		List<Usuario> lista = usuarioBusiness.listAll();

		add(new ListView<Usuario>("list", lista) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Usuario> item) {
				Usuario usuario = item.getModelObject();
				item.add(new Label("nome", usuario.getNome()));
				item.add(new Label("email", usuario.getEmail()));
				item.add(new Label("admin", usuario.getAdmin()));
			}
		});



	}

}
