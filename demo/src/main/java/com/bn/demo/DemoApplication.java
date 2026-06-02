package com.bn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// essa anotação faz um monte de coisa ao mesmo tempo, ela ativa o spring, configura tudo automaticamente e faz o scan de todos os componentes da aplicação
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// essa linha ela liga a aplicação inteira, sem ela não roda nada
	}

}