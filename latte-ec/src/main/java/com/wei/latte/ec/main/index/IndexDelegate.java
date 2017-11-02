package com.wei.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.ec.R;
import com.wei.latte.ec.R2;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.ISuccess;
import com.wei.latte.ui.recycler.MultipleFields;
import com.wei.latte.ui.recycler.MultipleItemEntity;
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
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        RestClient.builder()
                .url("http://192.168.0.100:3000/goods_detail_data_2")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtil.writeLog(response);

                        final IndexDataConvert convert = new IndexDataConvert();
                        convert.setJsonData(response);
                        final ArrayList<MultipleItemEntity> list = convert.convert();
                        final String image = list.get(1).getField(MultipleFields.IMAGE_URL);
                        Toast.makeText(getContext(), image, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        mRefreshHandler.firstPage("goods_detail_data_1");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
