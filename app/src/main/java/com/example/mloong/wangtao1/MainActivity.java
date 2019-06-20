package com.example.mloong.wangtao1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView pull_refresh_list;
    Handler handler = new Handler();
    ArrayList<String> list = new ArrayList<>();
    private ListView refreshableView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i <50 ; i++) {
            list.add(i+"haha");
        }
        pull_refresh_list = findViewById(R.id.pull_refresh_list);
        pull_refresh_list.setRefreshing(true);
        refreshableView = pull_refresh_list.getRefreshableView();
        myAdapter = new MyAdapter();
        refreshableView.setAdapter(myAdapter);
        initPullRefresh();
    }

    private void initPullRefresh() {
        pull_refresh_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter.notifyDataSetChanged();
                                pull_refresh_list.onRefreshComplete();
                            }
                        },2000);

                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }


    //适配器数据
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
