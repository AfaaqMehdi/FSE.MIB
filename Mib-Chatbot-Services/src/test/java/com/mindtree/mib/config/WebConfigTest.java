package com.mindtree.mib.config;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.mindtree.mib.chathistory.ExcelView;
import com.mindtree.mib.chathistory.ExcelViewResolver;

public class WebConfigTest {

	WebConfig webConfig;

	@Before
	public void intialize() {
		webConfig = new WebConfig();
	}

	@Test
	public void testViewResolver() {
		ViewResolver viewResolver = webConfig.contentNegotiatingViewResolver(new ContentNegotiationManager());

		ContentNegotiatingViewResolver resolver = (ContentNegotiatingViewResolver) viewResolver;

		Assert.assertEquals(1, resolver.getViewResolvers().size());
		Assert.assertEquals(ExcelViewResolver.class, resolver.getViewResolvers().get(0).getClass());
	}
	
	@Test
	public void testConfigureContent() {
		ContentNegotiationConfigurer configurer = new ContentNegotiationConfigurer(null);
		
		 webConfig.configureContentNegotiation(configurer);

		 Assert.assertNotNull(configurer);
	}

	@Test
	public void testResolveViewName() {
		try {
			ExcelViewResolver viewResolver = new ExcelViewResolver();
			View view = viewResolver.resolveViewName("ExcelView", Locale.ENGLISH);

			Assert.assertEquals(ExcelView.class, view.getClass());
		} catch (Exception ex) {
			Assert.fail();
		}
	}

}
