package com.ex.swa.controller;

import com.ex.swa.pojo.User;
import com.ex.swa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 信息操作处理
 */
@Controller
@RequestMapping("/system/user")
public class ceshi
{
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<User> list(User user)
    {
        List<User> list = userService.selectUserList(user);
        return list;
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/add")
    @ResponseBody
    public int addSave(User user)
    {
        return userService.insertUser(user);
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        User user = userService.selectUserById(userId);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public int editSave(User user)
    {
        return userService.updateUser(user);
    }

    /**
     * 删除用户
     */
    @PostMapping( "/remove")
    @ResponseBody
    public int remove(String ids)
    {
        return userService.deleteUserByIds(ids);
    }

}
