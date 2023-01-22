package com.github.republic5.bot;

import com.github.republic5.bot.config.ConfigManager;

import java.util.logging.Logger;

public class Main {
    private static ConfigManager config;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");

        logger.info("Initialize...");

        config = new ConfigManager();

        new AbwehrBot(config.getString("token"));

        logger.info("Successfully.");

    }

    public static ConfigManager getConfig() {
        return config;
    }

}
