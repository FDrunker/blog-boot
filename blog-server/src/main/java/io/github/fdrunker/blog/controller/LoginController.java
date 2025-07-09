package io.github.fdrunker.blog.controller;

import io.github.fdrunker.blog.common.annotation.OperationLog;
import io.github.fdrunker.blog.common.enums.BusinessType;
import io.github.fdrunker.blog.common.enums.OperatorType;
import io.github.fdrunker.blog.common.model.ResultJson;
import io.github.fdrunker.blog.pojo.dto.LoginDTO;
import io.github.fdrunker.blog.pojo.vo.UserVO;
import io.github.fdrunker.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统管理-系统登录操作")
@RestController
@RequestMapping("/manage/sso")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @OperationLog(operateType = OperatorType.ADMIN, businessType = BusinessType.OTHER)
    public ResultJson login(LoginDTO loginDTO) {
        UserVO userVO = userService.login(loginDTO);
        return ResultJson.success("登录成功", userVO);
    }

    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public ResultJson logout() {
        userService.logout();
        return ResultJson.success("登出成功");
    }

}
