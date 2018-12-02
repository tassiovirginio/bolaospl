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
		root = file.getAbsolutePath() + "/target/classes";
		System.out.println("clearCommentsHtml: " + root);
		listFilesAndFilesSubDirectories(root);
	}

	public void listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);
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
			Usuario usuarioAdmin = new Usuario();
			usuarioAdmin.setEmail("admin@admin.com");
			usuarioAdmin.setSenha("admin");
			usuarioAdmin.setCelular("99 9999-9999");
			usuarioAdmin.setNome("Administrador");
			usuarioBusiness.save(usuarioAdmin);
		}
		
		Usuario usuario01 = usuarioBusiness.getByEmail("usuario01@ufba.br");

		if (usuario01 == null) {
			usuario01 = new Usuario();
			usuario01.setEmail("usuario01@ufba.br");
			usuario01.setSenha("123");
			usuario01.setCelular("99 9999-9999");
			usuario01.setNome("Usuario01");
			usuarioBusiness.save(usuario01);
			
			
			Grupo grupo1 = new Grupo();
			grupo1.setNome("LES");
			grupo1.setDescricao("Grupo do Laboratório LES");
			grupo1.setDono(usuario01);
			grupoBusiness.save(grupo1);
			
			Grupo grupo2 = new Grupo();
			grupo2.setNome("INES");
			grupo2.setDescricao("Grupo do Laboratório INES");
			grupo2.setDono(usuario01);
			grupoBusiness.save(grupo2);
			
			Campeonato campeonato1 = new Campeonato();
			campeonato1.setNome("Brasileirão");
			campeonatoBusiness.save(campeonato1);
			
			Time time1 = new Time();
			time1.setNome("Bahia");
			time1.getCampeonatos().add(campeonato1);
			timeBusiness.save(time1);
			
			Time time2 = new Time();
			time2.setNome("Vitoria");
			time2.getCampeonatos().add(campeonato1);
			timeBusiness.save(time2);
			
			campeonato1.getTimes().add(time1);
			campeonato1.getTimes().add(time2);
			campeonatoBusiness.save(campeonato1);
			
			Jogo jogo1 = new Jogo();
			jogo1.setCampeonato(campeonato1);
			jogo1.setTime1(time1);
			jogo1.setTime2(time2);
			jogoBusiness.save(jogo1);
			
			Bolao bolao1 = new Bolao();
			bolao1.setCriacao(new Date());
			bolao1.setFechamento(new Date(new Date().getTime() + 60*60*1000*48));
			bolao1.setJogo(jogo1);
			bolao1.setCampeonato(campeonato1);
			bolao1.setGrupo(grupo1);
			bolao1.setJogo(jogo1);
			bolao1.setNome("Os amigos!!!");
			bolaoBusiness.save(bolao1);
			
			
		}
		
		
	}

}
