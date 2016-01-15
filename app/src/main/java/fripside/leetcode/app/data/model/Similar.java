package fripside.leetcode.app.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by fripside on 1/15/16.
 */
@Table(name = "Similarities")
public class Similar extends Model {

    public Similar() {}

    public Similar(Problem O, Problem S) {
        problemOrigin = O;
        problemSimilar = S;
    }

    @Column(name = "ProblemOrigin")
    public Problem problemOrigin;

    @Column(name = "ProblemSimilar")
    public Problem problemSimilar;
}
