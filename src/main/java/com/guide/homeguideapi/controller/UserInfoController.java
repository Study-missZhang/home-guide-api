package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 查询所有用户列表
     *
     * @return 用户列表
     */
    @Operation(
            summary = "查询所有用户",
            description = "返回 user_info 表中所有用户数据",
            responses = @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserInfo.class))))
    )
    @GetMapping("/list")
    public ResponseEntity<List<UserInfo>> list() {
        return ResponseEntity.ok(userInfoService.listAll());
    }

    /**
     * 根据主键查询用户
     *
     * @param id 主键ID
     * @return 用户信息，不存在时返回 404
     */
    @Operation(
            summary = "根据ID查询用户",
            description = "根据主键ID查询单个用户信息",
            responses = {
                    @ApiResponse(responseCode = "200", description = "查询成功",
                            content = @Content(schema = @Schema(implementation = UserInfo.class))),
                    @ApiResponse(responseCode = "404", description = "用户不存在")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getById(
            @Parameter(description = "用户主键ID", example = "1") @PathVariable Long id) {
        return userInfoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据微信 openid 查询用户
     *
     * @param openid 微信用户唯一标识
     * @return 用户信息，不存在时返回 404
     */
    @Operation(
            summary = "根据 openid 查询用户",
            description = "根据微信用户唯一标识 openid 查询用户信息",
            responses = {
                    @ApiResponse(responseCode = "200", description = "查询成功",
                            content = @Content(schema = @Schema(implementation = UserInfo.class))),
                    @ApiResponse(responseCode = "404", description = "用户不存在")
            }
    )
    @GetMapping("/openid/{openid}")
    public ResponseEntity<UserInfo> getByOpenid(
            @Parameter(description = "微信用户唯一标识", example = "oXxxx_xxxxxxxxxxxxxxx") @PathVariable String openid) {
        return userInfoService.getByOpenid(openid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息实体（JSON 请求体）
     * @return 影响行数
     */
    @Operation(
            summary = "新增用户",
            description = "新增一条用户信息，openid 为必填项且唯一",
            responses = @ApiResponse(responseCode = "200", description = "新增成功，返回影响行数",
                    content = @Content(schema = @Schema(type = "integer", example = "1")))
    )
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.save(userInfo));
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息实体（需包含 id，JSON 请求体）
     * @return 影响行数
     */
    @Operation(
            summary = "更新用户信息",
            description = "根据 id 更新用户信息，请求体中 id 为必填项",
            responses = @ApiResponse(responseCode = "200", description = "更新成功，返回影响行数",
                    content = @Content(schema = @Schema(type = "integer", example = "1")))
    )
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.update(userInfo));
    }

    /**
     * 根据主键删除用户
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Operation(
            summary = "删除用户",
            description = "根据主键ID删除用户信息",
            responses = @ApiResponse(responseCode = "200", description = "删除成功，返回影响行数",
                    content = @Content(schema = @Schema(type = "integer", example = "1")))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(
            @Parameter(description = "用户主键ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(userInfoService.deleteById(id));
    }
}
