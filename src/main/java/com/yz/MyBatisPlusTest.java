package com.yz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yz.plus.entity.City;
import com.yz.plus.mapper.CityMapper;
import com.yz.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;



public class MyBatisPlusTest {
    public static void main(String[] args) {
        try(SqlSession session = MyBatisUtils.getSession(true)){
            CityMapper mapper = session.getMapper(CityMapper.class);

            QueryWrapper<City> wrapper = Wrappers
                    .<City>query()
                    .eq("CountryCode", "ITA")
                            .orderByDesc("Population");
            mapper.selectPage(new Page<>(1,2), wrapper);
        }
    }
}
