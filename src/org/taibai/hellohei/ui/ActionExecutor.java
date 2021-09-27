package org.taibai.hellohei.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.state.TotalState;

/**
 * <p>Creation Time: 2021-09-22 11:49:12</p>
 * <p>Description: 动作执行者</p>
 *
 * @author 太白
 */
public class ActionExecutor {

    private final ImageView imageView = MainNode.getInstance().getImageView();
    private Action curAction;
    private final ResourceGetter resourceGetter = ResourceGetter.getInstance();
    private final ActionGenerator actionGenerator = new ActionGenerator();
    private static ActionExecutor actionExecutor;
    private Timeline timeline;

    public static ActionExecutor getInstance() {
        if (actionExecutor == null) actionExecutor = new ActionExecutor();
        return actionExecutor;
    }

    private ActionExecutor() {
    }

    public boolean execute(Action action) {
        // 如果上一个动作不可中断，那么动作执行失败
        if (curAction != null && !curAction.isInterruptable()) return false;
        Image actionImage = resourceGetter.get(action.getPath());
        imageView.setImage(actionImage);
        curAction = action;
        if (timeline != null) timeline.pause();
        // 如果当前动作是暂时的，则还需要恢复到某一个动作
        if (action.isTemporaryAction()) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(action.getTime()), e -> executeContinuousInterruptableActionAction(action.getRecoverPath())));
            timeline.play();
        }
        return true;
    }

    public boolean executeClickAction() {
        boolean ok = actionGenerator.generateNewActionIndex();
        if (ok) {
            execute(Action.creatTemporaryInterruptableAction(
                    actionGenerator.getActionPath(),
                    Constant.UserInterface.ActionRunTime,
                    Constant.ImageShow.mainImage));
            // 同时会增加心情值
            TotalState.getInstance().getEmotionState().increase();
        }
        return ok;
    }

    /**
     * 立即执行一个可中断的、持续的动作
     */
    private void executeContinuousInterruptableActionAction(String path) {
        curAction = null;
        timeline = null;
        actionGenerator.close();
        Action action = Action.creatContinuousInterruptableAction(path);
        execute(action);
    }

}
