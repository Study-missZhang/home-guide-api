package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.entity.HomesickLog;
import com.guide.homeguideapi.service.HomesickLogService;
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
 * 想家记录 Controller，提供想家记录相关 RESTful 接口
 *
 * @author zky
 */
@Tag(name = "想家记录", description = "用户想家打开记录的增删查接口")
@RestController
@RequestMapping("/homesick")
@RequiredArgsConstructor
public class HomesickLogController {

    /** 想家记录 Service */
    private final HomesickLogService homesickLogService;

    /**
     * 查询所有想家记录
     *
     * @return 想家记录列表
     */
    @Operation(
            summary = "查询所有想家记录",
            description = "返回 homesick_log 表中所有记录",
            responses = @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HomesickLog.class))))
    )
    @GetMapping("/list")
    public ResponseEntity<List<HomesickLog>> list() {
        return ResponseEntity.ok(homesickLogService.listAll());
    }

    /**
     * 根据主键查询想家记录
     *
     * @param id 主键ID
     * @return 想家记录，不存在时返回 404
     */
    @Operation(
            summary = "根据ID查询想家记录",
            description = "根据主键ID查询单条想家记录",
            responses = {
                    @ApiResponse(responseCode = "200", description = "查询成功",
                            content = @Content(schema = @Schema(implementation = HomesickLog.class))),
                    @ApiResponse(responseCode = "404", description = "记录不存在")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<HomesickLog> getById(
            @Parameter(description = "记录主键ID", example = "1") @PathVariable Long id) {
        return homesickLogService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据用户ID查询该用户所有想家记录
     *
     * @param userId 用户ID
     * @return 想家记录列表
     */
    @Operation(
            summary = "根据用户ID查询想家记录",
            description = "查询指定用户的所有想家记录，按时间升序排列",
            responses = @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HomesickLog.class))))
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HomesickLog>> listByUserId(
            @Parameter(description = "用户ID", example = "1") @PathVariable Long userId) {
        return ResponseEntity.ok(homesickLogService.listByUserId(userId));
    }

    /**
     * 新增一条想家记录
     *
     * @param homesickLog 想家记录实体（JSON 请求体）
     * @return 影响行数
     */
    @Operation(
            summary = "新增想家记录",
            description = "记录用户一次想家行为，userId 和 currentDistance 为必填项",
            responses = @ApiResponse(responseCode = "200", description = "新增成功，返回影响行数",
                    content = @Content(schema = @Schema(type = "integer", example = "1")))
    )
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody HomesickLog homesickLog) {
        return ResponseEntity.ok(homesickLogService.save(homesickLog));
    }

    /**
     * 根据主键删除想家记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Operation(
            summary = "删除想家记录",
            description = "根据主键ID删除一条想家记录",
            responses = @ApiResponse(responseCode = "200", description = "删除成功，返回影响行数",
                    content = @Content(schema = @Schema(type = "integer", example = "1")))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(
            @Parameter(description = "记录主键ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(homesickLogService.deleteById(id));
    }
}
