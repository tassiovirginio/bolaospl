package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("pagina01")
public class ListGrupoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private GrupoBusiness grupoBusiness;

    public ListGrupoPage() {
    	
    	List<Grupo> grupos = grupoBusiness.listAll();
    	
    	add(new ListView<Grupo>("listGrupo", grupos) {
			@Override
			protected void populateItem(ListItem<Grupo> item) {
				Grupo grupo = item.getModelObject();
				
				item.add(new Label("nome", grupo.getNome()));
				item.add(new Label("descricao", grupo.getDescricao()));
				item.add(new Label("size", grupo.getParticipantes().size()));
				
			}
        });
    	
    	
    }

}