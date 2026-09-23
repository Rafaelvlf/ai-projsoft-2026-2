package br.insper.ai.controller;

import br.insper.ai.model.Item;
import br.insper.ai.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void deveListar() {
        when(itemService.listar()).thenReturn(List.of(new Item()));
        assertEquals(1, itemController.listar().size());
    }

    @Test
    void deveBuscar() {
        Item item = new Item();
        when(itemService.buscar(1L)).thenReturn(item);
        assertSame(item, itemController.buscar(1L));
    }

    @Test
    void deveSalvar() {
        Item item = new Item();
        when(itemService.salvar(item)).thenReturn(item);
        assertSame(item, itemController.salvar(item));
    }
}