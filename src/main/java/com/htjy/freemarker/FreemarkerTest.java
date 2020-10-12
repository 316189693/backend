package com.htjy.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By willz
 * Date : 2020/10/12
 * Time: 10:11
 */
public class FreemarkerTest {
    private static final String TEMPLATE_PATH = "src/main/java/com/htjy/freemarker/ftl";
    private static final String CLASS_PATH = "src/main/java/com/htjy/freemarker/out";
    public static void main(String[] args) throws Exception {
        File targetFile = new File(CLASS_PATH+"\\"+"User.txt");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        try(
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));

        ){
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            configuration.setDirectoryForTemplateLoading(new File((TEMPLATE_PATH)));
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("classPath", "com.htjy.freemarker.out");
            dataMap.put("className", "User");
            List<Column> columns = new ArrayList<>();
            Column column1 = new Column();
            column1.setName("age");
            column1.setType("int");
            columns.add(column1);
            Column column2 = new Column();
            column2.setName("numn");
            column2.setType("int");
            columns.add(column2);
            dataMap.put("columns", columns);
            Template template = configuration.getTemplate("VoTemplate.ftl");
            ObjectWrapper objectWrapper = new DefaultObjectWrapper(Configuration.VERSION_2_3_30);
            template.process(dataMap, out,objectWrapper);
        }


    }
}

