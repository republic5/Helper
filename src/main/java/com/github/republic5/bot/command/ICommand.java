package com.github.republic5.bot.command;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface ICommand {

    /**
     * get command name.
     *
     * @return command name
     */
    String getName();

    /**
     * get command description.
     *
     * @return command description
     */
    String getDescription();

    /**
     * command execute method.
     *
     * @param ctx SlashCommandInteraction
     */
    void execute(SlashCommandInteraction ctx);

}
