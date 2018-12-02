package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("boloes")
public class ListBolaoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private BolaoBusiness bolaoBusiness;

    public ListBolaoPage() {
    	
    	List<Bolao> boloes = bolaoBusiness.listAll();
    	
    	add(new ListView<Bolao>("listBolao", boloes) {
			@Override
			protected void populateItem(ListItem<Bolao> item) {
				Bolao bolao = item.getModelObject();
				
				item.add(new Label("nome", bolao.getNome()));
				item.add(new Label("grupo", bolao.getGrupo().getNome()));
				item.add(new Label("campeonato", bolao.getCampeonato().getNome()));
				item.add(new Label("criacao", bolao.getCriacao()));
				item.add(new Label("fechamento", bolao.getFechamento()));
				item.add(new Label("time1", bolao.getJogo().getTime1().getNome()));
				item.add(new Label("time2", bolao.getJogo().getTime2().getNome()));
				item.add(new Label("apostas", bolao.getApostas().size()));
				
			}
        });
    	
    }

}