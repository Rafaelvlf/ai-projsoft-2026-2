package br.insper.ai.controller;

import br.insper.ai.dto.ProdutoDto;
import br.insper.ai.service.ProdutoService;
import br.insper.ai.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criar(@RequestBody ProdutoDto dto) {
        return produtoService.criar(dto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto obter(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.obterPorId(id);
        if (produto.isPresent()) {
            return produto.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

}
