package com.cooper;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class AutoGenerateCode {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取当前项目路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Cooper");
        gc.setOpen(false);//是否打开资源管理器
        gc.setFileOverride(true);//是否覆盖原来生成的
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setServiceName("%sService");//去除service的I前缀

        //设置实体类的代码
        gc.setDateType(DateType.ONLY_DATE);//设置日期类型
        gc.setIdType(IdType.ID_WORKER);//默认是初始的id生成算法，不变
        gc.setSwagger2(true);//配置swagger2

        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        //设置模块路径为com.cooper.learn
        pc.setModuleName("learn");//设置模块名称
        pc.setParent("com.cooper");
        pc.setEntity("entity");//实体类文件夹名称
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setService("service");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("person");//设置需要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除

        //自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtUpdate = new TableFill("gmt_update", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList();
        list.add(gmtCreate);
        list.add(gmtUpdate);
        strategy.setTableFillList(list);

        //设置乐观锁
        strategy.setVersionFieldName("version");

        strategy.setRestControllerStyle(true);//使用Restful风格
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        mpg.execute();
    }
}
