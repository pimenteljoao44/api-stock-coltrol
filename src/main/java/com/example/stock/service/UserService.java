package com.example.stock.service;
import java.util.List;
import java.util.Optional;

import com.example.stock.domain.User;
import com.example.stock.domain.enums.NivelAcesso;
import com.example.stock.dto.UserDTO;
import com.example.stock.repository.UserRepository;
import com.example.stock.service.exceptions.DataIntegratyViolationException;
import com.example.stock.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    private User findByLogin(UserDTO objDTO) {
        User obj = repository.findByLogin(objDTO.getEmail());
        if (obj != null) {
            return obj;
        }
        return null;
    }

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado!"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User create(UserDTO objDTO) {
        if (findByLogin(objDTO) != null) {
            throw new DataIntegratyViolationException("Usuário já cadastrado na base de dados!");
        }
        return fromDTO(objDTO);
    }

    public User update(@Valid UserDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private User fromDTO(UserDTO obj) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(obj.getSenha());
        User newObj = new User();
        newObj.setId(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setSenha(encryptedPassword);

        return repository.save(newObj);
    }
}