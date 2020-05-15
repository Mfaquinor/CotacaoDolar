# Cotação Dólar API

API para consulta de de cotação do dólar para datas especificas e monitoramento de carga nas requisições.

## Especificação de Requisitos

A especificação de Requisitos pode ser encontrada acessando [Aqui!](https://github.com/Mfaquinor/DesafioCotacaoDolar/blob/master/Requirements.md)

## Tecnologias Utilizadas

- [Quarkus](https://quarkus.io/)
- [Swagger](https://swagger.io/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)
- [Docker](https://www.docker.com/)
- [PostgreSQL](https://www.postgresql.org/)

## Começando Projeto

Empacotando o projeto Quarkus

	cd cotacao
	./mvnw package

Ao término do empacotamento, execute:

	docker-compose up

O docker se encarregará de subir todos os serviços.

## Acessando os Serviços

A seguir as URL's que apontam para os respectivos serviços:

- API - [http://localhost:8080/](http://localhost:8080/)
- Swagger - [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
- Prometheus - [http://localhost:9090/](http://localhost:9090/)
- Grafana - [http://localhost:3000/](http://localhost:3000/)

## Buscando Cotação do Dólar

Acessando os recursos a seguir é possível recuperar a cotação por datas especificas.

Descrição | Método | Endpoint | Parâmetros
--------- | ------ | -------- | ----------
Busca Data Atual | GET | http://localhost:8080/dollar | ---------------
Busca Data Especifica | GET | http://localhost:8080/dollar/{date} | {date} = 01-01-2010

## Rodando os Testes

	cd cotacao
	./mvnw test