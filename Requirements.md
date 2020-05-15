# Especificação de Requisitos

## 1. Introdução

Este documento tem como objetivo especificar a implementação da "API de Cotação do Dólar" bem como elucidar os critérios de aceitação e cenários de testes da ferramenta.

## 2. Descrição Geral

### Perspectiva do Produto

### Funções do Produto

Número | Função | Descrição
-------| -------|----------
01| Busca de Cotação do Dólar | Busca em uma API Pública a cotação do dólar em datas especificas.
02| Processamento das Buscas | Persistência em um banco de dados todos os registros de buscas ocorridos no sistema.
03 | Monitoramento das Buscas | Monitoramento e extração de métricas das buscas realizadas no sistema.

### 3.  Requisitos Específicos

### Requisitos Funcionais

Identificador | RF001
--------------|-------
Nome | Buscar cotação do dólar na data atual

Deve-se criar uma Rest API, no qual, por padrão, ao se realizar uma requisição pelo método HTTP GET deve consultar a API pública  ([https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios](https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios)), buscando a cotação do dólar para o dia atual.

A API Rest deve retornar para o usuário final uma reposta em format JSON contendo as seguintes informações:

- A cotação do dólar de venda
- A cotação do dólar de compra
- A data o qual a requisição foi feita (atual)
- A data da ultima atualização da cotação.

Identificador | RF002
--------------|-------
Nome | Buscar cotação do dólar para datas especificas

Deve-se criar uma Rest API, no qual, por padrão, ao se realizar uma requisição pelo método HTTP GET deve consultar a API pública  ([https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios](https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios)), buscando a cotação do dólar para o dia especificado por um parâmetro passado na URL da requisição.

A API Rest deve retornar para o usuário final uma reposta em format JSON contendo as seguintes informações:

- A cotação do dólar de venda
- A cotação do dólar de compra
- A data o qual a requisição foi feita (atual)
- A data da ultima atualização da cotação.

Deverá haver ao menos um teste automatizado para realizar uma validação de data especifica com sucesso.

Identificador | RF003
--------------|-------
Nome | Busca de cotação em feriados e finais de semana

A API deve verificar em cada consulta da cotação do dólar em uma data especifica, se a mesma é um fim de semana ou um feriado, se verdadeiro buscar a cotação do dólar no último dia útil a data desejada, se falso retornar a cotação do dólar no dia atual.

 A API Rest deve retornar para o usuário final uma reposta em format JSON contendo as seguintes informações:
 
- A cotação do dólar de venda
- A cotação do dólar de compra
- A data o qual a requisição foi feita (atual)
- A data da ultima atualização da cotação (dia útil).

Deverá haver ao menos dois testes automatizados para realizar uma validação de datas em finais de semana e feriados.

Identificador | RF004
--------------|-------
Nome | Busca com datas limites no passado e futuro.

Caso o usuário entre com uma data no futuro, a API deve retornar um erro 400 (Bad Request) e uma mensagem de erro informando que datas futuras não são permitidas.

A API deve permitir a busca de cotação do dólar para datas após o dia 10-01-1990, caso o usuário entre com uma data anterior a esta, a API deve retornar um erro 400 (Bad Request) e uma mensagem de erro informando que datas anteriores a esta não são válidas.

Deverá haver ao menos dois testes automatizados para realizar uma validação de datas limites no passado e no futuro.

Identificador | RF005
--------------|-------
Nome | Formatação da data de busca.

A API deve permitir a entrada de datas no formato 'dd-MM-yyyy', caso contrário deverá retornar o erro 400 (Bad Request) e uma mensagem de erro informando a formatação válida da data.

Deverá haver ao menos um teste automatizado para realizar uma validação para o erro apresentado.

Identificador | RF006
--------------|-------
Nome | Captura e processamento das requisições de busca.

A API deve interceptar as requisições de busca e persistir os seguintes dados em um banco de dados relacional:

- Um ID da requisição gerado automaticamente 
- O timestamp atual da requisição
- A cotação do dólar de venda
- A cotação do dólar de compra
- A data o qual a requisição foi feita (atual)
- A data e hora da ultima atualização da cotação (dia útil).

Identificador | RF007
--------------|-------
Nome | API de Documentação.

Deverá ser implementado na API a ferramenta "Swagger" para ler os endpoints da aplicação e gerar automaticamente a documentação das funcionalidades suportadas pela aplicação.

Identificador | RF008
--------------|-------
Nome | API de Documentação.

Deverá ser implementado na API as ferramentas "Prometheus" e "Grafana" para monitoramento e exibição de métricas relacionadas a API, os seguintes dados devem ser extraídos da API:

- Um contador do número de requisições realizadas até o momento.
- Um temporizador, para calcular o tempo de duração de cada requisição.
- Um observador da memória disponível na máquina virtual da aplicação.

Identificador | RF009
--------------|-------
Nome | Tela de busca de cotações.

Deverá ser implementada uma tela que conterá um campo do tipo data e um botão de "Busca", que ao ser disparado, envia uma requisição para a API Rest e imprime na tela o resultado obtido da mesma.

Identificador | RF010
--------------|-------
Nome | Conteirização.

A API Rest, a Interface gráfica, o banco de dados e todas as ferramentas devem ser encapsuladas em docker para facilitar a implantação em ambientes em nuvem. 
