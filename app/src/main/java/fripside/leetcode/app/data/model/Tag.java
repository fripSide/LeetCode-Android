package fripside.leetcode.app.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by fripside on 1/15/16.
 */
@Table(name = "Tags")
public class Tag extends Model {

    public Tag() {}

    public Tag(String content) {
        this.content = content;
    }

    @Column(name = "Content")
    public String content;

    @Column(name = "Url")
    public String url;

    public void saveOrUpdate() {
        Tag tag = new Select().from(Tag.class).where("Content = ?", content).executeSingle();
        if (tag == null) {
            save();
        }
    }

    @Override
    public String toString() {
        return content;
    }
}
