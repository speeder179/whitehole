package www.chinacloud.com.whitehole.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

import java.util.Map;

/**
 * Created by Speeder on 2015/11/2.
 *
 */
public abstract class SwaggerBundle<T extends Configuration> implements ConfiguredBundle<T> {
    @Override
    public void run(T configuration, Environment environment) throws Exception {
        SwaggerBundleConfiguration swaggerBundleConfiguration = getSwaggerBundleConfiguration(configuration);
        if (swaggerBundleConfiguration == null) {
            throw new IllegalStateException("You need to provide an instance of SwaggerBundleConfiguration");
        }

        ConfigurationHelper configurationHelper = new ConfigurationHelper(configuration, swaggerBundleConfiguration);
        new AssetsBundle(Constants.SWAGGER_RESOURCES_PATH, configurationHelper.getSwaggerUriPath(), null, Constants.SWAGGER_ASSETS_NAME).run(environment);

        environment.jersey().register(new SwaggerResource(configurationHelper.getUrlPattern()));
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        setUpSwagger(swaggerBundleConfiguration, configurationHelper.getUrlPattern());
        environment.jersey().register(new ApiListingResource());
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        bootstrap.addBundle(new ViewBundle<Configuration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(final Configuration configuration) {
                return ImmutableMap.of();
            }
        });
    }

    protected abstract SwaggerBundleConfiguration getSwaggerBundleConfiguration(T configuration);

    private void setUpSwagger(SwaggerBundleConfiguration swaggerBundleConfiguration, String basePath) {
        BeanConfig beanConfig = new BeanConfig();

        if(swaggerBundleConfiguration.getResourcePackage() != null) {
            beanConfig.setResourcePackage(swaggerBundleConfiguration.getResourcePackage());
        }

        /*if(swaggerBundleConfiguration.getBasePath() != null) {
            beanConfig.setBasePath(swaggerBundleConfiguration.getBasePath());
        }*/

        if(swaggerBundleConfiguration.getContact() != null) {
            beanConfig.setContact(swaggerBundleConfiguration.getContact());
        }

        if(swaggerBundleConfiguration.getDescription() != null) {
            beanConfig.setDescription(swaggerBundleConfiguration.getDescription());
        }

        if(swaggerBundleConfiguration.getHost() != null) {
            beanConfig.setHost(swaggerBundleConfiguration.getHost());
        }

        if(swaggerBundleConfiguration.getInfo() != null) {
            beanConfig.setInfo(swaggerBundleConfiguration.getInfo());
        }

        if(swaggerBundleConfiguration.getLicense() != null) {
            beanConfig.setLicense(swaggerBundleConfiguration.getLicense());
        }

        if(swaggerBundleConfiguration.getLicenseUrl() != null) {
            beanConfig.setLicenseUrl(swaggerBundleConfiguration.getLicenseUrl());
        }

        if(swaggerBundleConfiguration.getSchemes() != null) {
            beanConfig.setSchemes(swaggerBundleConfiguration.getSchemes());
        }

        if(swaggerBundleConfiguration.getTermsOfServiceUrl() != null) {
            beanConfig.setTermsOfServiceUrl(swaggerBundleConfiguration.getTermsOfServiceUrl());
        }

        if(swaggerBundleConfiguration.getTitle() != null) {
            beanConfig.setTitle(swaggerBundleConfiguration.getTitle());
        }

        if(swaggerBundleConfiguration.getVersion() != null) {
            beanConfig.setVersion(swaggerBundleConfiguration.getVersion());
        }

        beanConfig.setScan(true);
        beanConfig.setBasePath(basePath);
    }
}
