package www.chinacloud.com.whitehole.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import www.chinacloud.com.whitehole.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Speeder on 2015/10/30.
 * Whitehole 主配置模块
 */
public class WhiteholeConfiguration extends Configuration {

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }
}
