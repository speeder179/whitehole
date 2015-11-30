package www.chinacloud.com.whitehole.swagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Speeder on 2015/11/2.
 * swagger ui视图
 */
@Path(Constants.SWAGGER_PATH)
@Produces(MediaType.TEXT_HTML)
public class SwaggerResource {

    private String contextPath;

    public SwaggerResource(String contextPath) {
        this.contextPath = contextPath;
    }

    @GET
    public SwaggerView get() {
        return new SwaggerView(contextPath);
    }
}
