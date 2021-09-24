package org.taibai.hellohei.ui;

import org.taibai.hellohei.constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Creation Time: 2021-09-21 18:15:02</p>
 * <p>Description: 获取一个新的交互动作以及交互动作的关闭</p>
 *
 * @author 太白
 */
public class ActionGenerator {

    /**
     * 动作编号
     */
    private int actionIndex = NoAction;

    private static final Map<Integer, String> resource = new HashMap<Integer, String>() {{
        put(1, Constant.ImageShow.guitarImage);
    }};
    private static final int MinIndex = 1;
    private static final int MaxIndex = 1;
    public static final int NoAction = 0;

    /**
     * 随机生成一个动作编号，这里当动作编号不为0时说明动作还未结束
     *
     * @return 当且仅当上一个动作未结束时，返回false，且不生成新动作
     */
    public boolean generateNewActionIndex() {
        if (actionIndex != NoAction) return false;
        actionIndex = (int) (Math.random() * (MaxIndex - MinIndex + 1) + MinIndex);
        return true;
    }

    /**
     * 结束动作时必须调用该API，约定的
     *
     */
    public void close() {
        actionIndex = NoAction;
    }

    /**
     * 获得动作的GIF资源
     *
     * @return 动作GIF资源文件相对路径
     */
    public String getActionPath() {
        if (resource.containsKey(actionIndex))
            return resource.get(actionIndex);
        return null;
    }
}
