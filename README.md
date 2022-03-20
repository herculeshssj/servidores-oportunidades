Servidores - Oportunidades
==========================

Busca de oportunidades de vagas para servidores públicos.

Desenvolvido com a plataforma RAD OpenXava 6.5.2.

### Criação do container PostgreSQL

```
docker volume create oportunidade-db-data
docker run --name oportunidade-db --restart=unless-stopped -e POSTGRES_PASSWORD="Op0rt7n1d4de*" -e POSTGRES_USER=oportunidade -e POSTGRES_DB=oportunidade -p 5432:5432 -v oportunidade-db-data:/var/lib/postgresql/data -d postgres
```

No seu arquivo /etc/hosts, inclua uma nova entrada apontando para o IP da sua máquina:

```
123.456.789.012 oportunidade-db
```

Localização do arquivo hosts:
- Windows: C:\Windows\System32\drivers\etc
- Linux: /etc

### Docker

Edite o arquivo naviox-users.properties, informando o login e senha dos usuários com permissão de acesso a aplicação.

Formato das entradas:

```
usuario = senha
```

No diretório raiz do repositório, execute a construção da imagem Docker:

```
docker build -t oportunidade:latest .
```

Após a construção da imagem, realize a criação do container:

```
docker run --restart=unless-stopped --name oportunidade --link oportunidade-db -p 8080:8080 -d oportunidade:latest
```

O sistema está acessível em http://<ip>:8080/oportunidade/

Ex: http://192.168.15.10:8080/oportunidade/


### Backup e restore da base

**Backup:**

```
docker run --rm --link oportunidade-db -e PGPASSWORD="Op0rt7n1d4de*" -v "$PWD":/backup postgres pg_dump -U oportunidade -h oportunidade-db -O -F c -b -v -f /backup/oportunidade.backup -W oportunidade
```

**Restauração:**

```
docker run --rm --link oportunidade-db -e PGPASSWORD="Op0rt7n1d4de*" -v "$PWD":/backup postgres pg_restore -U oportunidade -h oportunidade-db -W -v -c --if-exists -O -d oportunidade /backup/oportunidade.backup
```