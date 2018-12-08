package com.test.animator.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/11/29
 *     desc   :
 *     modify :
 * </pre>
 */

public class MainItemBean {
    private String name;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static List<MainItemBean> getMainItemList() {
        List<MainItemBean> list = new ArrayList<>();
        MainItemBean bean = new MainItemBean();
        bean.setName("补间动画");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("逐帧动画");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("属性动画");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("Android画布");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("paint");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("Xfermode");
        list.add(bean);

        bean = new MainItemBean();
        bean.setName("其他其他");
        list.add(bean);

        return list;
    }
}
