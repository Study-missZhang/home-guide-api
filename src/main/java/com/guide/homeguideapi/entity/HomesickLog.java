package com.guide.homeguideapi.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 想家记录实体类，对应数据库表 homesick_log
 *
 * @author zky
 */
@Data
@TableName("homesick_log")
@Schema(description = "想家记录")
public class HomesickLog {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "关联 user_info 表的用户ID", example = "1")
    private Long userId;

    @Schema(description = "此时距离家的直线距离（公里）", example = "1024.50")
    private BigDecimal currentDistance;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "打开时间（想家时刻）", example = "2024-01-01T00:00:00")
    private LocalDateTime createTime;
}
