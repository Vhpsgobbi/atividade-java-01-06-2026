package com.bn.demo.services;

import com.bn.demo.models.ProdutoModel;
import com.bn.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// fala pro spring q essa classe é um serviço, é aqui onde fica a lógica de verdade da aplicação
public class ProdutoService implements UserDetailsService {
// implements UserDetailsService significa q essa classe também cuida de autenticação de usuário

    @Autowired
    // injeta o repository automaticamente, sem precisar criar na mão
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> buscarTodosProdutos(){
        return produtoRepository.findAll();
        // findAll() vai lá no banco e traz todos os produtos, simples assim
    }

    public ProdutoModel criarProduto(ProdutoModel produtoModel){
        return produtoRepository.save(produtoModel);
        // save() salva o produto no banco, se já existir ele atualiza, se não existir ele cria
    }

    public Optional<ProdutoModel> buscarProdutoId(Long id){
        return produtoRepository.findById(id);
        // findById() busca um produto pelo id, retorna um Optional pq o produto pode não existir
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoModel){
        ProdutoModel model = produtoRepository.findById(id).get();
        // busca o produto atual no banco pelo id
        model.setPreco(produtoModel.getPreco());
        // sobrescreve o preço com o novo valor que veio na requisição
        model.setEstoque(produtoModel.getEstoque());
        // sobrescreve o estoque com o novo valor que veio na requisição
        model.setNome(produtoModel.getNome());
        // sobrescreve o nome com o novo valor que veio na requisição
        return produtoRepository.save(model);
        // salva as alterações no banco e retorna o produto já atualizado
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
        // deleteById() vai lá no banco e apaga o produto com esse id, sem dó nem piedade
    }

    @Override
    // @Override significa q esse método já existia na interface UserDetailsService e tá sendo reescrito aqui
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return produtoRepository.findByLogin(username);
        // busca um usuário pelo login no banco, o spring chama isso automaticamente na hora de autenticar
    }
}