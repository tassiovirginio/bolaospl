package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("dashboard")
public class ListGrupoPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
	private GrupoBusiness grupoBusiness;
	
	@SpringBean
	private BolaoBusiness bolaoBusiness;

    public ListGrupoPage() {
    	final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
    	
    	List<Grupo> grupos = grupoBusiness.findAllParticipantGroups(usuarioLogado); //grupoBusiness.listAll();
    	
    	add(new Link("criaGrupo") {
            @Override
            public void onClick() {
                setResponsePage(new CreateGrupoPage());
            }
    	});
    	
    	add(new ListView<Grupo>("listGrupo", grupos) {
			@Override
			protected void populateItem(ListItem<Grupo> item) {
				Grupo grupo = item.getModelObject();
				
				item.add(new Label("nome", grupo.getNome()));

				item.add(new Label("dono", grupo.getDono().getNome()));
				item.add(new Label("size", grupo.getParticipantes().size()));

				item.add(new Link("linkGrupo") {
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						setResponsePage(new CreateGrupoPage(grupo));
					}
				});

				List<Bolao> listaBolao = bolaoBusiness.listBy(grupo);

				item.add(new ListView<Bolao>("listaBolao", listaBolao) {
					@Override
					protected void populateItem(ListItem<Bolao> item) {
						Bolao bolao = item.getModelObject();
						item.add(new Label("nome", bolao.getNome()));
						item.add(new Label("time1", bolao.getJogo().getTime1().getNome()));
						item.add(new Label("time2", bolao.getJogo().getTime2().getNome()));
						item.add(new Label("apostasSize", bolao.getApostas().size()));
					}
				});
				
				// item.add(new Link("linkBolao") {
				// 	@Override
				// 	public void onClick() {
				// 		setResponsePage(new ListBolaoPage());
				// 	}
				// });
				
			}
        });
    	
    	
    }

}