package fripside.leetcode.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.query.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fripside.leetcode.app.R;
import fripside.leetcode.app.adpter.ProblemListAdapter;
import fripside.leetcode.app.data.model.Pivot;
import fripside.leetcode.app.data.model.Problem;
import fripside.leetcode.app.data.model.Similar;
import fripside.leetcode.app.data.model.Tag;
import fripside.leetcode.app.utils.L;


// 设计模仿这个：https://itunes.apple.com/us/app/leetcode/id874923973?mt=8
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rev_problems)
    RecyclerView revProblems;

    ProblemListAdapter problemListAdapter;

    private List<Problem> problems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        testDb();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        revProblems.setLayoutManager(linearLayoutManager);
//        ProblemListAdapter.DividerLine dividerLine = new ProblemListAdapter.DividerLine(ProblemListAdapter.DividerLine.VERTICAL);
//        dividerLine.setSize(1);
//        dividerLine.setColor(0xFFDDDDDD);
//        revProblems.addItemDecoration(dividerLine);
//        revProblems.addItemDecoration(new Div(this, DividerItemDecoration.VERTICAL_LIST));
        loadData();
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

    private void loadData() {
        problems = new Select().from(Problem.class).execute();
        L.d("testDb", "" + problems.size());
        for (Problem problem : problems) {
            L.d("testDb", problem.title);
        }
        problemListAdapter = new ProblemListAdapter(this, problems);
        revProblems.setAdapter(problemListAdapter);
        problemListAdapter.notifyDataSetChanged();
    }

    private void testDb() {
        Problem problem = new Problem();
        problem.title = "问题测试";
        problem.isSolve = true;
        problem.isStar = true;
        problem.accept = 100;
        problem.url = "SSS";
        problem.submission = 123132;
        problem.content = "html content C++";
        problem.save();
        Tag tag = new Tag("ABC");
        tag = tag.saveOrUpdate();
        new Pivot(problem, tag).save();
        tag = new Tag("CDE");
        tag.content = "CDE";
        tag = tag.saveOrUpdate();
        new Pivot(problem, tag).save();
        L.d("testDb", problem.tags().toString()); // except: [ABC, CDE]
        for (Tag pv : problem.tags()) {
            L.d("testDb", pv.toString());
        }
        Problem B = new Problem();
        B.title = "相似问题";
        B.save();
        new Similar(problem, B).save();
        L.d("testDb", problem.similar().toString());
        L.d("testDb", "Equal: " + (B.getId() ==  problem.similar().get(0).getId())); // except: true
    }
}
