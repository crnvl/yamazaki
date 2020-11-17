package Tools;

import Database.BadCount;
import Database.NeutralCount;

public class Scoring {

    public static int getScore(String word) {
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

        int score = Integer.parseInt(neutral) - Integer.parseInt(bad);

        return score;
    }

}
