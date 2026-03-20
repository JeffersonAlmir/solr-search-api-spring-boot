# 🔍 API de Busca Inteligente com Apache Solr + PostgreSQL

API REST desenvolvida com **Spring Boot** que combina persistência de dados no **PostgreSQL** com busca aproximada (fuzzy search) via **Apache Solr**.

---

## 📖 Sobre o Projeto

A API permite cadastrar, listar, atualizar e deletar produtos, com suporte a **busca aproximada** por nome utilizando o Apache Solr com o algoritmo **EDIsMax** e distância de edição (fuzzy `~2`). Os dados são persistidos no PostgreSQL e indexados no Solr simultaneamente.

---

## ✅ Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker e Docker Compose

---

## 🚀 Como Executar

### Opção 1 — Docker Compose (recomendado)

Sobe a API, PostgreSQL e Solr com um único comando.

**1. Clone o repositório**

```bash
git clone https://github.com/seu-usuario/solr-spring.git
cd solr-spring
```

**2. Suba os containers**

```bash
docker-compose up -d
```

**3. Acompanhe os logs**

```bash
docker-compose logs -f
```

A aplicação estará disponível em `http://localhost:8080` assim que todos os serviços estiverem healthy.

**5. Para derrubar os containers**

```bash
docker-compose down
```

---

### Opção 2 — Executar localmente

**1. Suba o PostgreSQL e o Solr manualmente**

```bash
# PostgreSQL
docker run -d \
  --name postgres-solr \
  -e POSTGRES_DB=example-solr \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=123456 \
  -p 5434:5432 \
  postgres:15-alpine

# Apache Solr
docker run -d \
  --name solr \
  -p 8983:8983 \
  solr:10.0 \
  solr-precreate produtos
```

**2. Execute a aplicação**

```bash
mvn spring-boot:run
```

---

## 📄 Documentação interativa

Acesse o Swagger UI em: `http://localhost:8080/swagger-ui.html`
