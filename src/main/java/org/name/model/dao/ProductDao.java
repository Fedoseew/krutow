package org.name.model.dao;

import org.name.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ProductDao {

    private JdbcTemplate template;

    /**
     * Инжектим dataSource и создаем объект JdbcTemplate
     */
    @Autowired
    public ProductDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Получаем весь список продуктов из таблицы. Т.к. класс Product построен на концепции JavaBean
     * мы можем воспользоваться классом BeanPropertyRowMapper.
     */
    public List<Product> getAllProducts(){
        String sql = "SELECT * FROM product";
        return template.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public void addNewProduct(Product product) {
        String sql = "INSERT INTO product (id, name, quantity, price, guid, tax)" +
                " values (" + product.getId() + ",'" + product.getName() + "'," + product.getQuantity() + ",'"
                + product.getPrice() + "','" + product.getGuid() + "'," + product.getTax() + ")";
        template.execute(sql);
    }

    public void deleteProduct(Product product) {
        String sql = "DELETE FROM product WHERE id='" + product.getId() + "'";
        template.execute(sql);
    }
}