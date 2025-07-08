package io.github.fdrunker.blog.controller.manage.system;

import io.github.fdrunker.blog.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统管理-用户管理接口")
@RestController
@RequestMapping("/manage/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}
