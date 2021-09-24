package org.taibai.hellohei.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.taibai.hellohei.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * <p>Creation Time: 2021-09-21 19:00:47</p>
 * <p>Description: 交互功能</p>
 *
 * @author 太白
 */
public class InterfaceFunction {

    private final ImageView imageView;
    private final ActionExecutor actionExecutor;
    private final Stage stage;
    private VBox messageBox;
    private CheckboxMenuItem itemSay = new CheckboxMenuItem("碎碎念");
    private final String greet = "好久不见鸭，想你了~";

    public InterfaceFunction(Stage stage, ImageView imageView) {
        this.stage = stage;
        this.imageView = imageView;
        this.actionExecutor = ActionExecutor.newInstance(imageView);
        this.messageBox = new VBox();
        initMessage();
        say(greet, 8);
        // 开启随机事件
        RandomEvent randomEvent = new RandomEvent();
        new Thread(randomEvent).start();
    }

    /**
     * 初始化消息框
     */
    private void initMessage() {
        Label bubble = new Label();
        //设置气泡的宽度。如果没有这句，就会根据内容多少来自适应宽度
        bubble.setPrefWidth(100);
        bubble.setWrapText(true);   //自动换行
        bubble.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-background-radius: 8px;");
        bubble.setPadding(new Insets(7)); //标签的内边距的宽度
        bubble.setFont(new javafx.scene.text.Font(14));
        bubble.setTextFill(Color.web("#000000"));

        Polygon triangle = new Polygon(0.0, 0.0, 8.0, 10.0, 16.0, 0.0);//分别设置三角形三个顶点的X和Y
        triangle.setFill(new Color(1, 1, 1, 0.7));

        // VBox.setMargin(triangle, new Insets(0, 50, 0, 0));//设置三角形的位置，默认居中
        messageBox.getChildren().addAll(bubble, triangle);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);
        messageBox.setStyle("-fx-background:transparent;");
        //设置相对于父容器的位置
        messageBox.setLayoutX(0);
        messageBox.setLayoutY(0);
        messageBox.setVisible(true);
    }

    /**
     * 退出
     */
    public void exit() {
        // 展示告别动画
        double time = 1.5;
        actionExecutor.execute(Action.creatTemporaryUninterruptibleAction(Constant.ImageShow.byeImage, time, Constant.ImageShow.mainImage));
        // 要用Platform.runLater，不然会报错Not on FX application thread;
        Platform.runLater(() -> say("再见~", Constant.UserInterface.SayingRunTime));
        // 动画结束后执行退出
        new Timeline(new KeyFrame(
                Duration.seconds(time),
                ae -> System.exit(0)))
                .play();
    }

    /**
     * 说一句话
     *
     * @param msg      消息
     * @param duration 持续时间
     */
    public void say(String msg, int duration) {
        Label lbl = (Label) messageBox.getChildren().get(0);
        lbl.setText(msg);
        messageBox.setVisible(true);
        //设置气泡的显示时间
        new Timeline(new KeyFrame(
                Duration.seconds(duration),
                ae -> messageBox.setVisible(false)))
                .play();
    }

    /**
     * 添加系统托盘
     *
     * @param stage 舞台
     */
    public void setTray(Stage stage) {
        SystemTray tray = SystemTray.getSystemTray();
        //托盘图标
        BufferedImage image;
        try {
            // 为托盘添加一个右键弹出菜单
            PopupMenu popMenu = new PopupMenu();
            popMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            MenuItem itemShow = new MenuItem("显示");
            itemShow.addActionListener(e -> Platform.runLater(stage::show));

            MenuItem itemHide = new MenuItem("隐藏");
            // 要先setImplicitExit(false)，否则stage.hide()会直接关闭stage
            // stage.hide()等同于stage.close()
            itemHide.addActionListener(e -> {
                Platform.setImplicitExit(false);
                Platform.runLater(stage::hide);
            });

            MenuItem itemExit = new MenuItem("退出");
            itemExit.addActionListener(e -> exit());

            popMenu.add(itemSay);
            popMenu.addSeparator();
            popMenu.add(itemShow);
            popMenu.add(itemHide);
            popMenu.add(itemExit);
            //设置托盘图标
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Constant.ImageShow.iconImage)));
            TrayIcon trayIcon = new TrayIcon(image, "小黑", popMenu);
            trayIcon.setToolTip("小黑");
            trayIcon.setImageAutoSize(true);//自动调整图片大小。这步很重要，不然显示的是空白
            tray.add(trayIcon);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public VBox getMessageBox() {
        return messageBox;
    }

    class RandomEvent implements Runnable {
        @Override
        public void run() {
            while (true) {
                Random rand = new Random();
                //随机发生自动事件，以下设置间隔为9~24秒。要注意这个时间间隔包含了动画播放的时间
                long time = (rand.nextInt(15) + 10) * 1000;
                if (itemSay.getState()) {
                    //随机选择要说的话。因为目前只有两个宠物，所以可以用三目运算符
                    String str = Constant.UserInterface.selfTalking[rand.nextInt(5)];
                    Platform.runLater(() -> say(str, Constant.UserInterface.SayingRunTime));
                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
