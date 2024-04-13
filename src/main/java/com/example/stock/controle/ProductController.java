package com.example.stock.controle;

import com.example.stock.domain.Category;
import com.example.stock.domain.Product;
import com.example.stock.dto.CategoryDTO;
import com.example.stock.dto.ProductDTO;
import com.example.stock.dto.SaleDTO;
import com.example.stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> list = productService.findAll();
        List<ProductDTO> listDTO = new ArrayList<>();

        for (Product p : list) {
            listDTO.add(new ProductDTO(p));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO objDTO) {
        Product newObj = productService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductDTO(newObj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.update(id, productDTO);
        return ResponseEntity.ok().body(new ProductDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sell")
    public ResponseEntity<Void> sellProduct(@RequestBody SaleDTO saleDTO) {
        productService.sellProduct(saleDTO);
        return ResponseEntity.ok().build();
    }
}
