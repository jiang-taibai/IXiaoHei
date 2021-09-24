package org.taibai.hellohei.event;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.ui.Action;
import org.taibai.hellohei.ui.ActionExecutor;
import org.taibai.hellohei.ui.ActionGenerator;

/**
 * <p>Creation Time: 2021-09-22 12:50:52</p>
 * <p>Description: 全局事件监听者</p>
 *
 * @author 太白
 */
public class GlobalEventListener {

    private final Stage stage;
    private final ImageView imageView;
    private final AnchorPane anchorPane;
    /**
     * 动作执行者，触发的动作需要托付给动作执行者执行
     */
    private final ActionExecutor actionExecutor;

    private double xOffset = 0;
    private double yOffset = 0;
    private double preScreenX = 0;
    private double preScreenY = 0;

    public GlobalEventListener(Stage stage, ImageView imageView, AnchorPane anchorPane) {
        this.stage = stage;
        this.imageView = imageView;
        this.anchorPane = anchorPane;
        this.actionExecutor = ActionExecutor.newInstance(imageView);
        enableDrag();
        enableClick();
    }

    /**
     * 激活拖动
     */
    private void enableDrag() {
        anchorPane.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        anchorPane.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
    }

    /**
     * 点击随机触发一个动作
     */
    private void enableClick() {
        imageView.setOnMousePressed(e -> {
            preScreenX = e.getScreenX();
            preScreenY = e.getScreenY();
        });
        imageView.setOnMouseReleased(e -> {
            if (e.getScreenX() == preScreenX && e.getScreenY() == preScreenY) {
                actionExecutor.executeClickAction();
            }
        });
    }


}
