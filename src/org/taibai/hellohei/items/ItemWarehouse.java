package org.taibai.hellohei.items;

import org.taibai.hellohei.items.bath.BathEnum;
import org.taibai.hellohei.items.bath.BathItem;
import org.taibai.hellohei.items.food.FoodEnum;
import org.taibai.hellohei.items.food.FoodItem;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Creation Time: 2021-09-27 17:35:14</p>
 * <p>Description: Item存量</p>
 *
 * @author 太白
 */
public class ItemWarehouse {

    private static ItemWarehouse itemWarehouse;

    private final Map<String, FoodItem> foodItemMap = new HashMap<>();
    private final Map<String, BathItem> bathItemMap = new HashMap<>();

    private ItemWarehouse() {
        // 这里默认有10个，等后端系统写好后就可以持久化了
        for (FoodEnum foodEnum : FoodEnum.values()) {
            foodItemMap.put(foodEnum.getId(), new FoodItem(foodEnum, 10));
        }
        // 这里也默认有10个洗澡用品
        for (BathEnum bathEnum : BathEnum.values()) {
            bathItemMap.put(bathEnum.getId(), new BathItem(bathEnum, 10));
        }
    }

    public static ItemWarehouse getInstance() {
        if (itemWarehouse == null) itemWarehouse = new ItemWarehouse();
        return itemWarehouse;
    }

    public Map<String, FoodItem> getFoodItemMap() {
        return foodItemMap;
    }

    public Map<String, BathItem> getBathItemMap() {
        return bathItemMap;
    }
}
