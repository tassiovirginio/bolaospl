package br.ufba.reuse.bolao.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Aposta;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.base.BasePage;

@MountPath("ranking")
public class RankingPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private ApostaBusiness apostaBusiness;

    @SpringBean
    private UsuarioBusiness usuarioBusiness;

    public RankingPage() {

        List<Aposta> listaApostasExibicao = new ArrayList<>();

        List<Usuario> listaUsuarios = usuarioBusiness.listAll();

        for(Usuario usuario: listaUsuarios){
            int soma = 0;

            List<Aposta> lista = apostaBusiness.listApostasUsuario(usuario);
            for(Aposta aposta : lista){
                soma += aposta.getPontos();
            }

            Aposta aposta = new Aposta();
            aposta.setApostador(usuario);
            aposta.setPontos(soma);
            listaApostasExibicao.add(aposta);
        }
        

		add(new ListView<Aposta>("listApostas",listaApostasExibicao) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Aposta> item) {
				Aposta aposta = item.getModelObject();
				item.add(new Label("nome", aposta.getApostador().getNome()));
				item.add(new Label("pontos", aposta.getPontos()));
			}
		});


    }

}
