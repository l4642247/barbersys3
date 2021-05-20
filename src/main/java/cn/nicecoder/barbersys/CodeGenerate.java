package cn.nicecoder.barbersys;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 * @author: xxxxx
 * @date: 2020/12/18 上午11:22
 */
public class CodeGenerate {
    private static final String DB_URL = "jdbc:mysql://nicecoder.cn:3306/project?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    private static final String DB_DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Cnm.0001+";

    public static void main(String[] args) {
        //0、需要构建一个代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //1、生成器配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xxxxx");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        autoGenerator.setGlobalConfig(gc);

        //2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setDriverName(DB_DRIVERNAME);
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("barbersys");
        pc.setParent("cn.nicecoder");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("barber_user_role");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("deleted");
        //5、自动填充配置
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //6、乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }
}
