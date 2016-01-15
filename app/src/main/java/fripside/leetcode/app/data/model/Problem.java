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

    @Column(name = "Difficulty")
    public String difficulty;

    //收藏
    @Column(name = "IsStar")
    public boolean isStar;

    @Column(name = "IsSolve")
    public boolean isSolve;

    @Column(name = "Content")
    public String content;

    @Column(name = "Url")
    public String url;

    @Column(name = "Answer")
    public String answer;

    // 通过题目url来查找题目比用title更精确
    public Problem findProblemByUrl(String url) {
        return new Select().from(Problem.class).where("Problems.Url = ?", url).executeSingle();
    }

    public List<Problem> similar() {
        From from = new Select().from(Problem.class).join(Similar.class).on("Problems.Id == Similarities.problemSimilar").where("Similarities.ProblemOrigin = ?", getId());
        return from.execute();
    }

    public List<Tag> tags() {
        From from  = new Select().from(Tag.class).join(Pivot.class).on("Tags.Id = Pivots.Tag").where("Pivots.Problem = ?", getId());
        L.d("testDb", from.toSql());
        return from.execute();
    }

    public Problem saveOrUpdate() {

        return this;
    }
}
