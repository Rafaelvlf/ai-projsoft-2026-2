package br.insper.ai.service;

import br.insper.ai.dto.ProdutoDto;
import br.insper.ai.model.Produto;
import br.insper.ai.observer.OperacaoObservable;
import br.insper.ai.observer.OperacaoObserver;
import br.insper.ai.observer.ProdutoEstoqueObserver;
import br.insper.ai.operacao.TipoOperacao;
import br.insper.ai.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements OperacaoObservable {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired(required = false)
    private OperacaoObserver operacaoObserver;

    @Autowired(required = false)
    private ProdutoEstoqueObserver estoqueObserver;

    @Override
    public void registrarOperacao(Long idProduto, TipoOperacao operacao) {
        operacaoObserver.registrar(idProduto, operacao);
    }

    @Override
    public void verificarEstoque(Produto produto) {
        estoqueObserver.verificarEstoque(produto);
    }

    public Produto criar(ProdutoDto dto) {
        Produto produto = Produto.fromDto(dto);
        Produto salvo = produtoRepository.save(produto);
        verificarEstoque(produto);
        registrarOperacao(salvo.getId(), TipoOperacao.CRIAR);
        return salvo;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> obterPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            registrarOperacao(id, TipoOperacao.DELETAR);
            produtoRepository.deleteById(id);
            return true;
        }
        return  false;
    }
}