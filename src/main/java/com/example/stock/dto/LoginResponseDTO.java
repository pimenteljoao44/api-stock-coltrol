package com.example.stock.dto;

import com.example.stock.domain.enums.NivelAcesso;

public record LoginResponseDTO(Integer id, String nome, String email, String senha,String token) {
}
