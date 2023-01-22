package com.github.republic5.bot.config;

import com.github.republic5.bot.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager extends YamlManager {


    public ConfigManager() {
        super(getConfig());

    }

    private static InputStream getConfig() {
        Path config = Paths.get("config.yml");

        InputStream in;

            try {
                if (Files.notExists(config)) {
                    Files.createFile(config);
                    Util.transfer(ClassLoader.getSystemResourceAsStream("config.yml"), Files.newOutputStream(config));
                }
                in = Files.newInputStream(config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        return in;

    }

}
