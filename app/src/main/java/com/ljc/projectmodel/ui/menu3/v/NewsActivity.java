package com.ljc.projectmodel.ui.menu3.v;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljc.baselibrary.widgets.DividerItemDecoration;
import com.ljc.projectmodel.R;
import com.ljc.projectmodel.ui.MainActivity;
import com.ljc.projectmodel.widgets.CommTitle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lijiacheng on 16/7/14.
 */
public class NewsActivity extends MainActivity implements NewsView {
    @BindView(R.id.lv_show)
    RecyclerView lv_show;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fa_view)
    FloatingActionButton fa_view;
    ArrayList<String> list;

    public NewsActivity() {
        super(R.layout.act_news, true);
    }

    @Override
    public void initViews() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item" + i);
        }

        lv_show.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lv_show.setLayoutManager(new LinearLayoutManager(this));
        lv_show.setItemAnimator(new DefaultItemAnimator());
        lv_show.setAdapter(new Adapter());

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

    }

    @Override
    public void refreshViews() {
        CommTitle title = new CommTitle(this);
        title.setTitle(getResources().getString(R.string.text_news));
    }

    @OnClick({R.id.fa_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fa_view:
                Snackbar.make(fa_view, "gggggg", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(((LayoutInflater) NewsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.lv_item_news, null));
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv_content.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_content)
            TextView tv_content;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
