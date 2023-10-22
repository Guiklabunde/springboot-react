package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
 
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    
    @Autowired
    private RespostaModelo respostaModelo;

    // Método para listar todos os produtos
    public Iterable<ProdutoModelo> listar() {
        return produtoRepositorio.findAll();
    }

    // Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo produto, String acao) {
        
        if (produto.getNome().isEmpty()) {
            respostaModelo.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else if (produto.getMarca().isEmpty()) {
            respostaModelo.setMensagem("O nome da marca é obrigatório!");
            return new ResponseEntity<>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else {
            if ("cadastrar".equals(acao)) {
                ProdutoModelo produtoSalvo = produtoRepositorio.save(produto);
                return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
            } else {
                ProdutoModelo produtoAlterado = produtoRepositorio.save(produto);
                return new ResponseEntity<>(produtoAlterado, HttpStatus.OK);
            }
        }
    }

    // Método para remover produtos
    public ResponseEntity<RespostaModelo> remover(long codigo) {

        produtoRepositorio.deleteById(codigo);
        
        respostaModelo.setMensagem("O produto foi removido com sucesso!");
        return new ResponseEntity<>(respostaModelo, HttpStatus.OK);
       
    }

}