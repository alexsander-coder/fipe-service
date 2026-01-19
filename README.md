# Microsserviço REST — Tabela FIPE
 Microsserviço REST desenvolvido em **Java 17** com **Spring Boot**, responsável por consumir a **API pública da Tabela FIPE** e calcular a **variacão absoluta e percentual do valor de um veiculo ao longo dos anos de fabricação**.

O projeto foi desenvolvido como teste técnico, com foco em:

- boas práticas de desenvolvimento

- arquitetura limpa

- clareza de responsabilidades

- legibilidade e manutenibilidade

- design correto das classes e regras de negócio


## Pré-requisitos

Antes de executar o Backend, é necessário ter instalado na máquina:

- **Java 17**
- **Maven 3.8+**

> O projeto foi desenvolvido e testado utilizando Java 17.

## Clonando o Repositório

1. Clone o repositório para sua máquina local:

```bash
git clone https://github.com/alexsander-coder/fipe-service.git
```

2. Acesse a pasta do projeto:
```bash
cd fipe-service
```

## Funcionalidades

- Consumo da **API FIPE v2**
- Consulta de valores de um veiculo por ano
- Cálculo da:
  - variação absoluta de preço
  - variação percentual entre anos consecutivos
- Retorno ordenado do **ano mais recente para o mais antigo**
- Exposição via **endpoint REST**


## Considerações de Design

- As regras de cálculo estão isoladas no domínio, facilitando testes e evolução
- O domínio não depende de frameworks
- Comunicação externa desacoplada por meio de clientes dedicados
- Código orientado à legibilidade e fácil manutenção


## Decisão Arquitetural e Responsabilidade do Endpoint

Este microsserviço foi estruturado seguindo princípios de **Clean Architecture** e **Domain-Driven Design (DDD)**, mantendo uma separação clara entre **domínio**, **casos de uso** e **infraestrutura**.

O endpoint exposto pelo backend possui **responsabilidade única** e está diretamente relacionado ao domínio do problema proposto no desafio.

### Responsabilidades do endpoint

O endpoint é responsável por:

- Orquestrar chamadas à API pública da Tabela FIPE
- Consolidar os valores históricos de um veículo ao longo dos anos
- Aplicar regras de negócio do domínio, incluindo:
  - Cálculo da variação absoluta de preço
  - Cálculo da variação percentual entre os anos
- Retornar os dados já **processados e prontos para consumo**, sem delegar regras de negócio ao frontend

Toda a lógica de negócio, cálculos e regras do domínio estão **centralizadas na camada de domínio/aplicação**, garantindo:

- Consistência dos cálculos
- Reutilização de regras
- Facilidade de manutenção e evolução
- Independência de frameworks e de detalhes de infraestrutura

---

## Consumo direto da API FIPE no frontend


As informações de **marcas** e **modelos** são consumidas **diretamente pelo frontend** a partir da API pública da Tabela FIPE.

Essa decisão foi tomada de forma intencional e estratégica, considerando que:

- São dados **públicos, estáticos e sem regra de negócio**
- Não fazem parte do **domínio central** do problema
- Não exigem validação, transformação ou cálculo
- Não agregariam valor ao domínio caso fossem encapsulados no backend

Encapsular essas chamadas no backend resultaria apenas em um **proxy de dados**, o que é considerado um *anti-pattern* em arquiteturas orientadas a domínio, por introduzir complexidade sem benefício real.

---

## Benefícios da abordagem adotada

- Backend focado exclusivamente em **regras de negócio e casos de uso**
- Redução de acoplamento entre frontend e backend
- Menor volume de código redundante
- Arquitetura mais simples, clara e sustentável
- Aderência aos princípios **SRP (Single Responsibility Principle)**

---


## Exemplo de Requisição

**Request**
```http
GET /api/fipe/analise?vehicleType=carros&brandId=59&modelId=5940
```

**Response**
```http
[
  {
    "year": 2024,
    "price": 82000.00,
    "absoluteVariation": -1500.00,
    "percentageVariation": -1.79
  },
  {
    "year": 2023,
    "price": 83500.00,
    "absoluteVariation": 2500.00,
    "percentageVariation": 3.08
  }
]
```


## Arquitetura

O projeto segue uma arquitetura em camadas, com **separação clara de responsabilidades**:

```text
fipe
├── FipeApplication.java
├── application
│   └── service
│       └── FipeAnalysisService.java
├── config
│   └── CorsConfig.java
├── domain
│   ├── calculator
│   │   └── PriceVariationCalculator.java
│   └── model
│       └── VehicleYearValue.java
├── infrastructure
│   └── client
│       ├── FipeClient.java
│       └── dto
│           ├── FipeVehicleValueDTO.java
│           └── FipeYearDTO.java
└── web
    └── controller
        └── FipeController.java
