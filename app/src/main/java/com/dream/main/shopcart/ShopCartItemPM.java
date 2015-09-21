package com.dream.main.shopcart;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.shopcart.ShopCart;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.compoundbutton.CheckedChangeEvent;
import org.robobinding.widget.view.ClickEvent;

/**
 * @author yangll
 */
public class ShopCartItemPM implements ItemPresentationModel<Good>, HasPresentationModelChangeSupport {

    private Good good;
    //商品url
    private String url;
    //商品名称
    private String name;
    //总需剩余
    private String zongxu;
    //购买人次，金钱计算
    private String goumai;
    //个数
    private int count = 1;
    //是否选择支付
    private boolean check;

    PresentationModelChangeSupport changeSupport;

    public ShopCartItemPM() {
        changeSupport = new PresentationModelChangeSupport(this);
    }

    //增加，减少按钮
    public void onClick(ClickEvent event) {
        switch (event.getView().getId()) {
            case R.id.jianhao: //减号
                if (count-- < 2) {
                    count = 1;
                    return;
                }
                if(good.isCheck()) ShopCart.getShopCart().removeReadyPay(good);
                break;
            case R.id.jiahao: //加号
                if (count++ > 99) {
                    count = 99;
                    return;
                }
                if(good.isCheck()) ShopCart.getShopCart().addReadyPay(good);
                break;
        }
        setGoodCount(count);
        changeSupport.firePropertyChange("goumai");
    }

    //选择，或者 反选
    public void checkedChange(CheckedChangeEvent event) {
        good.setCheck(event.isChecked());
        if (event.isChecked()) {//选择
            for (int i = 0; i < count; i++) {
                ShopCart.getShopCart().addReadyPay(good);
            }
        } else {
            for (int i = 0; i < count; i++) {
                ShopCart.getShopCart().removeReadyPay(good);
            }
        }
    }

    @Override
    public void updateData(Good good, ItemContext itemContext) {
        this.good = good;
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public String getUrl() {
        return good.getThumb() == null ? "res://R.drawable.good_default":good.getThumb();
    }

    public String getName() {
        return good.getTitle();
    }

    public String getZongxu() {
        return "总需 " + good.getZongrenshu() + "  剩余 " + good.getShenyurenshu();
    }

    public String getGoumai() {
        float money = good.getMoney() == null ? 0 : Float.parseFloat(good.getMoney());
        return "我购买:" + count + "人次/￥" + (count * money);
    }

    public String getCount() {
        return count + "";
    }

    public void setGoodCount(int count) {
        this.count = count;
        changeSupport.firePropertyChange("count");
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
