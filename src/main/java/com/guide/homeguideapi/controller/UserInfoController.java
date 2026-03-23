package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息 Controller，提供用户相关 RESTful 接口
 *
 * @author zky
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    /** 用户信息 Service */
    private final UserInfoService userInfoService;

    /**
     * 查询所有用户列表
     * GET /user/list
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserInfo>> list() {
        return ResponseEntity.ok(userInfoService.listAll());
    }

    /**
     * 根据主键查询用户
     * GET /user/{id}
     *
     * @param id 主键ID
     * @return 用户信息，不存在时返回 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getById(@PathVariable Long id) {
        return userInfoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据微信 openid 查询用户
     * GET /user/openid/{openid}
     *
     * @param openid 微信用户唯一标识
     * @return 用户信息，不存在时返回 404
     */
    @GetMapping("/openid/{openid}")
    public ResponseEntity<UserInfo> getByOpenid(@PathVariable String openid) {
        return userInfoService.getByOpenid(openid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 新增用户信息
     * POST /user
     *
     * @param userInfo 用户信息实体（JSON 请求体）
     * @return 影响行数
     */
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.save(userInfo));
    }

    /**
     * 更新用户信息
     * PUT /user
     *
     * @param userInfo 用户信息实体（需包含 id，JSON 请求体）
     * @return 影响行数
     */
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.update(userInfo));
    }

    /**
     * 根据主键删除用户
     * DELETE /user/{id}
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userInfoService.deleteById(id));
    }
}
