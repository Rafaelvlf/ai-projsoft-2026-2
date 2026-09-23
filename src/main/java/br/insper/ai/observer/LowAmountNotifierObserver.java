package br.insper.ai.observer;

import br.insper.ai.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class LowAmountNotifierObserver implements ProdutoEstoqueObserver {

    @Override
    public void verificarEstoque(Produto produto) {
        if (produto.getQuantidade() < 10) {
            String mensagem = "O produto está com pouca quantidade";
            System.out.println(mensagem);
        }
    }
}
