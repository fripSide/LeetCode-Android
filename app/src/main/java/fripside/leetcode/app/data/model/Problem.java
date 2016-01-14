package fripside.leetcode.app.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import java.util.List;

import fripside.leetcode.app.utils.L;

/**
 * Created by fripside on 1/14/16.
 */
@Table(name = "Problems")
public class Problem extends Model {

    @Column(name = "No")
    public long number;

    @Column(name = "Title")
    public String title;

    @Column(name = "Accept")
    public long accept;

    @Column(name = "Submission")
    public long submission;

    //收藏
    @Column(name = "IsStar")
    public boolean isStar;

    @Column(name = "IsSolve")
    public boolean isSolve;

//    @Column(name = "Similar")
    public List<String> similar;

    @Column(name = "Url")
    public String url;

    public List<Tag> tags() {
        From from  = new Select().from(Tag.class).join(Pivot.class).on("Tags.Id = Pivots.Tag").where("Pivots.Problem = ?", getId());
        L.d("testDb", from.toSql());
        return from.execute();
    }
}
