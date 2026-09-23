package br.insper.ai.service;

import br.insper.ai.model.Item;
import br.insper.ai.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> listar() {
        return itemRepository.findAll();
    }

    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    public Item buscar(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
}