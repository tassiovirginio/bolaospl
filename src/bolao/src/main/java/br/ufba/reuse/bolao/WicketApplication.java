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
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import br.ufba.reuse.bolao.pages.LoginPage;

@Component
public class WicketApplication extends WebApplication {
	
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
	}
	
	private void clearCommentsHtml() {
		System.out.println("clearCommentsHtml");
		String root = this.getServletContext().getRealPath("/");
		File file = new File(root);
		file = file.getParentFile().getParentFile();//.getParentFile();
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
				if (currentLine.trim().startsWith("//@"))continue;
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

}
