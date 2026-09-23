package br.insper.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
}
