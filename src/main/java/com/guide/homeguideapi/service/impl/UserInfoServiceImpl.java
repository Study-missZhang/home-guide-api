package com.guide.homeguideapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.guide.homeguideapi.context.UserContext;
import com.guide.homeguideapi.controller.vo.HomeLocationRespVO;
import com.guide.homeguideapi.controller.vo.SetHomeReqVO;
import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.mapper.UserInfoMapper;
import com.guide.homeguideapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息 Service 实现类
 *
 * @author zky
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    /**
     * 设置当前用户家的位置
     * userId 从 UserContext 中获取
     *
     * @param reqVO 家的位置信息
     * @param userId 当前用户ID
     */
    @Override
    public boolean setHomeLocation(SetHomeReqVO reqVO, Long userId) {
        int rows = userInfoMapper.update(new LambdaUpdateWrapper<UserInfo>()
                .eq(UserInfo::getId, userId)
                .set(UserInfo::getHomeLatitude, reqVO.getHomeLatitude())
                .set(UserInfo::getHomeLongitude, reqVO.getHomeLongitude())
                .set(UserInfo::getHomeAddress, reqVO.getHomeAddress())
        );
        return rows > 0;
    }

    /**
     * 获取家的位置
     *
     * @param userId
     * @return
     */
    @Override
    public HomeLocationRespVO getHomeLocation(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if(userInfo == null) {
            return null;
        }
        HomeLocationRespVO respVO = new HomeLocationRespVO();
        respVO.setHomeLongitude(userInfo.getHomeLongitude());
        respVO.setHomeLatitude(userInfo.getHomeLatitude());
        respVO.setHomeAddress(userInfo.getHomeAddress());
        return respVO;
    }
}
