package org.taibai.hellohei.items.food;

/**
 * <p>Creation Time: 2021-09-27 17:22:01</p>
 * <p>Description: Item列表，列出所有食物、洗澡用品、药物</p>
 *
 * @author 太白
 */
public enum FoodEnum {

    EGG("鸡蛋", "FOOD_001", "foods/egg.png", 10, "eat drumstick.gif"),
    MILK("牛奶", "FOOD_002", "foods/milk.png", 5, "eat drumstick.gif");

    /**
     * 食物名称
     */
    private final String name;
    /**
     * 食物的唯一性ID
     */
    private final String id;
    /**
     * 食物的图片资源路径
     */
    private final String path;
    /**
     * 吃一个这个可以增加多少饱腹度（一般叫饥饿值，感觉不太对）
     */
    private final int buff;
    /**
     * 吃东西的图片资源路径
     */
    private final String actionPath;

    public static final String pathPrefix = "/org/taibai/hellohei/img/";

    FoodEnum(String name, String id, String path, int buff, String actionPath) {
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
        return pathPrefix + path;
    }

    public int getBuff() {
        return buff;
    }

    public String getActionPath() {
        return pathPrefix + actionPath;
    }
}
