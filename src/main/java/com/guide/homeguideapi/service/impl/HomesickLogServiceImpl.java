package com.guide.homeguideapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guide.homeguideapi.controller.vo.HomeLocationRespVO;
import com.guide.homeguideapi.controller.vo.HomesickReportReqVO;
import com.guide.homeguideapi.entity.HomesickLog;
import com.guide.homeguideapi.mapper.HomesickLogMapper;
import com.guide.homeguideapi.service.HomesickLogService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 想家记录 Service 实现类
 *
 * @author zky
 */
@Service
@RequiredArgsConstructor
public class HomesickLogServiceImpl implements HomesickLogService {

    @Resource
    private final HomesickLogMapper homesickLogMapper;

    @Override
    public Boolean report(HomesickReportReqVO homesickReportReqVO, Long userId) {
        HomesickLog homesickLog = new HomesickLog();
        homesickLog.setUserId(userId);
        homesickLog.setCurrentDistance(homesickReportReqVO.getCurrentDistance());
        homesickLog.setCreateTime(homesickReportReqVO.getCreateTime());
        return homesickLogMapper.insert(homesickLog) > 0;
    }
}
