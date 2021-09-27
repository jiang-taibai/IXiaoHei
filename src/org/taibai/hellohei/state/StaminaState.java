package org.taibai.hellohei.state;

/**
 * <p>Creation Time: 2021-09-28 00:51:27</p>
 * <p>Description: 体力值</p>
 *
 * @author 太白
 */
public class StaminaState {

    private int stamina = 60;

    public static final int Reduce_Step = 2;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;

    /**
     * 体力值降低
     */
    public void reduce() {
        stamina = Math.max(Min_Value, stamina - Reduce_Step);
    }

    /**
     * 体力值增加
     *
     * @param num 增加的量
     */
    public void increase(int num) {
        stamina = Math.min(Max_Value, stamina + num);
        System.out.printf("[StaminaState::increase(%d)]-当前体力值=%d\n", num, stamina);
    }

    /**
     * 是否还能增加
     *
     * @return 能否增加
     */
    public boolean canIncrease() {
        return stamina < Max_Value;
    }

}
