package www.chinacloud.com.whitehole.swagger;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by Speeder on 2015/11/4.
 */
public class SwaggerHealth extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
