package www.chinacloud.com.whitehole.core.resources;

import com.google.common.base.Optional;
import io.swagger.annotations.*;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import www.chinacloud.com.whitehole.core.dao.UserDAO;
import www.chinacloud.com.whitehole.core.entity.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by Speeder on 2015/11/9.
 */
@Path("/users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/users")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @POST
    @Transaction
    @ApiOperation(value = "新增用户")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "权限验证失败"),
            @ApiResponse(code = 201, message = "创建成功"),
            @ApiResponse(code = 422, message = "参数错误")
    })
    public Response addUser(@ApiParam(value = "用户对象", required = true) @Valid User user) {
        try {
            user.setId(UUID.randomUUID().toString());
            LOGGER.debug(user.getId());
            userDAO.addTwoUser(user);
            return Response.created(new URI("users/" + user.getId())).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.notAcceptable(null).build();
    }

    @GET
    @ApiOperation(value = "查询所有用户")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "权限验证失败"),
            @ApiResponse(code = 200, message = "查询成功")
    })
    public Response getUsers(@ApiParam(value = "把名字作为过滤条件") @QueryParam("name") Optional<String> name,
                             @ApiParam(value = "把email作为过滤条件") @QueryParam("email") Optional<String> email) {
        return Response.ok(userDAO.getUsers(Optional.of("%" + name.or("").trim() + "%"), Optional.of("%" + email.or("").trim() + "%"))).build();
    }

    @GET
    @Path("/{userId}")
    @ApiOperation(value = "根据用户ID查询用户")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "权限验证失败"),
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 404, message = "对应该ID的用户不存在")
    })
    public Response getUserById(@ApiParam(value = "用户ID") @PathParam("userId") Optional<String> id) {
        return Response.ok(userDAO.findUserById(id)).build();
    }

    @DELETE
    @Path("/{userId}")
    @ApiOperation(value = "根据用户ID删除用户")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "权限验证失败"),
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 404, message = "对应该ID的用户不存在")
    })
    public Response deleteUserById(@ApiParam(value = "用户ID") @PathParam("userId") Optional<String> userId) {
        int count = userDAO.deleteUserById(userId);
        if(count == 0) {
            throw new NotFoundException("ID为" + userId + "的用户不存在");
        }
        return Response.accepted().build();
    }
}
