package Tools;

import Database.BadCount;
import Database.NeutralCount;

public class Rating {

    public static String addNeutralScore(String word) {

        int count;
        if(NeutralCount.propExist(word)) {
            count = Integer.parseInt(NeutralCount.load(word)) + 1;
        }else {
            count = 1;
        }
        return String.valueOf(count);
    }

    public static String addBadScore(String word) {

        int count;
        if(BadCount.propExist(word)) {
            count = Integer.parseInt(BadCount.load(word)) + 1;
        }else {
            count = 1;
        }
        return String.valueOf(count);
    }

}
