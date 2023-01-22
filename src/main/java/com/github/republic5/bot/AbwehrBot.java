package com.github.republic5.bot;

import com.github.republic5.bot.command.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AbwehrBot {
    private static JDA jda;

    public AbwehrBot(String token) {
        jda = JDABuilder
                .create(token, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES)
                .build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jda.addEventListener(new SlashCommandListener());

        jda.updateCommands().addCommands(
                new CommandDataImpl("help", "get help"
                ), new CommandDataImpl(
                        "user", "get use info").addOptions(new OptionData(OptionType.MENTIONABLE, "user", "target user", true
                        )
                ), new CommandDataImpl(
                        "server", "get server info")
        ).queue();

        final Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("project.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] messages = {MessageFormat.format("Abwehr {0}",
                properties.getProperty("version")),
                MessageFormat.format("{0} guilds", jda.getGuilds().size()),
                MessageFormat.format("{0} ping", jda.getGatewayPing()),
                "✠ Passwort Freiheit, Ziel Lysyanka, 2300 Stunden"};
        final int[] currentIndex = {0};

        new Timer().schedule(new TimerTask() {
                                 public void run() {
                                     jda.getPresence().setActivity(Activity.playing(messages[currentIndex[0]]));
                                     currentIndex[0] = (currentIndex[0] + 1) % messages.length;
                                 }
                             }, 0, TimeUnit.SECONDS.toMillis(8)
        );

    }

}
