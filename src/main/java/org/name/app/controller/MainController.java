package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.name.model.Product;
import org.name.model.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MainController extends Controller {

    protected final ProductDao productDao;
    private final ProductTableController tableController;

    @FXML
    private Button load;

    private List<Product> products = new ArrayList<>();

    @Autowired
    public MainController(ProductTableController tableController,
                          ProductDao productDao) {
        this.tableController = tableController;
        this.productDao = productDao;
        products = productDao.getAllProducts();
    }

    /**
     * Обработка нажатия кнопки загрузки товаров
     */
    @FXML
    public void onClickLoad() {
        tableController.fillTable(products);
        load.setDisable(true);
    }

    @FXML
    public void onClickAdd() {
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
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void refreshTable() {
        if(products != null) {
            tableController.fillTable(products);
        }
    }
}