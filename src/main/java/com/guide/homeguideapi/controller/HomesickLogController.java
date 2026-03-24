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

}
