package com.wangfuzeng;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.wangfuzeng.common.base.BaseServiceImpl;


import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Gen {
    private static DataSourceConfig dataSourceConfig() {
        String url = "jdbc:mysql://localhost:3306/workdb?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        return new DataSourceConfig.Builder(url, username, password).build();
    }

    public static void main(String[] args) {
        String proj = System.getProperty("user.dir") + "/src/main/java";
        String mapperXml = System.getProperty("user.dir") + "/src/main/resources/mappers";

        DataSourceConfig ds = dataSourceConfig();
        FastAutoGenerator.create(ds.getUrl(), ds.getUsername(), ds.getPassword())
                // 全局配置
                .globalConfig(builder ->
                        builder.outputDir(proj)
                                .author("王福增")
                                .disableOpenDir()
                                //.enableSwagger()
                                .fileOverride() //禁止文件覆盖
                                .build())
                // 策略配置 sample
                .strategyConfig((scanner, builder) ->
                        builder
                                .addTablePrefix("")//设置表前缀移除
                                .addInclude("employee")
                                .controllerBuilder()
                                .enableRestStyle()
                                .mapperBuilder()
                                .formatXmlFileName("%sDao")
                                .formatMapperFileName("%sDao")
                                .serviceBuilder()
                                .superServiceImplClass(BaseServiceImpl.class)
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                                .entityBuilder()
                                .formatFileName("%sPO")
                                .enableLombok())
                // 包配置
                .packageConfig(builder -> builder

                        .parent("com.wangfuzeng.employee")
                        .entity("po")
                        .mapper("dao")
                        .xml("dao")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .other("")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml,mapperXml))
                        .build())
                // 模板配置
                .templateConfig(builder -> builder
                        .service("/tplgen/service.java")
                        .serviceImpl("/tplgen/serviceImpl.java")
                        .controller("/tplgen/controller.java")
                        .entity("/tplgen/po.java")
                        .mapper("/tplgen/dao.java")
                        .mapperXml("/tplgen/xml.java")
                        .build()
                )
                // 注入配置
                .injectionConfig(builder -> builder
                        .beforeOutputFile((tableInfo, objectMap) -> {
                            String MyModelName = tableInfo.getEntityName().substring(0, tableInfo.getEntityName().length() - 2);
                            String myModelName = MyModelName.substring(0, 1).toLowerCase() + MyModelName.substring(1);
                            objectMap.put("myModelName", MyModelName);
                            objectMap.put("lowModelName", myModelName);
                            objectMap.put("DtoName", MyModelName + "DTO");
                            objectMap.put("dtoName", myModelName + "DTO");
                        })
                        .customMap(Collections.singletonMap("test", "baomidou"))
                        .customFile(new HashMap<String, String>() {{
                            //put("vo", "/tplgen/vo.java.vm");
                            put("dto", "/tplgen/dto.java.vm");
                        }})
                        .build())

                // 模板引擎的某个方法
                .templateEngine(new VelocityTemplateEngine() {
                    @Override
                    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
                        String entityName = tableInfo.getEntityName().substring(0, tableInfo.getEntityName().length() - 2);
                        String otherPath = getPathInfo(OutputFile.other);
                        customFile.forEach((key, value) -> {
                            //String fileName = String.format((otherPath + File.separator + "%s"), key);
                            String fileName = null;
                            if ("dto".equals(key)) {
                                fileName = String.format((otherPath + File.separator + "dto" + File.separator + entityName + "DTO.java"));
                            }
                            outputFile(new File(fileName), objectMap, value);
                        });
                    }
                })
                // 执行
                .execute();
    }
}
