package com.dream.main.tabme.record;

import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.util.DreamUtils;
import com.github.snowdream.android.util.Log;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/11 00:42
 * 已揭晓
 */
public class MyDreamRecordUnFragmentItemPM implements ItemPresentationModel<MyDreamRecordUnInfo> {

    MyDreamRecordUnInfo info;

    private boolean circle = false;

    String name;//名称

    String tvZxrc;//总需人次

    String tvHdz;//获得者

    String tvDate;//揭晓时间


    @Override
    public void updateData(MyDreamRecordUnInfo myDreamRecordUnInfo, ItemContext itemContext) {
        info = myDreamRecordUnInfo;
    }

    public String getUrl() {
        return info.getUphoto() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getTvDate() {
//        return "揭晓时间：" + DreamUtils.formatSecTime(Long.valueOf(info.getQ_end_time()), "yyyy-MM-dd HH:mm");
        return "揭晓时间：";
    }

    public String getName() {
        return info.getShopname();
    }

    public String getTvZxrc() {
        return "总需人次：" + String.valueOf(info.getZongrenshu() + "人次");
    }

    public String getTvHdz() {
        return "获得者" + info.getQ_user();
    }

    public void onClicks(ClickEvent event) {

        Log.d("11111");
    }
}
