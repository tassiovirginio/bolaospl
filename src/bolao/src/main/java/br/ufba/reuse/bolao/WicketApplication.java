package br.ufba.reuse.bolao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.response.filter.AjaxServerAndClientTimeFilter;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import br.ufba.reuse.bolao.business.ApostaBusiness;
import br.ufba.reuse.bolao.business.BolaoBusiness;
import br.ufba.reuse.bolao.business.CampeonatoBusiness;
import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.JogoBusiness;
import br.ufba.reuse.bolao.business.TimeBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Bolao;
import br.ufba.reuse.bolao.entities.Campeonato;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Jogo;
import br.ufba.reuse.bolao.entities.Time;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.LoginPage;

@Component
public class WicketApplication extends WebApplication {

	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private GrupoBusiness grupoBusiness;
	
	@Autowired
	private BolaoBusiness bolaoBusiness;
	
	@Autowired
	private ApostaBusiness apostaBusiness;
	
	@Autowired
	private CampeonatoBusiness campeonatoBusiness;
	
	@Autowired
	private JogoBusiness jogoBusiness;
	
	@Autowired
	private TimeBusiness timeBusiness;
	

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	public void init() {
		clearCommentsHtml();
		getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		getApplicationSettings().setUploadProgressUpdatesEnabled(true);
		getRequestCycleSettings().addResponseFilter(new AjaxServerAndClientTimeFilter());
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getMarkupSettings().setStripComments(true);
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getResourceSettings().setThrowExceptionOnMissingResource(false);
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		new AnnotatedMountScanner().scanPackage(this.getClass().getPackage().getName() + ".pages").mount(this);

		cargaTeste();
	}

	private void clearCommentsHtml() {
		System.out.println("clearCommentsHtml");
		String root = this.getServletContext().getRealPath("/");
		File file = new File(root);
		file = file.getParentFile().getParentFile();// .getParentFile();
//		root = file.getAbsolutePath() + "/target/classes"; //Maven
		root = file.getAbsolutePath() + "/build"; //Gradle
		System.out.println("clearCommentsHtml: " + root);
		listFilesAndFilesSubDirectories(root);
	}

