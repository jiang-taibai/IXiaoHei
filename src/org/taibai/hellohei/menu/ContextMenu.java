package org.taibai.hellohei.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.taibai.hellohei.controller.ContextMenuController;

import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.Objects;


/**
 * <p>Creation Time: 2021-09-25 04:36:35</p>
 * <p>Description: 点击本体触发的右键菜单</p>
 *
 * @author 太白
 */
public class ContextMenu {

    private static ContextMenu contextMenu;

    private ContextMenu() {

    }

    public static ContextMenu getInstance() {
        if (contextMenu == null) contextMenu = new ContextMenu();
        return contextMenu;
    }

    public void show(Node node, double screenX, double screenY) {
        // ====== 设置名义上的stage，避免在任务栏生成一个小窗口 ======
        final Stage nominalStage = new Stage();
        nominalStage.initStyle(StageStyle.UTILITY);
        nominalStage.setOpacity(0);
        final Stage stage = new Stage();
        stage.initOwner(nominalStage);
        stage.initStyle(StageStyle.TRANSPARENT);    // 设定窗口透明且无边框
        stage.setAlwaysOnTop(true);                 // 设置窗口总显示在最前

        // ====== 设置菜单出现的位置，默认出现在光标的右下方，但是有两种超出屏幕边缘的情况 ======
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double screenWidth = screenRectangle.getWidth();
        double screenHeight = screenRectangle.getHeight();
        double stageWidth = 138.0;
        double stageHeight = 280.0;
        // 如果弹出的右键菜单超出屏幕下边缘，就向上展开
        if(screenY + stageHeight > screenHeight) screenY -= stageHeight;
        // 如果弹出的右键菜单超出屏幕右边缘，就向左展开
        if(screenX + stageWidth > screenWidth) screenX -= stageWidth;
        stage.setX(screenX);
        stage.setY(screenY);

        // ====== 获得fxml文件 ======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/taibai/hellohei/fxml/ContextMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ====== 获得控制器实例 ======
        ContextMenuController controller = loader.getController();   //获取Controller的实例对象
        controller.Init(stage, screenX, screenY);

        // ====== 在stage中装入scene，并为scene设置css样式 ======
        Scene scene = new Scene(Objects.requireNonNull(root));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("/org/taibai/hellohei/fxml/ContextMenu.css")).toExternalForm());

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
