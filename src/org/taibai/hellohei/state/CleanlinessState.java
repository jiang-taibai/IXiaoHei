package org.taibai.hellohei.state;

/**
 * <p>Creation Time: 2021-09-28 00:56:19</p>
 * <p>Description: 清洁度状态</p>
 *
 * @author 太白
 */
public class CleanlinessState {

    private int cleanliness = 60;

    public static final int Reduce_Step = 2;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;

    /**
     * 心情值降低
     */
    public void reduce() {
        cleanliness = Math.max(Min_Value, cleanliness - Reduce_Step);
    }

    /**
     * 清洁度增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        cleanliness = Math.min(Max_Value, cleanliness + num);
        System.out.printf("[CleanlinessState::increase(%d)]-当前体力值=%d\n", num, cleanliness);
    }

    /**
     * 是否还能增加
     *
     * @return 能否增加
     */
    public boolean canIncrease() {
        return cleanliness < Max_Value;
    }

}
