package br.insper.ai.service;

import br.insper.ai.model.Item;
import br.insper.ai.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void deveListar() {
        when(itemRepository.findAll()).thenReturn(List.of(new Item()));
        assertEquals(1, itemService.listar().size());
    }

    @Test
    void deveSalvar() {
        Item item = new Item();
        when(itemRepository.save(item)).thenReturn(item);
        assertSame(item, itemService.salvar(item));
    }

    @Test
    void deveBuscar() {
        Item item = new Item();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        assertSame(item, itemService.buscar(1L));
    }

    @Test
    void deveLancarErroQuandoNaoEncontrar() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> itemService.buscar(1L));
    }
}