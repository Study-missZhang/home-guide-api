package com.guide.homeguideapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guide.homeguideapi.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 Mapper 接口
 * 继承 BaseMapper 获得单表 CRUD 能力，无需手写 SQL
 *
 * @author zky
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
