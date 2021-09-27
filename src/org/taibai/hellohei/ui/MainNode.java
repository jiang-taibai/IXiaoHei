package org.taibai.hellohei.ui;


import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * <p>Creation Time: 2021-09-27 22:37:00</p>
 * <p>Description: 动作的展示窗口，包括主界面的ImageView(展示GIF)，Stage(控制整个程序的显隐、关闭等)
 *  毕竟是全局唯一的对象，所以设置为单例模式，全局拿到的就是唯一的对象</p>
 *
 * @author 太白
 */
public class MainNode {

    private static MainNode mainNode;
    private final ImageView imageView;
    private final Stage stage;

    private MainNode() {
        imageView = new ImageView();
        stage = new Stage();
    }

    public static MainNode getInstance() {
        if (mainNode == null) mainNode = new MainNode();
        return mainNode;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Stage getStage() {
        return stage;
    }
}
