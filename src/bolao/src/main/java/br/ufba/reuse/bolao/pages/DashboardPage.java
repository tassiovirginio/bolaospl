package br.ufba.reuse.bolao.pages;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("dashboard")
public class DashboardPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
	private GrupoBusiness grupoBusiness;

	@SpringBean
	private ApostaBusiness apostaBusiness;

	@SpringBean
	private UsuarioBusiness usuarioBusiness;
	
	@SpringBean 
	private BolaoBusiness bolaoBusiness;

    public DashboardPage() {
		final Usuario usuarioLogado = (Usuario) getSession().getAttribute("usuario");
		
		add(new Label("sweepstakes", bolaoBusiness.size()));
		add(new Label("bets",apostaBusiness.size()));
		add(new Label("bettors",usuarioBusiness.size()));
		add(new Label("points", apostaBusiness.pontosTotais()));
    	
    	List<Grupo> grupos = grupoBusiness.findAllParticipantGroups(usuarioLogado); //grupoBusiness.listAll();
		
    	add(new Link("criaGrupo") {
            @Override
            public void onClick() {
                setResponsePage(new CreateGrupoPage(DashboardPage.this));
            }
    	});
    	
    	add(new ListView<Grupo>("listGrupo", grupos) {
			@Override
			protected void populateItem(ListItem<Grupo> item) {
				Grupo grupo = item.getModelObject();
				
				item.add(new Label("nome", grupo.getNome()));

				item.add(new Label("dono", grupo.getDono().getNome()));
				item.add(new Label("size", grupo.getParticipantes().size()));

				Link linkGrupo = new Link("linkGrupo") {
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						setResponsePage(new CreateGrupoPage(grupo,DashboardPage.this));
					}
				};

				linkGrupo.setVisible(usuarioLogado.equals(grupo.getDono()));

				item.add(linkGrupo);

				List<Bolao> listaBolao = bolaoBusiness.listBy(grupo);

				item.add(new ListView<Bolao>("listaBolao", listaBolao) {
					@Override
					protected void populateItem(ListItem<Bolao> item) {
						Bolao bolao = item.getModelObject();
						item.add(new Label("nome", bolao.getNome()));
						// item.add(new Label("time1", bolao.getJogo().getTime1().getNome()));
						// item.add(new Label("time2", bolao.getJogo().getTime2().getNome()));

						WebMarkupContainer image1 = new WebMarkupContainer("imgTime1");
						image1.add(new AttributeModifier("src",bolao.getJogo().getTime1().getImgUrl()));
						image1.add(new AttributeModifier("alt",bolao.getJogo().getTime1().getNome()));
						item.add(image1);

						WebMarkupContainer image2 = new WebMarkupContainer("imgTime2");
						image2.add(new AttributeModifier("src",bolao.getJogo().getTime2().getImgUrl()));
						image2.add(new AttributeModifier("alt",bolao.getJogo().getTime2().getNome()));
						item.add(image2);

						item.add(new Label("placar1", bolao.getJogo().getPlacar1()));
						item.add(new Label("placar2", bolao.getJogo().getPlacar2()));

						item.add(new Label("apostasSize", bolao.getApostas().size()));

						item.add(new Link("linkApostas"){
							@Override
							public void onClick() {
								setResponsePage(new ListApostaPage(bolao));
							}
						});

						Link linkRanking = new Link("linkRanking") {
							@Override
							public void onClick() {
								setResponsePage(new RankinBolaoPage(bolao));
							}
						};
						linkRanking.setVisible(bolao.getProcessado() != null);
						item.add(linkRanking);

						item.add(new Label("data", bolao.getJogo().getData()));

						item.add(new Label("vencedor", bolao.getJogo().getVencedor().getNome()));

						Label lbAguardando = new Label("lbAguardando", "aguardando");
						lbAguardando.setVisible(bolao.getProcessado() == null);
						item.add(lbAguardando);

						Label lbProcessado = new Label("lbProcessado", "processado");
						lbProcessado.setVisible(bolao.getProcessado() != null);
						item.add(lbProcessado);

						Link linkApostar = new Link("linkApostar") {
							@Override
							public void onClick() {
								setResponsePage(new ApostaPage(bolao));
							}
						};
						linkApostar.setVisible(bolao.getProcessado() == null);
						item.add(linkApostar);

					}
				});
				
			}
        });
    	
    	
    }

}