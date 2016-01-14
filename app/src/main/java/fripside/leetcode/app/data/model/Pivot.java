package fripside.leetcode.app.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by fripside on 1/15/16.
 */
@Table(name = "Pivots")
public class Pivot extends Model {

    public Pivot() {}

    public Pivot(Problem problem, Tag tag) {
        this.problem = problem;
        this.tag = tag;
    }

    @Column(name = "Problem")
    public Problem problem;

    @Column(name = "Tag")
    public Tag tag;
}
