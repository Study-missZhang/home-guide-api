package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.context.UserContext;
import com.guide.homeguideapi.controller.vo.HomeLocationRespVO;
import com.guide.homeguideapi.controller.vo.SetHomeReqVO;
import com.guide.homeguideapi.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息 Controller，提供用户相关 RESTful 接口
 *
 * @author zky
 */
@Tag(name = "用户信息", description = "用户基础信息的增删改查接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    /** 用户信息 Service */
    private final UserInfoService userInfoService;

    @Operation(
            summary = "设置家的位置",
            description = "接收经纬度和地址，更新当前用户的家位置，需登录后调用",
            responses = @ApiResponse(responseCode = "200", description = "设置成功",
                    content = @Content(schema = @Schema(type = "string", example = "success"))))
    @PostMapping("/home")
    public ResponseEntity<Boolean> setHomeLocation(@RequestBody SetHomeReqVO reqVO){
        Long userId = UserContext.getUserId();
        boolean result = userInfoService.setHomeLocation(reqVO, userId);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "获取家的位置",
            description = "返回当前登录用户家的经纬度和地址，需登录后调用",
            responses = {
                    @ApiResponse(responseCode = "200", description = "查询成功",
                            content = @Content(schema = @Schema(implementation = HomeLocationRespVO.class))),
                    @ApiResponse(responseCode = "401", description = "未登录或 token 已过期")
            }
    )
    @GetMapping("/get/home")
    public ResponseEntity<HomeLocationRespVO> getHomeLocation(){
        Long userId = UserContext.getUserId();
        HomeLocationRespVO respVO = userInfoService.getHomeLocation(userId);
        return ResponseEntity.ok(respVO);
    }
}
