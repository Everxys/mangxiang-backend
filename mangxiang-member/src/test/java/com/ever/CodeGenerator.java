package com.ever;

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
// 代码自动生成器
public class CodeGenerator {
    public static void main(String[] args) {
// 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
// 配置策略
// 1、全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");//项目路径,获取用户目录
//        gc.setOutputDir(projectPath+"/src/main/java");//输出到这个目录下的/src/main/java下
        gc.setOutputDir("D:\\JAVA\\mangxiang-backend\\mangxiang-member\\src\\main\\java");
        gc.setAuthor("Everxys");//设置作者信息
        gc.setOpen(false);//是否打开资源管理器
        gc.setFileOverride(true); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去Service的I前缀
        gc.setIdType(IdType.ASSIGN_ID);//主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//生成swagger
        mpg.setGlobalConfig(gc);
//2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mangxiang_ums?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
//3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("member");//新建的包名
        pc.setParent("com.ever");//放在哪个包下
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");   
        pc.setController("controller");
        mpg.setPackageInfo(pc);
//4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("mx_");
        //设置要映射的表名
        strategy.setInclude("mx_user","mx_admin");
                strategy.setNaming(NamingStrategy.underline_to_camel);//表名下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列名下划线转驼峰
        strategy.setEntityLombokModel(true); // 是否使用lombok；
        strategy.setLogicDeleteFieldName("deleted");//设置逻辑删除字段
// 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified",
                FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
// 乐观锁
        strategy.setVersionFieldName("version");//设置乐观锁字段
        strategy.setRestControllerStyle(true);//是否使用Restful风格
        strategy.setControllerMappingHyphenStyle(true);//localhost:8080/hello_id_2

        mpg.setStrategy(strategy);
        mpg.execute(); //执行
    }
}