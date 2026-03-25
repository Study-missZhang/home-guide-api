package com.guide.homeguideapi.service;

import com.guide.homeguideapi.controller.vo.HomeLocationRespVO;
import com.guide.homeguideapi.controller.vo.HomesickReportReqVO;
import com.guide.homeguideapi.entity.HomesickLog;
import java.util.List;
import java.util.Optional;

/**
 * 想家记录 Service 接口，定义想家记录相关业务逻辑
 *
 * @author zky
 */
public interface HomesickLogService {


    /**
     * 上报想家记录
     *
     * @param homesickReportReqVO
     * @param userId
     * @return
     */
    Boolean report(HomesickReportReqVO homesickReportReqVO, Long userId);
}
