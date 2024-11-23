package com.yz;

import com.yz.entity.Country;
import com.yz.mapper.TestMapper;
import com.yz.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import java.io.FileNotFoundException;

@SuppressWarnings("ALL")
public class MybatisTest {
    public static void main(String[] args) throws FileNotFoundException {

        try(SqlSession session = MyBatisUtils.getSession(true)) {
            TestMapper mapper = session.getMapper(TestMapper.class);
            System.out.println(mapper.getClass());
//            System.out.println(mapper.selectCountryByName0("Germany"));
        }
    }
}
