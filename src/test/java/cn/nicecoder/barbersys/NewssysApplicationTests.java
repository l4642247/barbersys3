package cn.nicecoder.barbersys;

import cn.hutool.core.util.ObjectUtil;
import cn.nicecoder.barbersys.entity.News;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BarbersysApplicationTests {

    @Test
    void contextLoads() throws IllegalAccessException {

        News obj = new News();
        System.out.println(obj);

        System.out.println(obj);
        System.out.println(ObjectUtil.isAllEmpty(obj));
    }

}
