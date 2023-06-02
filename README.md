# Documentação da API de Relatórios
Este repositório contém a API de Relatórios Flexpag. A coleção oferece um conjunto de endpoints para gerar relatórios diversos.

## Uso
Para utilizar a API de Relatórios, siga as instruções abaixo:

Clone o repositório em sua máquina local. Importe a coleção do Postman para a sua aplicação do Postman. Verifique se as dependências, variáveis de ambiente e o banco de dados estão configurados corretamente. Faça requisições para os endpoints desejados.
## Endpoints
A API de Relatórios disponibiliza os seguintes endpoints:

### Obter Link de Download:
Baixar relatório: Obtém o link de download de um relatório específico.
### Gerar Relatórios:
Gerar relatório: Gera um relatório de clientes.
Generate Transaction Report: Gera um relatório de transações.
### Parâmetros:
A coleção do Postman utiliza os seguintes parâmetros:

reportType: Tipo de relatório a ser gerado.
createdAt: Data de criação do relatório.
paymentType: Tipo de pagamento.
clientId: Id do cliente
status: Status do pagamento.

Certifique-se de configurar corretamente os parâmetros antes de fazer requisições para os endpoints de geração de relatórios, os parâmetros podem variar de acordo com o tipo de parâmetro.

## URL Base da API
A URL base da API de Relatórios é http://localhost:8090/.

### Informações Adicionais
Para obter mais informações sobre cada endpoint, incluindo os dados da requisição e da resposta, consulte as requisições individuais na coleção do Postman.

### Postman collection:
Uma collection do postman está disponível na raíz do projeto.
