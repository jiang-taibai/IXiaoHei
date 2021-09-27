package org.taibai.hellohei.state;

import javax.swing.text.html.ImageView;

/**
 * <p>Creation Time: 2021-09-25 02:58:15</p>
 * <p>Description: 小黑的所有状态</p>
 *
 * @author 太白
 */
public class TotalState {

    private static TotalState totalState;

    private final EmotionState emotionState;
    private final StaminaState staminaState;
    private final CleanlinessState cleanlinessState;

    private TotalState() {
        emotionState = new EmotionState();
        staminaState = new StaminaState();
        cleanlinessState = new CleanlinessState();
    }

    public static TotalState getInstance() {
        if (totalState == null) totalState = new TotalState();
        return totalState;
    }

    public EmotionState getEmotionState() {
        return emotionState;
    }

    public StaminaState getStaminaState() {
        return staminaState;
    }

    public CleanlinessState getCleanlinessState() {
        return cleanlinessState;
    }

}
