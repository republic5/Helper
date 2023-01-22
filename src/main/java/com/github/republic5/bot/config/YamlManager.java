package com.github.republic5.bot.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlManager {
    private Map<String, Object> map;
    private final Yaml yaml;
    private final InputStream in;

    public YamlManager(InputStream in) {
        yaml = new Yaml();
        map = yaml.load(in);
        this.in = in;

    }

    public void reload() {
        map = yaml.load(in);

    }

    public Object get(String path) {
        return map.get(path);

    }

    public String getString(String path) {
        return (String) get(path);

    }

    public Integer getInteger(String path) {
        return (Integer) get(path);

    }

}
