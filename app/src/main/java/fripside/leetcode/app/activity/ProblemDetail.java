package fripside.leetcode.app.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.activeandroid.query.Select;

import butterknife.Bind;
import butterknife.ButterKnife;
import fripside.leetcode.app.R;
import fripside.leetcode.app.data.model.Problem;
import fripside.leetcode.app.utils.L;

/**
 * Created by fripside on 1/15/16.
 */
public class ProblemDetail extends AppCompatActivity {

    @Bind(R.id.wv_problem)
    WebView wvProblem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);
        ButterKnife.bind(this);
        wvProblem.getSettings().setJavaScriptEnabled(true);
        wvProblem.setWebChromeClient(new WebChromeClient());
        wvProblem.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        loadProblem();
        Toast.makeText(this, "loadProblem" + "loadProblem onCreate", Toast.LENGTH_LONG);
    }

    private void loadProblem() {
        Long pid = getIntent().getLongExtra("Pid", 0L);
        L.i("loadProblem", "loadProblem" + pid);
        if (pid > 0) {
            Problem problem = new Select().from(Problem.class).where("Id = ?", pid).executeSingle();
            L.i("loadProblem", problem.content);
            String content = problem.content.replace("\\n", "\n");
            wvProblem.loadDataWithBaseURL("file:///android_assets/", content, "text/html", "utf-8", null);
        }
    }

}
