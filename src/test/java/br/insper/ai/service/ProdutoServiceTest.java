package br.insper.ai.service;

import br.insper.ai.dto.ProdutoDto;
import br.insper.ai.model.Produto;
import br.insper.ai.observer.OperacaoObserver;
import br.insper.ai.observer.ProdutoEstoqueObserver;
import br.insper.ai.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoEstoqueObserver estoqueObserver;

    @Mock
    private OperacaoObserver operacaoObserver;

    @Test
    public void test_shouldReturnProdutoWhenCallObterPorId() {

        ProdutoDto dto = new ProdutoDto();
        dto.setNome("produto1");
        dto.setPreco(new BigDecimal(1000));
        dto.setDescricao("produto1 do teste");
        dto.setQuantidade(11);

        Produto produto = Produto.fromDto(dto);

        Mockito.when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        Optional<Produto> op = produtoService.obterPorId(1L);

        Assertions.assertTrue(op.isPresent());
        Assertions.assertEquals("produto1", op.get().getNome());
        Assertions.assertEquals("produto1 do teste", op.get().getDescricao());
        Assertions.assertEquals(11, op.get().getQuantidade());
    }

    @Test
    public void test_shouldReturnTwoProductsWhenListarTodos() {

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        produtos.add(new Produto());

        // cria as mocks
        Mockito.when(produtoRepository.findAll())
                .thenReturn(produtos);

        // chama o metodo testado
        List<Produto> response = produtoService.listar();

        // asserts
        Assertions.assertEquals(2, response.size());
    }

    @Test
    public void test_shouldCreateProduto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome("produto1");
        dto.setPreco(new BigDecimal(1000));
        dto.setDescricao("produto1 do teste");
        dto.setQuantidade(11);

        Produto produto = Produto.fromDto(dto);

        Mockito.when(produtoRepository.save(Mockito.any()))
                .thenReturn(produto);



        Produto response = produtoService.criar(dto);

        Assertions.assertEquals("produto1", response.getNome());
    }

    @Test
    public void test_shouldDeleteProduto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome("produto1");
        dto.setPreco(new BigDecimal(1000));
        dto.setDescricao("produto1 do teste");
        dto.setQuantidade(11);

        Produto produto = Produto.fromDto(dto);

        Mockito.when(produtoRepository.existsById(Mockito.any())).thenReturn(true);

        boolean response = produtoService.deletar(produto.getId());

        Assertions.assertTrue(response);
    }

    @Test
    public void test_shouldNotDeleteProdutoIfItDoesNotExist() {

        boolean response = produtoService.deletar(1L);

        Assertions.assertFalse(response);
    }
}
