package com.tvajjala.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
/**
 * Enable this to customize
 *
 * @author ThirupathiReddy V
 *
 */
//@Configuration
//@EnableJpaRepositories(basePackages = {
//"com.avenuecode.persistence.repository" })
//@EnableTransactionManagement
public class DatabaseConfig {

    /** Reference to logger */
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public DataSource dataSource() {
	LOG.info("Configure 3rd party dataSource here . for demo im using default spring boot provided one");
	return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }


}
