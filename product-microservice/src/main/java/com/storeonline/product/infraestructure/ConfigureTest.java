package com.storeonline.product.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class ConfigureTest {
	
	@Bean
	@Profile("h2")
	public ConnectionFactoryInitializer initializeH2(ConnectionFactory connectionFactory) {
		return initializer(connectionFactory, "schema-h2.sql");
	}
	
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory, String schema) {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		
		var populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource(schema)));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}
}
