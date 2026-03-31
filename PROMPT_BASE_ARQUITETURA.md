# 📋 PROMPT BASE - Criação de Fluxo Completo Clean Architecture

Use este prompt como template para criar novos fluxos de negócio. Basta copiar, preencher os placeholders e enviar.

---

## 🎯 Template de Requisição

```
Crie um use case de [NOME_DA_ACAO] da entidade [ENTIDADE] seguindo a arquitetura limpa.

Requisitos:
- [VALIDAÇÃO_1]
- [VALIDAÇÃO_2]
- [VALIDAÇÃO_3]
- Retorno com os campos: [CAMPO_1], [CAMPO_2], [CAMPO_3]
- [REQUISITO_ESPECÍFICO]

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

---

## 📝 Exemplos Prontos para Copiar e Colar

### Exemplo 1: Deletar (Soft Delete)

```
Crie um use case de deletar um produto da entidade Product seguindo a arquitetura limpa.

Requisitos:
- Validar se o produto existe
- Implementar soft delete (apenas marcar deletedAt)
- Validar se o produto já foi deletado
- Retorno simples com status de sucesso
- Endpoint DELETE /products/{id}

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

### Exemplo 2: Buscar por ID

```
Crie um use case de buscar um produto por ID da entidade Product seguindo a arquitetura limpa.

Requisitos:
- Validar se o ID é válido
- Retornar erro 404 se não encontrar
- Listar todos os campos menos o deletedAt
- Endpoint GET /products/{id}

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

### Exemplo 3: Filtro Avançado

```
Crie um use case de buscar produtos por múltiplos filtros da entidade Product seguindo a arquitetura limpa.

Requisitos:
- Filtro por nome (LIKE)
- Filtro por preço mínimo e máximo
- Filtro por data de criação (entre datas)
- Paginação
- Retorno com campos: id, name, price, createdAt
- Endpoint GET /products/search?name=...&minPrice=...&maxPrice=...&fromDate=...&toDate=...&page=0&pageSize=10

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

---

## 🏗️ Estrutura Criada Automaticamente

Quando você usar o prompt, vou criar automaticamente:

### 1. **DTOs** (Application Layer)
- Input DTO com validações
- Output DTO com campos apropriados

### 2. **Domain Layer**
- Interface do Use Case
- Interface(s) do Repository (se necessário)

### 3. **Application Layer**
- Implementação do Use Case com validações
- Lógica de negócio

### 4. **Infrastructure Layer**
- Atualizar `Repository` existente ou criar novo
- Adicionar métodos ao JPA Repository
- Implementação do Repository com conversões
- Adicionar mappers necessários
- Atualizar Controller com novo endpoint

---

## 🎯 Checklist de Arquitetura

Cada fluxo criado terá:

- ✅ **Entidade de Domínio** - Lógica pura de negócio
- ✅ **Repository Abstrato** - Interface no domínio
- ✅ **Use Case Interface** - Contrato de negócio
- ✅ **Use Case Implementação** - Orquestração e validações
- ✅ **DTOs** - Input/Output tipados
- ✅ **Entidade JPA** - Mapeamento para banco
- ✅ **JPA Repository** - Operações de banco
- ✅ **Repository Implementação** - Conversões entre camadas
- ✅ **Mapper** - Conversões automáticas (MapStruct)
- ✅ **Controller** - Endpoint REST com HTTP apropriado

---

## 🚀 Tipos de Operações Suportadas

### CRUD Completo
- ✅ **CREATE** - POST /resource
- ✅ **READ (List)** - GET /resource?filters&page
- ✅ **READ (By ID)** - GET /resource/{id}
- ✅ **UPDATE** - PUT /resource/{id}
- ✅ **DELETE** - DELETE /resource/{id}

### Operações Customizadas
- ✅ Busca com múltiplos filtros
- ✅ Filtros por intervalo de data
- ✅ Filtros por range de valores
- ✅ Soft delete
- ✅ Ativação/Desativação
- ✅ Bulk operations

---

## 💡 Dicas de Requisição

### Para Operations Simples
```
Crie um use case de [AÇÃO] de [ENTIDADE] seguindo a arquitetura limpa.

Requisitos:
- [REQUISITO_1]
- [REQUISITO_2]

Endpoint: [METODO] /[ROTA]
```

### Para Operations Complexas
```
Crie um use case de [AÇÃO] de [ENTIDADE] seguindo a arquitetura limpa.

Requisitos de Validação:
- [VALIDAÇÃO_1]
- [VALIDAÇÃO_2]

Requisitos de Negócio:
- [NEGÓCIO_1]
- [NEGÓCIO_2]

Requisitos de Resposta:
- Retornar: [CAMPOS]
- Formato: [FORMATO]

Filtros/Parâmetros:
- [FILTRO_1]
- [FILTRO_2]

Endpoint: [METODO] /[ROTA]
```

---

