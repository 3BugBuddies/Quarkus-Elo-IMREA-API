# elo-imrea-quarkus-api

Este projeto é uma API RESTful desenvolvida com o **Quarkus**, o *Supersonic Subatomic Java Framework*, para gerenciar um sistema de informações de saúde/clínica.

A API é responsável pelo gerenciamento completo de dados de pacientes, acompanhantes, profissionais de saúde, agendamentos (atendimentos) e o envio de lembretes automáticos.

## Funcionalidades Principais (Endpoints)

A API expõe os seguintes recursos REST, cada um com as operações CRUD (Criação, Leitura, Atualização, Exclusão) e regras de negócio específicas (BO - Business Objects):

| Recurso | Endpoint Base | Descrição |
| :--- | :--- | :--- |
| **Colaboradores** | `/colaborador` | Gerencia usuários internos/equipe, com validações de CPF exclusivo. |
| **Pacientes** | `/paciente` | Gerencia as informações cadastrais dos pacientes, incluindo diagnóstico e validações de CPF. |
| **Profissionais de Saúde** | `/profissionalsaude` | Gerencia os dados dos profissionais, com validações de CPF e número de documento (ex: CRM, COREN). |
| **Acompanhantes** | `/acompanhante` | Permite o cadastro de acompanhantes, verificando se são maiores de 18 anos. |
| **Atendimentos** | `/atendimento` | Gerencia os agendamentos, incluindo as regras para `concluir` e `cancelar` um atendimento. Controla o status (`MARCADO`, `CANCELADO`, `REMARCADO`, `CONCLUIDO`) e formato (`REMOTO`, `PRESENCIAL`). |
| **Lembretes** | `/lembrete` | Cria mensagens de lembrete com base nos dados do atendimento e permite o reenvio (`reenviar/{id}`). |

## Configuração Técnica

### Tecnologias

* **Framework:** Quarkus
* **Linguagem:** Java 17
* **Build Tool:** Apache Maven
* **APIs Web:** Quarkus REST (JAX-RS) e Jackson para serialização JSON.
* **Validação:** Bean Validation (via `camel-quarkus-bean-validator`) para anotações nos TOs.

### Banco de Dados

A camada de persistência utiliza **JDBC** com um design pattern **DAO** (Data Access Object) e o driver **Oracle (ojdbc11)**.

A conexão é estabelecida por meio de variáveis de ambiente, que devem ser configuradas antes da execução:

* `DB_URL`
* `DB_USER`
* `DB_PASSWORD`

### Configuração CORS

O projeto inclui um `CorsFilter` que habilita as seguintes configurações para facilitar o desenvolvimento e integração com front-ends:

* `Access-Control-Allow-Origin: *` (Permite todas as origens)
* `Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS, HEAD`

## Como Executar a Aplicação

### Modo Desenvolvimento

Para iniciar a aplicação em modo de desenvolvimento com *live coding* (recarregamento automático em mudanças de código):

```shell script
./mvnw quarkus:dev