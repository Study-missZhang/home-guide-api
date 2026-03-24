package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.controller.vo.WxLoginReqVO;
import com.guide.homeguideapi.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录 Controller，提供登录相关的接口
 *
 * @author zky
 */
@Tag(name = "登录", description = "微信小程序登录相关接口")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class WxLoginController {

    /** 登录 Service */
    private final LoginService loginService;

    /**
     * 微信登录接口
     * 小程序调用 wx.login() 拿到 code 后请求此接口
     *
     * @param wxLoginReqVO 登录请求参数（含 code）
     * @return JWT token 字符串
     */
    @Operation(
            summary = "微信登录",
            description = "小程序端调用 wx.login() 获取 code，传入此接口换取 JWT token，后续请求需在 Header 中携带该 token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "登录成功，返回 JWT token",
                            content = @Content(schema = @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiJ9..."))),
                    @ApiResponse(responseCode = "500", description = "微信登录失败，code 无效或已过期")
            }
    )
    @PostMapping("/wx")
    public ResponseEntity<String> wxLogin(@RequestBody WxLoginReqVO wxLoginReqVO) {
        return ResponseEntity.ok(loginService.wxLogin(wxLoginReqVO));
    }
}
