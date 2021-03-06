# Sicredi enquetes


No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Essa api serve para você gerenciar essas sessões de votação.

Com essa API REST é possível:

● Cadastrar uma nova pauta.

● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default).

● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada
associado é identificado por um id único e pode votar apenas uma vez por pauta).

● Contabilizar os votos e dar o resultado da votação na pauta.


! Qualquer chamada para as interfaces pode ser considerada como autorizada !

# Tecnologias utilizadas
 - Spring boot
 - Junit
 - Mockito
 - Java 8
 - Flyway
 - Lombok
 - MySQL
 - JMS + ActiveMQ
 - Docker
 - Swagger2
 
# Método de desenvolvimento
- Para persistir os associados,enquetes e votações foi utilizado a banco de dados Postgres.
- Para contabilizar a votação de enquetes já encerradas, foi implementado um agendador, que, de hora
em hora busca por enquetes encerradas, contabiliza seus votos e atualiza o resultado final da votação.
- Para documentar a api foi utilizado o Swagger2.
- Teste unitário implementado somente no controlador do associado, por questões de tempo.

# Configuração docker

Em ambiente local, Com o docker rodando, executar os seguintes comandos : 

docker container run --name dbsicredi -e MYSQL_ROOT_PASSWORD=root -d -p 3308:3306 mysql:5.7.31

docker container run -p 61616:61616 -p 8161:8161 --name activemq rmohr/activemq

