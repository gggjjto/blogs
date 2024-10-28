package com.gd.gateway.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gd.gateway.mapper.VisitLogMapper;
import com.gd.gateway.pojo.VisitRecord;
import org.springframework.stereotype.Service;

/**
 * 访问日志Service类
 *
 * @author 阿杆
 * @version 1.0
 * @date 2023/1/19 22:25
 */
@Service
public class VisitLogService extends ServiceImpl<VisitLogMapper, VisitRecord> {

}
