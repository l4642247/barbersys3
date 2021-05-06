package cn.nicecoder.barbersys.handler;

import cn.nicecoder.barbersys.enums.CommonEnum;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus填充器
 * @author: longt
 * @date: 2020/12/21 下午7:45
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final static Integer CLICK_NUM_INIT = 0;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("status", CommonEnum.NORMAL.getCode(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
