package com.example.stock.service;

import com.example.stock.domain.Category;
import com.example.stock.domain.User;
import com.example.stock.dto.CategoryDTO;
import com.example.stock.dto.UserDTO;
import com.example.stock.repository.CategoryRepository;
import com.example.stock.service.exceptions.DataIntegratyViolationException;
import com.example.stock.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByName (String name) {
        return categoryRepository.findByName(name);
    }

    public Category findById (Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
    }

    public List<Category> findAll () {
        return categoryRepository.findAll();
    }

    public Category create(CategoryDTO objDTO) {
        if (findByName(objDTO.getName()) != null) {
            throw new DataIntegratyViolationException("Categoria já cadastrada na base de dados!");
        }

        Category category = fromDTO(objDTO);
        return categoryRepository.save(category);
    }

    public Category update(Integer id, @Valid CategoryDTO obj) {
        Category category = findById(id);

        category.setName(obj.getName());

        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }


    public Category fromDTO(CategoryDTO dto) {
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setName(dto.getName());
        return category;
    }


}
