package com.wei.latte.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.ec.R;
import com.wei.latte.ec.R2;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.ISuccess;
import com.wei.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/20.
 */

public class AddressDelegate extends LatteDelegate implements ISuccess{

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("address")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }
}
