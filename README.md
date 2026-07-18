# Sobrevivência Jurássica

Jogo em modo texto (terminal), desenvolvido como trabalho avaliativo da disciplina de Programação Orientada a Objetos do curso de Ciência da Computação da Universidade Federal de Pelotas (UFPel).

**Autores:** Felipe Lima Rodrigues e Gabriela de Carvalho Bruno

## Sobre o jogo

O jogo se passa em um parque cujas instalações foram invadidas por dinossauros. O jogador assume o papel de um humano que deve eliminar todas as ameaças do complexo antes de ser morto, explorando o mapa em busca de suprimentos — como armamentos e equipamentos médicos — enquanto enfrenta quatro tipos distintos de dinossauros, cada um com comportamento próprio.

O mapa é gerado e exibido em uma grade no terminal, com movimentação livre do personagem, combates por turnos baseados em rolagem de dados e um sistema de inventário para gerenciar itens coletados.

## Dinossauros (inimigos)

São quatro tipos de dinossauros, cada um com um comportamento de movimento próprio (padrão Strategy):

- **Tiranossauro Rex** — o mais forte, causa dois pontos de dano por ataque (os demais causam apenas um).
- **Velociraptor** — possui comportamento de movimento próprio e ágil.
- **Troodonte** — possui comportamento perseguidor.
- **Compsognato** — o menor e mais fraco dos inimigos.

Os comportamentos de movimento incluem: aleatório, perseguidor, exclusivo do velociraptor e parado.

## Itens

Espalhados pelo mapa em caixas de suprimentos, podendo ser:

- **Kit Médico** — recupera pontos de vida do personagem.
- **Bastão Elétrico** — arma corpo a corpo, com dano calculado a partir da rolagem de um dado de seis lados.
- **Dardos** — arma à distância com munição limitada, sempre causa dano crítico ao ser usada.

## Dificuldade

Ao iniciar o jogo, o jogador escolhe entre três níveis de dificuldade:

1. Fácil
2. Médio
3. Difícil

A dificuldade escolhida influencia a percepção dos inimigos e o tamanho do mapa gerado.

## Requisitos

- Java 25 ou superior
- Maven 3.6 ou superior
- IDE recomendada: Visual Studio Code com a extensão Java Extension Pack

## Como compilar e rodar

### Linux

```bash
# 1. Clone ou extraia o projeto
# 2. Acesse a pasta raiz do projeto pelo terminal
mvn compile
mvn exec:java -Dexec.mainClass="trabalho.sobrevivenciajurassica.SobrevivenciaJurassica"
```

### Windows

```powershell
# 1. Clone ou extraia o projeto
# 2. Acesse a pasta raiz do projeto pelo Prompt de Comando ou PowerShell
mvn compile
mvn exec:java -Dexec.mainClass="trabalho.sobrevivenciajurassica.SobrevivenciaJurassica"
```

## Estrutura e conceitos de POO

O projeto aplica os principais pilares da Programação Orientada a Objetos:

- **Herança**: hierarquia `ElementoMapa` → `EntidadeViva` → `Personagem`/`Dinossauro`, e `Itens` → `Arma`/`KitMedico`.
- **Polimorfismo**: métodos `atacar()` (cada dinossauro ataca de forma diferente) e `mover()` (cada comportamento de movimento é único).
- **Encapsulamento**: atributos privados/protegidos acessados via getters e setters, garantindo regras como limites de saúde.
- **Interfaces**: `Atacante` e `ComportamentoMovimento`, permitindo tratamento uniforme de diferentes implementações.
- **Composição**: `Personagem` possui um `Inventario`; `Dinossauro` possui um `ComportamentoMovimento` (padrão de projeto Strategy).

## Status

Jogo funcional em terminal, com geração aleatória de mapa, sistema de combate baseado em dados, diferentes comportamentos de inimigos e sistema de inventário.