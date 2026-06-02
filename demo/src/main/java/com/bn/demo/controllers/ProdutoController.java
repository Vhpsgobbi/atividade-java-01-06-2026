package com.bn.demo.controllers;

import com.bn.demo.models.ProdutoModel;
import com.bn.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
// Fala pro Spring que essa classe é um controller REST, ou seja, vai receber e responder requisições HTTP
@RequestMapping(path = {"/produtos", "/produtos/"})
// Define o caminho base da URL pra todos os endpoints dessa classe, tudo aqui começa com /produtos
public class ProdutoController {

    @Autowired
    // Injeta automaticamente o ProdutoService aqui, sem precisar criar o objeto na mão com "new"
    private ProdutoService produtoService;

    @GetMapping
    // Mapeia requisições GET pra esse método, quando alguém chamar GET /produtos cai aqui
    public ResponseEntity<List<ProdutoModel>> buscarTodosOsProdutos(){
        List<ProdutoModel> requeste = produtoService.buscarTodosProdutos();
        return ResponseEntity.ok().body(requeste);
    }

    @PostMapping
    // Mapeia requisições POST, quando alguém mandar um produto novo no body da requisição cai aqui
    public ResponseEntity<ProdutoModel> criarProdutos(@RequestBody ProdutoModel produtoModel){
    // @RequestBody diz que os dados do novo produto vêm no corpo da requisição (em JSON)
        ProdutoModel requeste = produtoService.criarProduto(produtoModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(produtoModel.getId())
                .toUri();
        return ResponseEntity.created(uri).body(requeste);
    }

    @DeleteMapping("/{id}")
    // Mapeia requisições DELETE, o {id} na URL indica qual produto vai ser deletado
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id){
    // @PathVariable pega o valor do {id} que veio na URL e joga direto no parâmetro "id"
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    // Mapeia GET com um id específico, tipo GET /produtos/5 pra buscar o produto de id 5
    public Optional<ProdutoModel> buscarProdutoPorId(@PathVariable Long id){
    // @PathVariable mesma coisa de antes, captura o {id} da URL
        return produtoService.buscarProdutoId(id);
    }

    @PutMapping("/{id}")
    // Mapeia requisições PUT, usado pra atualizar um produto existente pelo id
    public ResponseEntity<ProdutoModel> atualizarProdutos(@PathVariable Long id, @RequestBody ProdutoModel ProdutoModel){
    // @PathVariable pega o id da URL, @RequestBody pega os dados atualizados do produto no corpo da requisição
        ProdutoModel requeste = produtoService.atualizarProduto(id, ProdutoModel);
        return ResponseEntity.ok().body(requeste);
    }
}