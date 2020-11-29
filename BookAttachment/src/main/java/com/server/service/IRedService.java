package com.server.service;


import com.server.dto.RedPacketDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 红包记录服务
 * @author: zhonglinsen
 * @date: 2019/3/18
 */
public interface IRedService {

    void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception;

    void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception;

}
