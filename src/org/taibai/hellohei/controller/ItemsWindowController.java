package org.taibai.hellohei.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.taibai.hellohei.items.bath.BathItem;
import org.taibai.hellohei.items.food.FoodItem;
import org.taibai.hellohei.items.ItemWarehouse;
import org.taibai.hellohei.state.TotalState;

import java.util.Map;

/**
 * <p>Creation Time: 2021-09-27 00:32:16</p>
 * <p>Description: 货物列表控制器，该窗口用于展示食物、洗澡用品、打工列表</p>
 *
 * @author 太白
 */
public class ItemsWindowController {

    public static final String FoodTitle = "食物仓库";
    public static final String BathTitle = "沐浴仓库";
    public static final String DrugTitle = "药品仓库";

    @FXML
    public Label title;
    private String titleText;
    @FXML
    public Pane itemPane;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox vbox;
    private Stage stage;

    public void Init(String title, Stage stage) {
        this.stage = stage;
        this.titleText = title;
        this.title.setText(title);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        // 禁用左右滚轴
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loadItems();
    }

    private void loadItems() {
        switch (titleText) {
            case FoodTitle:
                loadFoodItems();
                break;
            case BathTitle:
                loadBathItems();
                break;
            case DrugTitle:
                loadDrugItems();
        }
    }

    private void loadFoodItems() {
        Map<String, FoodItem> foodItemList = ItemWarehouse.getInstance().getFoodItemMap();
        for (Map.Entry<String, FoodItem> entry : foodItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getStaminaState().canIncrease()) {
                stage.close();
            }
        }));
    }

    private void loadBathItems() {
        Map<String, BathItem> bathItemList = ItemWarehouse.getInstance().getBathItemMap();
        for (Map.Entry<String, BathItem> entry : bathItemList.entrySet()) {
            if (entry.getValue().getItemNum() != 0) {
                vbox.getChildren().add(entry.getValue().toItemAnchorPane());
            }
        }
        ObservableList<Node> children = vbox.getChildren();
        children.forEach(c -> c.setOnMouseReleased(e -> {
            if (TotalState.getInstance().getCleanlinessState().canIncrease()) {
                stage.close();
            }
        }));
    }

    private void loadDrugItems() {

    }

}
