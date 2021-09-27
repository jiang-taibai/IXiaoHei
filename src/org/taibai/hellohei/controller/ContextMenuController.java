package org.taibai.hellohei.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


/**
 * <p>Creation Time: 2021-09-25 10:38:00</p>
 * <p>Description: 右键菜单的控制器</p>
 *
 * @author 太白
 */
public class ContextMenuController {

    /**
     * 点击按钮后应当隐藏一级菜单
     */
    private Stage preStage;
    /**
     * 打开的菜单左上角X坐标，为了打开二级菜单
     */
    private double screenX;
    /**
     * 打开的菜单左上角Y坐标，为了打开二级菜单
     */
    private double screenY;

    public void Init(Stage stage, double screenX, double screenY) {
        this.preStage = stage;
        this.screenX = screenX;
        this.screenY = screenY;
    }

    @FXML
    public void eat() {
        preStage.close();
        showItemsWindow(ItemsWindowController.FoodTitle);
    }

    @FXML
    public void bath() {
        preStage.close();
        showItemsWindow(ItemsWindowController.BathTitle);
    }

    private void showItemsWindow(String title) {
        // ====== 设置名义上的stage，避免在任务栏生成一个小窗口 ======
        final Stage nominalStage = new Stage();
        nominalStage.initStyle(StageStyle.UTILITY);
        nominalStage.setOpacity(0);
        final Stage stage = new Stage();
        stage.initOwner(nominalStage);
        stage.initStyle(StageStyle.TRANSPARENT);    // 设定窗口透明且无边框
        stage.setAlwaysOnTop(true);                 // 设置窗口总显示在最前

        // ====== 设置菜单出现的位置，默认出现在光标的右下方，但是有两种超出屏幕边缘的情况 ======
        stage.setX(screenX);
        stage.setY(screenY);

        // ====== 获得fxml文件 ======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/taibai/hellohei/fxml/ItemsWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ====== 获得控制器实例 ======
        ItemsWindowController controller = loader.getController();   //获取Controller的实例对象
        controller.Init(title, stage);

        // ====== 在stage中装入scene，并为scene设置css样式 ======
        Scene scene = new Scene(Objects.requireNonNull(root));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("/org/taibai/hellohei/fxml/ItemsWindow.css")).toExternalForm());

        // ====== 当失去焦点的时候设置隐藏stage ======
        stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!stage.isFocused()) {
                stage.close();
            }
        });

        // ====== 展示菜单 ======
        nominalStage.show();
        stage.show();
    }

}
