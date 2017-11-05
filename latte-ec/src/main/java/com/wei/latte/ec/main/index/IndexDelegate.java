package com.wei.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.ec.R;
import com.wei.latte.ec.R2;
import com.wei.latte.ec.main.EcBottomDelegate;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.ISuccess;
import com.wei.latte.ui.recycler.BaseDecoration;
import com.wei.latte.ui.recycler.MultipleFields;
import com.wei.latte.ui.recycler.MultipleItemEntity;
import com.wei.latte.ui.refresh.PageBean;
import com.wei.latte.ui.refresh.RefreshHandler;
import com.wei.latte.util.log.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/10/31.
 */

@SuppressWarnings("DefaultFileTemplate")
public class IndexDelegate extends BottomItemDelegtate{

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConvert());
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index_data");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
