# 📁 Sistema de Gerenciamento de Projetos (JPA + DAO)

Este projeto é um sistema acadêmico desenvolvido em **Java** utilizando **JPA (EclipseLink)** e o padrão **DAO**, com o objetivo de praticar mapeamento objeto-relacional, relacionamentos entre entidades e operações CRUD com persistência em banco de dados relacional.

---

## 🎯 Objetivos do Projeto

- Aplicar conceitos de **JPA**
- Implementar o padrão **DAO**
- Modelar corretamente relacionamentos entre entidades
- Compreender **fetch types**, **cascade**, **entidades dominantes** e **integridade referencial**
- Persistir dados em banco **MySQL**

---

## 🛠️ Tecnologias Utilizadas

- **Java**
- **JPA (EclipseLink 2.5.x)**
- **MySQL**
- **Maven**
- **Padrão DAO**
- **JPAUtil para gerenciamento do EntityManager**

---

## 📊 Modelo Conceitual

O sistema é composto pelas seguintes entidades principais:

- **Projeto**
- **Coordenador**
- **Tarefa**
- **Detalhe**

---

## 🔗 Relacionamentos entre Entidades

### 🧑‍💼 Projeto ↔ Coordenador
- **Tipo:** `OneToOne`
- **Direção:** Unidirecional
- **Acesso:** Apenas `Projeto` acessa `Coordenador`
- **Cascade:** ❌ Nenhum
- **FetchType:** `LAZY`

📌 Justificativa: o coordenador existe independentemente do projeto.

---

### 📁 Projeto ↔ Tarefa
- **Tipo:** `OneToMany / ManyToOne`
- **Direção:** Bidirecional
- **Classe dominante:** `Tarefa`
- **Projeto possui várias tarefas**
@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Tarefa> tarefas;
@ManyToOne
@JoinColumn(name = "idProjeto")
private Projeto projeto;
📌 
  Justificativa:
- Uma tarefa não faz sentido sem um projeto
- Ao remover um projeto, todas as tarefas associadas são removidas automaticamente

### 📝 Tarefa ↔ Detalhe
- **Tipo:** `OneToMany / ManyToOne`
- **Direção:** Unidirecional (Tarefa → Detalhe)
- **Cascade:** ALL
- **FetchType:** `LAZY`

  Justificativa:

- Detalhes pertencem exclusivamente a uma tarefa
- Não existe sentido em manter um detalhe sem sua tarefa

## FetchType Utilizado 
Relacionamento          FetchType 
Projeto → Coordenador   LAZY 
Projeto → Tarefas       LAZY
Tarefa → Detalhes       LAZY

Observação: FetchType EAGER foi evitado para prevenir carregamento excessivo e problemas de performance.

## Tratamento de Exclusão e Integridade Referencial
Durante o desenvolvimento, foi enfrentado o erro:

Cannot delete or update a parent row: a foreign key constraint fails

✅ Solução adotada:
Uso de:
cascade = CascadeType.ALL
orphanRemoval = true

Isso garante que:
- Ao remover um projeto, suas tarefas também são removidas
- Evita violações de chave estrangeira no banco de dados

## Padrão DAO
- Cada entidade possui um DAO responsável pelas operações CRUD, garantindo:
- Separação de responsabilidades
- Código mais organizado
- Facilidade de manutenção

## Aprendizados:
- Diferença entre classe dominante e classe inversa
- Impacto de cascade e orphanRemoval
- Problemas comuns de integridade referencial
- Diferença entre carregamento LAZY e EAGER
- Boas práticas em projetos JPA

## 👤 Autor:  
Gabriel da Silva Nunes  
Estudante de Sistemas para Internet  
Projeto acadêmico desenvolvido para estudo de JPA e persistência de dados  
  
## Observação:  
Este projeto possui fins educacionais e foi desenvolvido como parte do aprendizado em Programação para Web I.
