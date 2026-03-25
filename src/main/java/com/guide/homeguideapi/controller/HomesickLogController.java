package com.guide.homeguideapi.controller;

import com.guide.homeguideapi.context.UserContext;
import com.guide.homeguideapi.controller.vo.HomeLocationRespVO;
import com.guide.homeguideapi.controller.vo.HomesickReportReqVO;
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

    @Operation(
            summary = "上报想家记录",
            description = "前端计算完距离后静默上报，存储用户当前距离家的距离和想家时刻",
            responses = {
                    @ApiResponse(responseCode = "200", description = "上报成功",
                            content = @Content(schema = @Schema(type = "boolean", example = "true"))),
                    @ApiResponse(responseCode = "401", description = "未登录或 token 已过期")
            }
    )
    @PostMapping("/report")
    public ResponseEntity<Boolean> report(@RequestBody HomesickReportReqVO homesickReportReqVO){
        Long userId = UserContext.getUserId();
        return ResponseEntity.ok(homesickLogService.report(homesickReportReqVO, userId));
    }

}