	public void listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);
		System.out.println("---------> " + directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				if (file.getName().endsWith(".html")) {
					System.out.println("Apagando comentários: " + file.getName() + " - " + clearLineWithComment(file));
				}
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
	}

	private Boolean clearLineWithComment(File inputFile) {
		boolean successful = false;
		try {
			File tempFile = new File("file.tmp");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer;

			writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.trim().startsWith("//@"))
					continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			successful = tempFile.renameTo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return successful;

	}

	private void cargaTeste() {

		System.out.println("Criando Usuário Administrador...");

		Usuario uAdmin = usuarioBusiness.getByEmail("admin@admin.com");

		if (uAdmin == null) {
			uAdmin = new Usuario();
			uAdmin.setEmail("admin@admin.com");
			uAdmin.setSenha("admin");
			uAdmin.setCelular("99 9999-9999");
			uAdmin.setNome("Administrador");
			uAdmin.setAdmin(true);
			usuarioBusiness.save(uAdmin);

			Usuario u1 = new Usuario();
			u1.setEmail("usuario1@sisbolao.com");
			u1.setSenha("usuario1");
			u1.setCelular("99 9999-9999");
			u1.setNome("Usuario1");
			uAdmin.setAdmin(false);
			usuarioBusiness.save(u1);

			Usuario u2 = new Usuario();
			u2.setEmail("usuario2@sisbolao.com");
			u2.setSenha("usuario2");
			u2.setCelular("99 9999-9999");
			u2.setNome("Usuario2");
			usuarioBusiness.save(u2);
			
			Grupo grupo1 = new Grupo();
			grupo1.setNome("LES");
			grupo1.setDescricao("Grupo do Laboratório LES");
			grupo1.setDono(uAdmin);
			grupoBusiness.save(grupo1);
			
			Grupo grupo2 = new Grupo();
			grupo2.setNome("INES");
			grupo2.setDescricao("Grupo do Laboratório INES");
			grupo2.setDono(uAdmin);
			grupoBusiness.save(grupo2);
			
			Campeonato campeonato1 = new Campeonato();
			campeonato1.setNome("Brasileirão");
			campeonatoBusiness.save(campeonato1);

			campeonatoBusiness.save(new Campeonato("Copa do Brasil"));
			campeonatoBusiness.save(new Campeonato("Copa do América"));
			campeonatoBusiness.save(new Campeonato("Euro Copa"));
			
			Time time1 = new Time();
			time1.setNome("Bahia");
			time1.setImgUrl("https://www.escudosfc.com.br/images/bahia.png");
			time1.getCampeonatos().add(campeonato1);
			timeBusiness.save(time1);
			
			Time time2 = new Time();
			time2.setNome("Vitoria");
			time2.setImgUrl("https://www.escudosfc.com.br/images/vitoria.png");
			time2.getCampeonatos().add(campeonato1);
			timeBusiness.save(time2);

			Time time3 = new Time();
			time3.setNome("Vasco");
			time3.setImgUrl("https://www.escudosfc.com.br/images/vasco.png");
			time3.getCampeonatos().add(campeonato1);
			timeBusiness.save(time3);

			Time time4 = new Time();
			time4.setNome("Grêmio");
			time4.setImgUrl("https://www.escudosfc.com.br/images/gremio.png");
			time4.getCampeonatos().add(campeonato1);
			timeBusiness.save(time4);
			
			campeonato1.getTimes().add(time1);
			campeonato1.getTimes().add(time2);
			campeonato1.getTimes().add(time3);
			campeonato1.getTimes().add(time4);
			campeonatoBusiness.save(campeonato1);
			
			Jogo jogo1 = new Jogo();
			jogo1.setCampeonato(campeonato1);
			campeonato1.getJogos().add(jogo1);
			jogo1.setTime1(time1);
			jogo1.setTime2(time2);
			jogo1.setPlacar1(2);
			jogo1.setPlacar2(1);
			jogo1.setVencedor(time1);
			jogo1.setData(new Date(new Date().getTime() + (10 * 24 * 60 * 60 * 1000)));
			jogoBusiness.save(jogo1);

			Jogo jogo2 = new Jogo();
			jogo2.setCampeonato(campeonato1);
			campeonato1.getJogos().add(jogo2);
			jogo2.setTime1(time3);
			jogo2.setTime2(time4);
			jogo2.setPlacar1(2);
			jogo2.setPlacar2(1);
			jogo2.setVencedor(time3);
			jogo2.setData(new Date(new Date().getTime() + (10 * 24 * 60 * 60 * 1000)));
			jogoBusiness.save(jogo2);

			Jogo jogo3 = new Jogo();
			jogo3.setCampeonato(campeonato1);
			campeonato1.getJogos().add(jogo3);
			jogo3.setTime1(time1);
			jogo3.setTime2(time4);
			jogo3.setData(new Date(new Date().getTime() + (10 * 24 * 60 * 60 * 1000)));
			jogoBusiness.save(jogo3);

			Jogo jogo4 = new Jogo();
			jogo4.setCampeonato(campeonato1);
			campeonato1.getJogos().add(jogo4);
			jogo4.setTime1(time2);
			jogo4.setTime2(time4);
			jogo4.setData(new Date(new Date().getTime() + (10 * 24 * 60 * 60 * 1000)));
			jogoBusiness.save(jogo4);
			
			Bolao bolao1 = new Bolao();
			bolao1.setCriacao(new Date());
			bolao1.setFechamento(new Date(new Date().getTime() + 60*60*1000*48));
			bolao1.setJogo(jogo1);
			bolao1.setCampeonato(campeonato1);
			bolao1.setGrupo(grupo1);
			bolao1.setNome("Os amigos!!! 100 Reais");
			bolaoBusiness.save(bolao1);

			Bolao bolao2 = new Bolao();
			bolao2.setCriacao(new Date());
			bolao2.setFechamento(new Date(new Date().getTime() + 60*60*1000*48));
			bolao2.setJogo(jogo2);
			bolao2.setCampeonato(campeonato1);
			bolao2.setGrupo(grupo1);
			bolao2.setNome("Os amigos!!! 10 Reais");
			bolaoBusiness.save(bolao2);

			campeonatoBusiness.save(campeonato1);
			
			
		}
		
		
	}

}
