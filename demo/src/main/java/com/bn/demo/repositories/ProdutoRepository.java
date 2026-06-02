package com.bn.demo.repositories;

import com.bn.demo.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

       UserDetails findByLogin(String role);
}
