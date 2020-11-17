package Commands;

import Database.BadCount;
import Database.NeutralCount;
import Tools.Maths;
import Tools.Scoring;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.text.DecimalFormat;

public class checkRating implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args.length > 0) {
            String word = args[0];
            String neutral, bad;

            if (NeutralCount.propExist(word)) {
                neutral = NeutralCount.load(word);
            } else {
                neutral = "0";
            }

            if (BadCount.propExist(word)) {
                bad = BadCount.load(word);
            } else {
                bad = "0";
            }

            int score = Scoring.getScore(word);

            String status;
            if (score > 0) {
                status = "Fine";
            } else if (score < 0) {
                status = "Bad";
            } else if (score == 0) {
                status = "Neutral";
            } else {
                status = "undefined";
            }

            double confidence = 0, obtained;

            if(status.contains("Fine")) {
                obtained = Integer.parseInt(neutral);
            }else if (status.contains("Bad")) {
                obtained = Integer.parseInt(bad);
            }else if(status.contains("Neutral")) {
                obtained = 100;
            }else {
                obtained = 0;
            }
            if(Integer.parseInt(neutral) == 0 || Integer.parseInt(bad) == 0 || score == 0) {
                confidence = 100.0;
            }else {
                confidence = Maths.calculatePercentage(obtained, Double.parseDouble(neutral) + Double.parseDouble(bad));
                DecimalFormat df = new DecimalFormat("#.##");
                confidence = Double.parseDouble(df.format(confidence));
            }

            event.getTextChannel().sendMessage("Rating for ``" + word + "``\n" +
                    "```\n" +
                    "Rating:        " + score + "\n" +
                    "Neutral:       " + neutral + "\n" +
                    "Bad:           " + bad + "\n" +
                    "Confidence:    " + confidence + "%\n" +
                    "```\n" +
                    "Word Status: **" + status + "**").queue();
        }else {
            event.getTextChannel().sendMessage("Please include a Word!").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String Description() {
        return "Check the Rating of any Word.";
    }

    @Override
    public String Example() {
        return "check fuck";
    }

    @Override
    public String Usage() {
        return "check <word>";
    }

    @Override
    public String Permissions() {
        return null;
    }
}
