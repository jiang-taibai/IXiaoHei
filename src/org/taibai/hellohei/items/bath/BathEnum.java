package org.taibai.hellohei.items.bath;

import org.taibai.hellohei.constant.Constant;

/**
 * <p>Creation Time: 2021-09-28 00:01:02</p>
 * <p>Description: 洗澡用品清单</p>
 *
 * @author 太白
 */
public enum BathEnum {

    SOAP("肥皂", "BATH_001", Constant.ImageShow.ItemImage.soapImage, 10, Constant.ImageShow.bathImage);

    /**
     * 洗澡用品名称
     */
    private final String name;
    /**
     * 洗澡用品的唯一性ID
     */
    private final String id;
    /**
     * 洗澡用品的图片资源路径
     */
    private final String path;
    /**
     * 用一个这个可以增加多少清洁度
     */
    private final int buff;
    /**
     * 洗澡的图片资源路径
     */
    private final String actionPath;

    BathEnum(String name, String id, String path, int buff, String actionPath) {
        this.name = name;
        this.id = id;
        this.path = path;
        this.buff = buff;
        this.actionPath = actionPath;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public int getBuff() {
        return buff;
    }

    public String getActionPath() {
        return actionPath;
    }
}
