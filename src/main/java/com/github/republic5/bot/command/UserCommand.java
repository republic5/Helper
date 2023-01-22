package com.github.republic5.bot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

public class UserCommand implements ICommand {

    @Override
    public String getName() {
        return "user";
    }

    @Override
    public String getDescription() {
        return "get user info mentions.";
    }

    @Override
    public void execute(SlashCommandInteraction ctx) {
        Member member = ctx.getOptions().get(0).getAsMember();
        Guild guild = ctx.getGuild();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM/dd HH:mm");

        Color color = member.getColor() != null ? member.getColor() : Color.gray;
        String avatarUrl = member.getEffectiveAvatarUrl();
        String name = member.getUser().getName();
        String join = member.getTimeJoined().format(formatter);
        String create = member.getTimeCreated().format(formatter);
        String roles;
        StringBuilder roleAppender = new StringBuilder();
        member.getRoles().forEach(role -> roleAppender.append(role.getAsMention()));
        roles = roleAppender.toString();

        String status = null;

        switch (guild.getMember(member).getOnlineStatus()) {
            case ONLINE:
                status = ":green_circle: Online";
                break;
            case IDLE:
                status = ":yellow_circle: IDLE";
                break;
            case DO_NOT_DISTURB:
                status = ":red_circle: Do not disturb";
                break;
            case OFFLINE:
                status = ":black_circle: Offline";
                break;
            case INVISIBLE:
                status = ":white_circle: Invisible";
                break;
            case UNKNOWN:
                status = "? Unknown";

        }

        // not work?
        String clientName;
        AtomicReference<String> tmp = new AtomicReference<>();
        member.getActiveClients().forEach(clientType -> {
             switch (clientType) {
                 case WEB:
                     tmp.set("Web browse");
                     break;
                 case MOBILE:
                     tmp.set("Mobile App");
                     break;
                 case DESKTOP:
                     tmp.set("Desktop App");
                     break;
                 case UNKNOWN:
                     tmp.set("Unknown");
             }

         });
         clientName = tmp.get() != null ? tmp.get():"<None>";

        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(color);
        eb.setThumbnail(avatarUrl);

        eb.setTitle(name);
        eb.addField("Join in this server", join, false);
        eb.addField("Create account", create, false);
        eb.addField("Status", status, false);
        eb.addField("Roles", roles, false);
        eb.addField("Now client type", clientName, false);

        ctx.replyEmbeds(eb.build()).queue();

    }
}
