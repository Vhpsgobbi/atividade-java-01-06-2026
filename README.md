VICTOR HUGO PACCHIONI SGOBBI
RA: 24000732-2

1. É um framework do spring que cuida de toda parte de segurança da aplicação, tipo quem pode entrar, quem não pode e como provar que é você mesmo

2. Desativa a proteção contra CSRF, que basicamente barra envio automático de cookies maliciosos, em API REST não precisa disso não

3. Fala pro servidor não guardar sessão de ninguém, cada requisição chega do zero sem o servidor lembrar de nada, tipo amnésia total

4. Libera o acesso público pra aquela rota específica, qualquer um pode bater naquele endpoint sem precisar se autenticar, sem token, sem nada

5. Filtra quais rotas e quais métodos HTTP vão receber uma regra de segurança específica, tipo "essa regra aqui só vale pra esse caminho e esse método"

6. Cria uma regra de segurança pra todo o resto que você não configurou antes, funciona como um "e o que sobrou?" da sua configuração de segurança

7. Aplicação que não guarda memória nenhuma das requisições anteriores, cada vez que o cliente manda uma requisição é como se fosse a primeira vez

8. Configura o gerenciamento de sessões do spring security pra não criar sessão HTTP nenhuma, ou seja, modo stateless na prática



