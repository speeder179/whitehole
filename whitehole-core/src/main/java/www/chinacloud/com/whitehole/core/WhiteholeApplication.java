package www.chinacloud.com.whitehole.core;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import www.chinacloud.com.whitehole.core.dao.UserDAO;
import www.chinacloud.com.whitehole.core.resources.UserResource;
import www.chinacloud.com.whitehole.swagger.SwaggerBundle;
import www.chinacloud.com.whitehole.swagger.SwaggerBundleConfiguration;
import www.chinacloud.com.whitehole.swagger.SwaggerHealth;

/**
 * Created by Speeder on 2015/10/30.
 * Whitehole 项目执行主入口
 */
public class WhiteholeApplication extends Application<WhiteholeConfiguration> {

    public static void main(String... args) throws Exception {
        new WhiteholeApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<WhiteholeConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<WhiteholeConfiguration>() {

            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WhiteholeConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(WhiteholeConfiguration whiteholeConfiguration, Environment environment) throws Exception {
        environment.healthChecks().register("whitehole-swagger", new SwaggerHealth());
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, whiteholeConfiguration.getDatabase(), "mysql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        dao.createUserTable();
        environment.jersey().register(new UserResource(dao));
    }
}
