package br.insper.ai.observer;

import br.insper.ai.model.Produto;
import br.insper.ai.operacao.TipoOperacao;

public interface OperacaoObservable {
    void registrarOperacao(Long idProduto, TipoOperacao operacao);
    void verificarEstoque(Produto produto);
}
