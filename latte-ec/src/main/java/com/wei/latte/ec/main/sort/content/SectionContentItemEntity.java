package com.wei.latte.ec.main.sort.content;

/**
 * Created by Administrator on 2017/11/6.
 */

public class SectionContentItemEntity {

    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodThumb = null;

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        this.mGoodsId = goodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        this.mGoodsName = goodsName;
    }

    public String getGoodThumb() {
        return mGoodThumb;
    }

    public void setGoodThumb(String goodThumb) {
        this.mGoodThumb = goodThumb;
    }
}
