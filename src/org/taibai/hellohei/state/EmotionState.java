package org.taibai.hellohei.state;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.img.ResourceGetter;

/**
 * <p>Creation Time: 2021-09-25 02:59:11</p>
 * <p>Description: 小黑的心情值</p>
 *
 * @author 太白
 */
public class EmotionState {

    /**
     * 心情值，取值为[0, 100]
     */
    private int emotion = 60;
    public static final int Reduce_Step = 5;
    public static final int Increase_Step = 10;
    public static final int Max_Value = 100;
    public static final int Min_Value = 0;
    private ImageView imageView;

    private final ResourceGetter resourceGetter = ResourceGetter.getInstance();

    public EmotionState() {
        imageView = new ImageView();
    }

    /**
     * 心情值降低
     */
    public void reduce() {
        emotion = Math.max(Min_Value, emotion - Reduce_Step);
    }

    /**
     * 心情值增加
     */
    public void increase() {
        if (emotion < Max_Value) showIncreasedAnimation();
        emotion = Math.min(Max_Value, emotion + Increase_Step);
        System.out.printf("[EmotionState::increase]-当前心情=%d\n", emotion);
    }

    /**
     * 展示心情增加的动画
     */
    private void showIncreasedAnimation() {
        Image increasingImg = resourceGetter.get(Constant.ImageShow.emotionIncreasingImage);
        imageView.setImage(increasingImg);
        imageView.setStyle("-fx-background:transparent;");
        // 设置相对于父容器的位置
        imageView.setX(0);
        imageView.setY(0);
        imageView.setLayoutX(60);
        imageView.setLayoutY(0);
        imageView.setFitHeight(80);         // 设置图片显示的大小
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);   // 保留width:height比例
        imageView.setVisible(true);

        double millis = Constant.UserInterface.ActionRunTime * 1000;
        // 位移动画
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(millis), imageView);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setFromY(40);
        translateTransition.setToY(0);
        // translateTransition.play();
        // 淡入淡出动画
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(millis), imageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        // 并行执行动画
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeTransition,
                translateTransition
        );
        parallelTransition.play();
    }

    public ImageView getImageView() {
        return imageView;
    }
}
