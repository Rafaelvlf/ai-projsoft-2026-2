package br.insper.ai.observer;

import br.insper.ai.model.Produto;
import br.insper.ai.operacao.TipoOperacao;

public interface OperacaoObserver {
    void registrar(Long idProduto, TipoOperacao operacao);
}
