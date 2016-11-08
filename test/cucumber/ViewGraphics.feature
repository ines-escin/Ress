Feature: Visualização de gráficos de coletas
  As um usuário
  I want to visualizar gráficos de todas as coletas em um determinado período de tempo
  So that eu possa estudar e analisar coletas de uma região em um período de tempo.


#GUI
  Scenario: Nenhuma coleta realizada na última semana
    Given Nenhuma coleta foi realizada no último mês
    And Eu estou na tela de opções de gráficos
    When Eu seleciono a opção “Último mês”
    Then Eu posso ver uma tela com um gráfico vazio

  Scenario: Seleção de gráfico de coletas da última semana
    Given Eu estou na tela de opções de gráficos
    And Foram realizadas apenas 2 coletas no dia “07/11/2016”
    When Eu seleciono a opção “Último ano”
    Then Eu posso ver um gráfico Coletas x Data que possui apenas a altura 2 quando x = “07/11/2016”


  Scenario: Seleção de gráfico de coletas do último mês
    Given Eu estou na tela de opções de gráficos
    And Foram realizadas apenas 2 coletas no dia “07/11/2016”
    When Eu seleciono a opção “Último mês”
    Then Eu posso ver um gráfico Coletas x Data que possui apenas a altura 2 quando x = “07/11/2016”

#controle
  Scenario: Solicitação de gráficos de coletas
    Given Existe uma lista de coletas realizadas
    When Eu seleciono a opção “Último mês”
    Then A lista de coletas realizadas não muda