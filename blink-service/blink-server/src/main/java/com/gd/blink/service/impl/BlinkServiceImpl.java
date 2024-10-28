package com.gd.blink.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gd.blink.mapper.BlinkGeneralMapper;
import com.gd.blink.mapper.BlinkMapper;
import com.gd.blink.pojo.Blink;
import com.gd.blink.pojo.BlinkGeneral;
import com.gd.blink.pojo.SaveBlinkBO;
import com.gd.blink.pojo.UpdateBlinkBO;
import com.gd.blink.service.BlinkService;
import com.gd.core.exception.MapperException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/10/5
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BlinkServiceImpl extends ServiceImpl<BlinkMapper, Blink> implements BlinkService {
    @Resource
    private BlinkMapper blinkMapper;
    @Resource
    private BlinkGeneralMapper blinkGeneralMapper;
    @Override
    public void create(SaveBlinkBO blinkBO) {
        Blink blink = new Blink();
        BeanUtils.copyProperties(blinkBO, blink);
        blink.setCreateTime(new Timestamp(System.currentTimeMillis()));
        blinkMapper.insert(blink);
        BlinkGeneral general = new BlinkGeneral();
        general.setBlinkId(blink.getId());
        general.setScore(getRaring(blink));
        blinkGeneralMapper.insert(general);
    }

    @Override
    public void remove(int id, int userId) {
        LambdaQueryWrapper<Blink> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blink::getId, id).eq(Blink::getUserId, userId);
        if (blinkMapper.selectCount(wrapper) != 1) {
            throw new MapperException("动态查询失败", "id -> " + id);
        }
        blinkGeneralMapper.deleteById(id);
    }

    @Override
    public void update(UpdateBlinkBO blinkBO) {
        LambdaUpdateWrapper<Blink> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Blink::getId, blinkBO.getId()).eq(Blink::getUserId, blinkBO.getUserId());
        wrapper.set(Blink::getContent, blinkBO.getContent());
        if (blinkMapper.update(null, wrapper) != 1) {
            throw new MapperException("动态更新失败", blinkBO);
        }
    }

    private double getRaring(Blink blink) {
        return blink.getContent().length();
    }
}
