package br.ufba.reuse.bolao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.response.filter.AjaxServerAndClientTimeFilter;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import br.ufba.reuse.bolao.business.GrupoBusiness;
import br.ufba.reuse.bolao.business.UsuarioBusiness;
import br.ufba.reuse.bolao.entities.Grupo;
import br.ufba.reuse.bolao.entities.Usuario;
import br.ufba.reuse.bolao.pages.LoginPage;

@Component
public class WicketApplication extends WebApplication {

	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private GrupoBusiness grupoBusiness;

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
			
		}
		
		
	}

}
