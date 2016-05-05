package com.project.dropwizard;

import com.project.dropwizard.dao.PersonDAO;

import com.project.dropwizard.model.Person;

import com.project.dropwizard.resources.PersonResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class App extends Application<HibernateConfiguration> {

    //run from console : java -jar dropwizard_backend-xxxx.jar server config.yaml
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    private final HibernateBundle<HibernateConfiguration> hibernate = new HibernateBundle<HibernateConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HibernateConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<HibernateConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(HibernateConfiguration c, Environment e) throws Exception {
        final PersonDAO dao = new PersonDAO(hibernate.getSessionFactory());
        // Create a DBI factory and build a JDBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory
                .build(e, c.getDataSourceFactory(), "mysql");
        // Add the resource to the environment

        e.jersey().register(new PersonResource(dao));

    }
}
