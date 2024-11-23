package com.yz.utils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class MyBatisUtils {
    private static final SqlSessionFactory factory;

    static {
        try {
            Configuration configuration
                    =  new XMLConfigBuilder(Resources.getResourceAsStream("mybatis-config.xml"), null, null)
                    .parse();

            //构建mybatis-plus需要的globalconfig
            GlobalConfig globalConfig = new GlobalConfig();

            //此参数会自动生成实现baseMapper的基础方法映射
            globalConfig.setSqlInjector(new DefaultSqlInjector());
            //设置id生成器
            globalConfig.setIdentifierGenerator(new DefaultIdentifierGenerator());
            //设置超类mapper
            globalConfig.setSuperMapperClass(BaseMapper.class);


            MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();

            mybatisConfiguration.setEnvironment(configuration.getEnvironment());

            mybatisConfiguration.setMapUnderscoreToCamelCase(configuration.isMapUnderscoreToCamelCase());

            mybatisConfiguration.setCacheEnabled(configuration.isCacheEnabled());

            for (Class<?> mapper : configuration.getMapperRegistry().getMappers()) {
                mybatisConfiguration.addMapper(mapper);
            }

            mybatisConfiguration.setLogImpl(configuration.getLogImpl());

            mybatisConfiguration.addInterceptor(initInterceptor());

            //给configuration注入GlobalConfig里面的配置
            GlobalConfigUtils.setGlobalConfig(mybatisConfiguration, globalConfig);


            factory = new SqlSessionFactoryBuilder().build(mybatisConfiguration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static SqlSession getSession(boolean autoCommit){
        return factory.openSession(autoCommit);
    }


    private static Interceptor initInterceptor() {
        //创建mybatis-plus插件对象
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //构建分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setOverflow(true);
        paginationInnerInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
