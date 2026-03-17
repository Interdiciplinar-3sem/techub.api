# techub.api

Plataforma de resumos compartilhados por universitários.

## O que você precisa

- Docker Desktop instalado e aberto
- Java 21 instalado
- Maven (ou usar o `mvnw.cmd` do projeto)

## Como rodar com Docker (passo a passo)

### 1) Subir o banco de dados

```powershell
docker compose up -d
```

### 2) Conferir se o banco subiu

```powershell
docker compose ps
```

Se estiver tudo certo, você vai ver o serviço `postgres` em execução.

### 3) Iniciar a aplicação

No diretório do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

Se preferir Maven global:

```powershell
mvn spring-boot:run
```

A API sobe na porta `3000`.

## Como parar tudo

```powershell
docker compose down
```

## Problemas comuns (rápido)

- Erro de porta `5432` em uso: feche outro PostgreSQL local ou altere a porta no `docker-compose.yml`.
- Erro de nome de container `techub-db` em uso:

```powershell
docker rm -f techub-db
docker compose up -d
```
