package com.htjy.concurrent.dynamicSwitch;

import com.google.common.base.Objects;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

public class DynamicLoadProperties {
    private static class SingletonHoler{
        private static DynamicLoadProperties instance = new DynamicLoadProperties();
    }



    private DynamicLoadProperties(){}
    private static WatchService watchService;
    private static String fileName;
    private static Properties properties;
    private static Resource resource;
    static{
        try{
            fileName = "application.properties";
            resource = new ClassPathResource(fileName);
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(resource.getFile().getParent()).register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e){
            e.printStackTrace();
        }

        Thread watchThread = new Thread(() -> {
            while (true) {
                try {
                    WatchKey watchKey = watchService.take();
                    for(WatchEvent<?> event : watchKey.pollEvents()){
                        if(Objects.equal(event.context().toString(), fileName)){
                            properties = PropertiesLoaderUtils.loadProperties(resource);
                            break;
                        }
                    }
                    watchKey.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        watchThread.setDaemon(true);
        watchThread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try{
                watchService.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public static DynamicLoadProperties getInstance(){
        return SingletonHoler.instance;
    }
    public static Properties getProperties(){
        return properties;
    }
}
