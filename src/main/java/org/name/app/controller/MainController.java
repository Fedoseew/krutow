package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.name.model.Product;
import org.name.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class MainController extends Controller {

    private final ProductTableController tableController;
    protected final ProductDao productDao;
    @FXML
    private Button load;
    private List<Product> products = new ArrayList<>();

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
        products = productDao.getAllProducts();
        tableController.fillTable(products);
        load.setDisable(true);

    }

    @FXML
    public void onClickAdd() throws IOException {
        NewProductModalStage newProductModalStage = new NewProductModalStage();
        newProductModalStage.show();
    }

    @FXML
    public void onClickDelete() {
        TableView.TableViewSelectionModel<Product> model =
                tableController.productTable.getSelectionModel();
        Product product = model.getSelectedItem();
        productDao.deleteProduct(product);
        products.remove(product);
        tableController.fillTable(products);
    }
}