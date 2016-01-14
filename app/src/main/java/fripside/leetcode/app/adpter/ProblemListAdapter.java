package fripside.leetcode.app.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fripside on 1/14/16.
 */
public class ProblemListAdapter extends RecyclerView.Adapter<ProblemListAdapter.ProblemItem> {


    @Override
    public ProblemItem onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProblemItem problemItem, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProblemItem extends RecyclerView.ViewHolder {

        public ProblemItem(View itemView) {
            super(itemView);
        }
    }
}
