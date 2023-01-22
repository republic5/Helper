package com.github.republic5.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener extends ListenerAdapter {

    //commands list
    private static final List<ICommand> commands = new ArrayList<>();

    public SlashCommandListener() {
        // slash commands
        commands.add(new UserCommand());
        commands.add(new ServerCommand());
        commands.add(new HelpCommand());

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String name = event.getName();

        for (ICommand command:commands) {
            // execute command
            if (name.equals(command.getName())) {
                command.execute(event);

            }

        }

    }

    public static List<ICommand> getCommands() {
        return commands;

    }

}
