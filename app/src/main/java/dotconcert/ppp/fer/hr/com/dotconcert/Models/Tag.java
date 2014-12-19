package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 11.11.2014..
 */
public class Tag {
    private int idTag;
    private String tag;

    public Tag(int idTag, String tag) {
        this.idTag = idTag;
        this.tag = tag;
    }

    public int getIdTag() {
        return idTag;
    }

    public String getTag() {
        return tag;
    }
}
