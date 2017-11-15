package com.wei.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wei.latte.ec.R;
import com.wei.latte.util.log.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder>{

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
//                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_text, "testa");
                LogUtil.writeLog(item.getmText());
//                helper.setText(R.id.tv_arrow_value, item.getmValue());
                helper.setText(R.id.tv_arrow_value, "testb");
                LogUtil.writeLog(item.getmValue());
                break;
            default:
                break;
        }
    }
}
