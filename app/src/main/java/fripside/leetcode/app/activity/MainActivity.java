package fripside.leetcode.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import fripside.leetcode.app.R;
import fripside.leetcode.app.data.model.Pivot;
import fripside.leetcode.app.data.model.Problem;
import fripside.leetcode.app.data.model.Tag;
import fripside.leetcode.app.utils.L;


// 设计模仿这个：https://itunes.apple.com/us/app/leetcode/id874923973?mt=8
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rev_problems)
    RecyclerView revProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        testDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d(this.getClass().getSimpleName(), "onDestroy");
    }

    private void testDb() {
        Problem problem = new Problem();
        problem.title = "问题测试";
        problem.isSolve = true;
        problem.isStar = true;
        problem.accept = 100;
        problem.submission = 123132;
//        problem.similar = Collections.singletonList("问题测试");
        problem.save();
        Tag tag = new Tag("ABC");
        tag.saveOrUpdate();
        new Pivot(problem, tag).save();
        tag = new Tag("CDE");
        tag.content = "CDE";
        tag.saveOrUpdate();
        new Pivot(problem, tag).save();
        L.d("testDb", problem.tags().toString());
        for (Tag pv : problem.tags()) {
            L.d("testDb", pv.toString());
        }

    }
}
