package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.name.model.Product;
import org.name.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.UUID;

@Component
public class NewProductController extends Controller {

    @FXML
    private TextField nameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @FXML
    public void back() {

    }

    @FXML
    public void save() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        ProductDao productDao = context.getBean(ProductDao.class);

        if (
                !StringUtils.isEmpty(nameField.getText())
                        && !StringUtils.isEmpty(quantityField.getText())
                        && !StringUtils.isEmpty(priceField.getText())
        ) {

            Product product = new Product();
            product.setId(productDao.getLastId());
            product.setGuid(UUID.randomUUID().toString());
            product.setTax(Math.abs(new Random().nextInt()));
            product.setName(nameField.getText().trim());
            product.setPrice(priceField.getText());

            try {
                product.setQuantity(Integer.parseInt(quantityField.getText()));
                productDao.addNewProduct(product);
                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.close();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong quantity!");
                alert.setContentText("Quantity must be a number");
                alert.show();
                clearAllFields();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Wrong input!");
            alert.show();
        }
    }

    private void clearAllFields() {
        nameField.clear();
        priceField.clear();
        quantityField.clear();
    }
}