## 🔗 Padrões HTTP Utilizados

| Operação | Método | Status | Descrição |
|----------|--------|--------|-----------|
| Criar | POST | 201 | Created - Com body de resposta |
| Listar | GET | 200 | Ok - Com lista e paginação |
| Buscar um | GET /{id} | 200 | Ok - Com objeto único |
| Atualizar | PUT /{id} | 200 | Ok - Com objeto atualizado |
| Deletar | DELETE /{id} | 204 | No Content - Sem body |
| Soft Delete | DELETE /{id} | 204 | No Content - Marca deletedAt |

---

## 🛡️ Validações Automáticas

Vou incluir automaticamente:

- ✅ Validação de campos obrigatórios (@NotNull, @NotBlank)
- ✅ Validação de tamanho/comprimento
- ✅ Validação de formato (email, números, etc)
- ✅ Validação de ranges (min/max)
- ✅ Validação de existência (id válido)
- ✅ Validação de soft delete (não retornar deletados)
- ✅ Trim de espaços em branco

---

## 📂 Estrutura de Pastas Padrão

```
src/main/java/ecommerce/shop/
├── domain/
│   ├── entity/
│   │   └── [Entidade].java
│   ├── repository/[entidade]/
│   │   └── [Ação][Entidade]Repository.java
│   └── usecase/[entidade]/
│       └── [Acao][Entidade]UseCase.java
├── application/
│   ├── dto/[entidade]/
│   │   ├── request/
│   │   │   └── [Acao][Entidade]InputDTO.java
│   │   └── response/
│   │       └── [Acao][Entidade]ResponseDTO.java
│   └── usecase/[entidade]/
│       └── [Acao][Entidade]UseCaseImpl.java
└── infrastructure/
    ├── persistence/
    │   ├── entity/
    │   │   └── [Entidade]JpaEntity.java
    │   ├── repository/[entidade]/
    │   │   ├── [Entidade]JpaRepository.java
    │   │   └── [Entidade]RepositoryImpl.java
    │   └── mapper/
    │       └── [Entidade]StructMapper.java
    └── web/
        └── controller/
            └── [Entidade]Controller.java
```

---

## 🎓 Exemplos de Requisições Bem Estruturadas

### Exemplo: Listar Usuários com Filtros Avançados
```
Crie um use case de buscar usuários com filtros da entidade User seguindo a arquitetura limpa.

Requisitos de Validação:
- Email deve estar em formato válido se fornecido
- Data de criação deve ser válida se fornecida

Requisitos de Negócio:
- Filtrar apenas usuários ativos (deletedAt IS NULL)
- Filtro por email (LIKE)
- Filtro por role (ADMIN, USER, etc)
- Filtro por data de criação (entre datas)
- Paginação

Requisitos de Resposta:
- Retornar: id, name, email, role, createdAt
- Não retornar: password, deletedAt

Endpoint: GET /users/search?email=...&role=...&fromDate=...&toDate=...&page=0&pageSize=10

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

### Exemplo: Ativar/Desativar Produto
```
Crie um use case de ativar/desativar um produto da entidade Product seguindo a arquitetura limpa.

Requisitos:
- Receber um booleano indicando se deve ativar ou desativar
- Se desativar, marcar deletedAt com data atual
- Se ativar, limpar deletedAt
- Validar se produto existe
- Não permitir ativar se já está ativo

Endpoint: PATCH /products/{id}/toggle-status

Não precisa criar arquivos MD ao final da tarefa.
Não peça para executar comandos de compilação.
```

---

## 🔄 Fluxo de Criação

Quando você enviar a requisição, vou:

1. ✅ Criar **DTOs** com validações apropriadas
2. ✅ Criar **Interfaces** no domain layer
3. ✅ Criar **Implementação** do use case com validações
4. ✅ **Atualizar ou criar** repository abstrato
5. ✅ **Atualizar** JPA repository com queries necessárias
6. ✅ **Implementar** repository com conversões
7. ✅ **Atualizar** mapper com novos mapeamentos
8. ✅ **Atualizar** controller com novo endpoint
9. ✅ **Validar** erros de compilação

---

## ❓ Dúvidas Frequentes

**P: Posso usar fields opcionais?**
R: Sim! Use `@NotNull` apenas para obrigatórios, o resto é opcional.

**P: Como faço soft delete?**
R: Use `@NotNull deletedAt` na entidade, valide `deletedAt IS NULL` nas queries.

**P: Posso filtrar por múltiplos campos?**
R: Sim! Todos os filtros vão no DTO de entrada com validações apropriadas.

**P: E se o recurso não existir?**
R: Vou usar `Optional` e lançar `IllegalArgumentException` com mensagem clara.

**P: Como funciona a paginação?**
R: Automaticamente com `PageRequest.of()` e retorno de `Page<T>` com metadados.

---

**Última Atualização**: 31/03/2026 | **Versão**: 1.0

