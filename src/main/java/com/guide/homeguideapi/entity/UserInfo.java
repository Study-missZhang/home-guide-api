package com.guide.homeguideapi.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户基础信息实体类，对应数据库表 user_info
 *
 * @author zky
 */
@Data
public class UserInfo {

    /** 主键ID */
    private Long id;

    /** 微信用户唯一标识 */
    private String openid;

    /** 绑定的手机号 */
    private String phone;

    /** 家-纬度 (如: 34.2658) */
    private BigDecimal homeLatitude;

    /** 家-经度 (如: 108.9431) */
    private BigDecimal homeLongitude;

    /** 家的文字地址描述 */
    private String homeAddress;

    /** 注册时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}
