package Listeners;

import Tools.Scoring;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Punisher extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String[] words = event.getMessage().getContentRaw().split(" ");
        for (int i = 0; i < words.length; i++) {
            if (Scoring.getScore(words[i]) < 0) {
                event.getTextChannel().sendMessage("The Codex Astartes does not support this action!").queue();
                i = 124124;
            }
        }

    }
}
