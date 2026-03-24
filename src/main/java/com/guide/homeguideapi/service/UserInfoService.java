package com.guide.homeguideapi.service;

import com.guide.homeguideapi.controller.vo.SetHomeReqVO;
import com.guide.homeguideapi.entity.UserInfo;
import java.util.List;
import java.util.Optional;

/**
 * 用户信息 Service 接口，定义用户相关业务逻辑
 *
 * @author zky
 */
public interface UserInfoService {


    /**
     * 设置当前用户家的位置
     *
     * @param reqVO  家的位置信息（经纬度 + 地址）
     * @param userId 当前用户ID
     */
    boolean setHomeLocation(SetHomeReqVO reqVO, Long userId);
}
