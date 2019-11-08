package com.twinya.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author twinya
 * @Date 2019/6/21 15:45
 */
public class MyPropertiesHandler implements PropertySourceLoader {
    private static final Logger logger = LoggerFactory.getLogger(MyPropertiesHandler.class);

    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties", "xml"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        ArrayList<PropertySource<?>> list = new ArrayList<>();
        Properties properties = getProperties(resource);
        if (!properties.isEmpty()) {
            list.add(new PropertiesPropertySource(name, properties));
        }
        return list;
    }

    private Properties getProperties(Resource resource) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = resource.getInputStream();
        properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        inputStream.close();
        return properties;
    }
}
