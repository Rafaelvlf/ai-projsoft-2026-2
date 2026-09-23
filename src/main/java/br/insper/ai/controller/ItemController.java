package br.insper.ai.controller;

import br.insper.ai.model.Item;
import br.insper.ai.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> listar() {
        return itemService.listar();
    }

    @GetMapping("/{id}")
    public Item buscar(@PathVariable Long id) {
        return itemService.buscar(id);
    }

    @PostMapping
    public Item salvar(@RequestBody Item item) {
        return itemService.salvar(item);
    }
}