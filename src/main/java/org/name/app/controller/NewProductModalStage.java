package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.name.app.SpringStageLoader;
import org.name.model.Product;
import org.name.model.dao.ProductDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class NewProductModalStage extends Stage {



    public NewProductModalStage() {
        this.initModality(Modality.WINDOW_MODAL);
        this.centerOnScreen();

        try {

            Scene scene = SpringStageLoader.loadScene("addNewProduct");
            this.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
