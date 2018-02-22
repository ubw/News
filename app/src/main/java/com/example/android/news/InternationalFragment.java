package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InternationalFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsInfo>>{

    private static final String REQUEST_URL = "https://content.guardianapis.com/search?tag=world/world&api-key=test";
    private ArrayAdapter<NewsInfo> mAdapter;
    private View internationalView;
    private TextView mEmptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        internationalView = null;
        internationalView = inflater.inflate(R.layout.news_layout, container, false);

        //找到listview的layout
        ListView newsListView = (ListView) internationalView.findViewById(R.id.list);

        //设置空视图
        ListView basketballListView = (ListView) internationalView.findViewById(R.id.list);
        mEmptyView = (TextView) internationalView.findViewById(R.id.empty_view);
        basketballListView.setEmptyView(mEmptyView);

        //设置适配器
        mAdapter = new NewsInfoArrayAdapter(getActivity(), new ArrayList<NewsInfo>());
        newsListView.setAdapter(mAdapter);

        //判断网络是否连接
        ConnectivityManager cm =
                (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Log.d("InternationalFragment", "===========test inconnected = "+ isConnected +"-===============");
        if (isConnected == true) {
            Log.d("InternationalFragment", "===========initLoader============");
            //第3个加载器
            getActivity().getLoaderManager().initLoader(2, null, this).forceLoad();
        } else {
            mEmptyView = (TextView) internationalView.findViewById(R.id.empty_view);
            mEmptyView.setText(getResources().getString(R.string.no_network));
            ProgressBar progressBar = (ProgressBar) internationalView.findViewById(R.id.progressbar);
            progressBar.setVisibility(View.GONE);
        }

        //设置点击监听
        basketballListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //找到当前点击的位置
                NewsInfo newInfo = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(newInfo.getmUrl()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return internationalView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("InternationalFragment", "===========onCreate============");
        //重新加载第3个加载器
        getActivity().getLoaderManager().restartLoader(2, null, this).forceLoad();
    }

    @Override
    public Loader<List<NewsInfo>> onCreateLoader(int id, Bundle args) {
        Log.d("InternationalFragment", "===========onCreateLoader============");
        return new NewsAsyncTaskLoader(getActivity(), REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsInfo>> loader, List<NewsInfo> newsInfos) {
        //指示器隐藏
        ProgressBar progressBar = (ProgressBar) internationalView.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if (newsInfos == null) {
            return;
        }
        //设置空视图文字
        mEmptyView.setText(getResources().getString(R.string.no_news_found));
        // 清除之前数据的适配器
        mAdapter.clear();
        //如果newsInfo不为空,添加到适配器
        if (newsInfos != null && !newsInfos.isEmpty()) {
            mAdapter.addAll(newsInfos);
        }
        Log.d("InternationalFragment", "===========onLoadFinished============");
    }

    @Override
    public void onLoaderReset(Loader<List<NewsInfo>> loader) {
        Log.d("InternationalFragment", "========onLoaderReset==============");
        mAdapter.clear();
    }

}
