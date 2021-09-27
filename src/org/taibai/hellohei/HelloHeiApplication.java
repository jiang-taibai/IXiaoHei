package org.taibai.hellohei;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.taibai.hellohei.constant.Constant;
import org.taibai.hellohei.event.GlobalEventListener;
import org.taibai.hellohei.img.ResourceGetter;
import org.taibai.hellohei.state.TotalState;
import org.taibai.hellohei.ui.InterfaceFunction;
import org.taibai.hellohei.ui.MainNode;

import java.io.IOException;
import java.util.Objects;


public class HelloHeiApplication extends Application {

    /**
     * 展示图片的窗口
     */
    private final ImageView imageView = MainNode.getInstance().getImageView();
    private final Stage stage = MainNode.getInstance().getStage();
    private AnchorPane pane;
    private InterfaceFunction interfaceFunction;
    private final TotalState totalState = TotalState.getInstance();
    /**
     * 全局事件监听，目前支持拖拽、左键点击反馈
     */
    private GlobalEventListener globalEventListener;

    private final ResourceGetter resourceGetter = ResourceGetter.getInstance();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);     // 设置父级透明度为0
        stage.initOwner(primaryStage);  // 将 primaryStage 设置为归属对象，即父级窗口
        initImageView();
        // 交互功能平台
        interfaceFunction = InterfaceFunction.getInstance();
        // 面板
        pane = new AnchorPane(
                interfaceFunction.getMessageBox(),
                imageView,
                totalState.getEmotionState().getImageView()
        );
        pane.setStyle("-fx-background:transparent;");
        // 开启全局事件
        globalEventListener = new GlobalEventListener(stage, imageView, pane);
        initStage(stage);
        primaryStage.show();
        stage.show();
        interfaceFunction.setTray(stage);   //添加系统托盘
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initImageView() {
        Image image = resourceGetter.get(Constant.ImageShow.mainImage);
        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setLayoutX(0);
        imageView.setLayoutY(50);
        imageView.setFitHeight(Constant.ImageShow.ImageHeight); // 设置图片显示的大小
        imageView.setFitHeight(Constant.ImageShow.ImageWidth);
        imageView.setPreserveRatio(true);                       // 保留width:height比例
        imageView.setStyle("-fx-background:transparent;");      // 透明背景
    }

    private void initStage(Stage stage) {
        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(null);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage.setScene(scene);
        // 设置窗体的初始位置
        stage.setX(850);
        stage.setY(400);
        stage.setAlwaysOnTop(true);// 窗口总显示在最前
        // 修改任务栏图标
        stage.getIcons().add(resourceGetter.get(Constant.ImageShow.iconImage));
        stage.initStyle(StageStyle.TRANSPARENT);// 背景透明
        stage.setOnCloseRequest(event -> {
            event.consume();
            interfaceFunction.exit();
        });
    }

}