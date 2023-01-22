package com.github.republic5.bot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.awt.*;

public class HelpCommand implements ICommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "get help";
    }

    @Override
    public void execute(SlashCommandInteraction ctx) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Command help");

        eb.setColor(Color.green);

        for (ICommand command : SlashCommandListener.getCommands()) {
            eb.addField(command.getName(), command.getDescription(), false);

        }

        ctx.replyEmbeds(eb.build()).queue();

    }
}
