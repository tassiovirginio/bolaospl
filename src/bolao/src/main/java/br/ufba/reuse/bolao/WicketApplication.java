package br.ufba.reuse.bolao;

import br.ufba.reuse.bolao.pages.Pagina01;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.response.filter.AjaxServerAndClientTimeFilter;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

@Component
public class WicketApplication extends WebApplication  {


	@Override
	public Class<Pagina01> getHomePage() {
		return Pagina01.class;
	}

	@Override
	public void init() {
        getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
        getApplicationSettings().setUploadProgressUpdatesEnabled(true);
		getRequestCycleSettings().addResponseFilter(new AjaxServerAndClientTimeFilter());
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getResourceSettings().setThrowExceptionOnMissingResource(false);
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		getMarkupSettings().setStripWicketTags(true);
		new AnnotatedMountScanner().scanPackage(this.getClass().getPackage().getName()+".pages").mount(this);
    }

}
