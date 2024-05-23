package Amazon.Config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = "Amazon")
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		System.out.println("SpringConfig.viewResolver45()");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// It specifies that JSTL views should be used. 
		//JstlView is a class provided by the Spring framework that is specifically designed to handle JSP pages using JSTL.
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	public static SessionFactory getSessionFactory() {
		Properties ps = new Properties();

		ps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		ps.put(Environment.URL, "jdbc:mysql://localhost:3306/demodb");
		ps.put("hibernate.connection.username", "root");
		ps.put("hibernate.connection.password", "root");
		ps.put(Environment.SHOW_SQL, "true");
		ps.put(Environment.FORMAT_SQL, "true");
		ps.put(Environment.HBM2DDL_AUTO, "update");

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(ps).build();
		// Create a MetadataSources and add annotated entity classes
		MetadataSources metadataSources = new MetadataSources(ssr).addAnnotatedClass(Amazon.User.User.class);
		Metadata meta = metadataSources.buildMetadata();
		// Create a SessionFactory
		SessionFactory sessionFactory = meta.buildSessionFactory();

		return sessionFactory;

	}
	
	

}

