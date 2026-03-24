package com.guide.homeguideapi.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 想家记录实体类，对应数据库表 homesick_log
 *
 * @author zky
 */
@Data
public class HomesickLog {

    /** 主键ID */
    private Long id;

    /** 关联 user_info 表的 id */
    private Long userId;

    /** 此时距离家的直线距离（公里） */
    private BigDecimal currentDistance;

    /** 打开时间（想家时刻） */
    private LocalDateTime createTime;
}
