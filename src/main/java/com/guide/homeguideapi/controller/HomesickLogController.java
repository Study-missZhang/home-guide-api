package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.entity.HomesickLog;
import com.guide.homeguideapi.service.HomesickLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 想家记录 Controller，提供想家记录相关 RESTful 接口
 *
 * @author zky
 */
@RestController
@RequestMapping("/homesick")
@RequiredArgsConstructor
public class HomesickLogController {

    /** 想家记录 Service */
    private final HomesickLogService homesickLogService;

    /**
     * 查询所有想家记录
     * GET /homesick/list
     *
     * @return 想家记录列表
     */
    @GetMapping("/list")
    public ResponseEntity<List<HomesickLog>> list() {
        return ResponseEntity.ok(homesickLogService.listAll());
    }

    /**
     * 根据主键查询想家记录
     * GET /homesick/{id}
     *
     * @param id 主键ID
     * @return 想家记录，不存在时返回 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<HomesickLog> getById(@PathVariable Long id) {
        return homesickLogService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据用户ID查询该用户所有想家记录
     * GET /homesick/user/{userId}
     *
     * @param userId 用户ID
     * @return 想家记录列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HomesickLog>> listByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(homesickLogService.listByUserId(userId));
    }

    /**
     * 新增一条想家记录
     * POST /homesick
     *
     * @param homesickLog 想家记录实体（JSON 请求体）
     * @return 影响行数
     */
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody HomesickLog homesickLog) {
        return ResponseEntity.ok(homesickLogService.save(homesickLog));
    }

    /**
     * 根据主键删除想家记录
     * DELETE /homesick/{id}
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        return ResponseEntity.ok(homesickLogService.deleteById(id));
    }
}
