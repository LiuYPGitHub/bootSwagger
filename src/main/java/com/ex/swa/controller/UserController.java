package com.ex.swa.controller;

import com.ex.swa.pojo.User;
import com.ex.swa.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 测试
 */
@Api(tags = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    Map<Object, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public List<User> getUser(User user) {
        List<User> list = userService.selectUserList(user);
        return list;
    }

    @ApiOperation(value = "创建用户", notes = "创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "User")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getPhonenumber(), user);
        return "success";
    }

    @ApiOperation(value = "获用户细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/byid", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/putid", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User user1 = users.get(id);
        user1.setUserName(user.getUserName());
        users.put(id, user1);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/delid", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }
}
