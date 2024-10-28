package com.gd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gd.server.mapper.CityMapper;
import com.gd.server.mapper.ProvinceMapper;
import com.gd.server.pojo.City;
import com.gd.server.pojo.Province;
import com.gd.server.pojo.University;
import com.gd.server.mapper.UniversityMapper;
import com.gd.server.service.IUniversityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-29
 */
@Slf4j
@Service
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, University> implements IUniversityService {

    @Resource
    private ProvinceMapper provinceMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private UniversityMapper universityMapper;

    @Override
    public List<Province> getProvinceList() {
        return provinceMapper.selectList(null);
    }

    @Override
    public List<City> getCityList(int provinceId) {
        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(City::getProvinceId, provinceId);
        return cityMapper.selectList(wrapper);
    }

    @Override
    public List<University> getUniversityList(int cityId) {
        LambdaQueryWrapper<University> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(University::getCityId, cityId);
        return universityMapper.selectList(wrapper);
    }

    @Override
    public String getUniversityName(int schoolCode) {
        LambdaQueryWrapper<University> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(University::getCode, schoolCode);
        University university = universityMapper.selectOne(wrapper);
        return university == null ? null : university.getName();
    }
}
