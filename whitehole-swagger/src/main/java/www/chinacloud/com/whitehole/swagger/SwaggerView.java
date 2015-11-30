package www.chinacloud.com.whitehole.swagger;

import com.google.common.base.Charsets;
import io.dropwizard.views.View;

/**
 * Created by Speeder on 2015/11/2.
 * 创建swagger视图，主要就是为了传2个变量到html中
 */
public class SwaggerView extends View {
    private final String contextPath;

    private final String swaggerPath;

    protected SwaggerView(String contextPath) {
        super("index.ftl", Charsets.UTF_8);

        if(contextPath.equals("/")) {
            this.contextPath = "";
            this.swaggerPath = Constants.SWAGGER_URI_PATH;
        } else {
            this.contextPath = contextPath;
            this.swaggerPath = contextPath + Constants.SWAGGER_URI_PATH;
        }
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getSwaggerPath() {
        return swaggerPath;
    }
}
