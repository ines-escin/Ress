Feature: Visualização de gráficos de coletas
  As um usuário
  I want to visualizar gráficos de todas as coletas em um determinado período de tempo
  So that eu possa estudar e analisar coletas de uma região em um período de tempo.


#GUI
  Scenario: Nenhuma coleta realizada na última semana
    Given Eu estou na tela de opções de gráficos
    And A data atual é 13/10/2016
    And Nenhuma coleta foi realizada na última semana
    When Eu seleciono a opção “Última Semana”
    Then Eu posso ver uma tela com um gráfico vazio

  Scenario: Seleção de gráfico de coletas da última semana
    Given Eu estou na tela de opções de gráficos
    And Foram realizadas apenas 3 coletas no dia “16/10/2016” e apenas 2 coletas no dia “13/10/2016”
    And A data atual é 17/10/2016
    When Eu seleciono a opção “Última semana”
    Then Eu posso ver um gráfico Coletas x Data que possui apenas as alturas 2 quando x = “13/10/2016” e 3 quando x = “16/10/2016”


  Scenario: Seleção de gráfico de coletas do último mês
    Given Eu estou na tela de opções de gráficos
    And Foram realizadas apenas 5 coletas no dia “26/10/2016” e apenas 6 coletas no dia “15/11/2016”
    And A data atual é 16/11/2016
    When Eu seleciono a opção “Último mês”
    Then Eu posso ver um gráfico Coletas x Data que possui apenas as alturas 5 quando x = “26/10/2016” e 6 quando x = “15/11/2016”

#controle
  Scenario: Solicitação de gráficos de coletas
    Given Eu estou na tela de opções de gráficos
    When Eu seleciono a opção “Último mês”
    Then A lista de coletas realizadas não muda