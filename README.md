# Microsserviço REST — Tabela FIPE
 Microsserviço REST desenvolvido em **Java 17** com **Spring Boot**, responsável por consumir a **API pública da Tabela FIPE** e calcular a **variacão absoluta e percentual do valor de um veiculo ao longo dos anos de fabricação**.

O projeto foi desenvolvido como teste técnico, com foco em:

- boas práticas de desenvolvimento

- arquitetura limpa

- clareza de responsabilidades

- legibilidade e manutenibilidade

- design correto das classes e regras de negócio


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
