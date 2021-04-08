package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.name.model.Product;
import org.name.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainController extends Controller {

    private ProductDao productDao;
    private ProductTableController tableController;

    @FXML
    private Button load;

    @Autowired
    public MainController(ProductTableController tableController,
                          ProductDao productDao) {
        this.tableController = tableController;
        this.productDao = productDao;
    }

    /**
     * Обработка нажатия кнопки загрузки товаров
     */
    @FXML
    public void onClickLoad() {
        tableController.fillTable(productDao.getAllProducts());
    }

    @FXML
    public void onClickAdd() {
        NewProductModalStage newProductModalStage = new NewProductModalStage();
        newProductModalStage.setOnHidden(close -> {
            System.out.println("close");
            tableController.fillTable(productDao.getAllProducts());
        });
        newProductModalStage.show();
    }

    @FXML
    public void onClickDelete() {
        TableView.TableViewSelectionModel<Product> model =
                tableController.productTable.getSelectionModel();
        Product product = model.getSelectedItem();
        productDao.deleteProduct(product);
        tableController.fillTable(productDao.getAllProducts());
    }
}