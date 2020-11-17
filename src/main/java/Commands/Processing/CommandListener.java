package Commands.Processing;
import Database.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class CommandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        String prefix = Config.load("prefix");

        if (event.getMessage().getContentRaw().startsWith(prefix)
                && !Objects.equals(event.getMessage().getAuthor().getId(), event.getJDA().getSelfUser().getId())
                && !event.getMessage().getAuthor().isBot()) {

            try {
                CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw().toLowerCase(), event, prefix));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
