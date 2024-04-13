package com.example.stock.service;

import com.example.stock.domain.Category;
import com.example.stock.domain.Product;
import com.example.stock.dto.ProductDTO;
import com.example.stock.dto.SaleDTO;
import com.example.stock.repository.CategoryRepository;
import com.example.stock.repository.ProductRepository;
import com.example.stock.service.exceptions.DataIntegratyViolationException;
import com.example.stock.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// ProductService.java
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository; // Se necessário

    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(ProductDTO productDTO) {
        if (findByName(productDTO.getName()) != null) {
            throw new DataIntegratyViolationException("Produto já cadastrado na base de dados!");
        }

        Product product = fromDTO(productDTO);
        return productRepository.save(product);
    }

    public Product update(Integer id, ProductDTO productDTO) {
        Product product = findById(id);
        updateProductFromDTO(product, productDTO);
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public void sellProduct(SaleDTO saleDTO) {
        Product product = findById(saleDTO.getProductId());
        BigDecimal quantitySold = saleDTO.getQuantitySold();

        if (quantitySold.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DataIntegratyViolationException("Quantidade inválida para realizar a venda!");
        }

        if (product.getAmount().compareTo(quantitySold) >= 0) {
            BigDecimal newAmount = product.getAmount().subtract(quantitySold);
            product.setAmount(newAmount);
            productRepository.save(product);
        } else {
            throw new DataIntegratyViolationException("Quantidade insuficiente em estoque para realizar a venda!");
        }
    }


    private Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());

        if (productDTO.getCategory() != null && productDTO.getCategory().getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getCategoryId())
                    .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com o ID: "
                            + productDTO.getCategory().getCategoryId()));
            product.setCategory(category);
        }
        return product;
    }


    private void updateProductFromDTO(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());

        if (productDTO.getCategory() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getCategoryId())
                    .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com o ID: " + productDTO.getCategory().getCategoryId()));
            product.setCategory(category);
        }
    }
}

