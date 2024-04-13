package com.example.stock.controle;

import com.example.stock.domain.Category;
import com.example.stock.domain.User;
import com.example.stock.dto.CategoryDTO;
import com.example.stock.dto.UserDTO;
import com.example.stock.service.CategoryService;
import com.example.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id){
        CategoryDTO objDTO = new CategoryDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<Category> list = service.findAll();
        List<CategoryDTO> listDTO = new ArrayList<>();

        for (Category c : list) {
            listDTO.add(new CategoryDTO(c));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO objDTO) {
        Category newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getCategoryId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryDTO(newObj));
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id,@Valid @RequestBody CategoryDTO obj){
        obj = new CategoryDTO(service.update(id,obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
