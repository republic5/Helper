package com.github.republic5.bot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ServerCommand implements ICommand {
    @Override
    public String getName() {
        return "server";
    }

    @Override
    public String getDescription() {
        return "get Server info";
    }

    @Override
    public void execute(SlashCommandInteraction ctx) {
        Guild guild = ctx.getGuild();

        String name = guild.getName();
        String iconUrl = guild.getIconUrl();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM/dd HH:mm");

        String owner = guild.getOwner().getUser().getName();
        String timeCreated = guild.getTimeCreated().format(formatter);
        String members = String.valueOf(guild.getMemberCount());
        String textChannels = String.valueOf(guild.getTextChannels().size());
        String voiceChannels = String.valueOf(guild.getVoiceChannels().size());

        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(name);
        eb.setThumbnail(iconUrl);
        eb.setColor(Color.green);

        eb.addField("Owner", owner, false);
        eb.addField("Since", timeCreated, false);
        eb.addField("Members", members, false);
        eb.addField("Text channels", textChannels, false);
        eb.addField("Voice channels", voiceChannels, false);

        ctx.replyEmbeds(eb.build()).queue();

    }
}
