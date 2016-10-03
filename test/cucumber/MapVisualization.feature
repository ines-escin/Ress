Feature: Map Visualization
  As uma empresa
  I want to visualizar os restaurantes com coletas pendentes
  So that eu posso saber onde ficam os pontos de coleta

  #GUI

  Scenario: Visualizar locais
    Given Estou na página de visualização de mapa do ResS
    And restaurantes “r1” e “r2” possuem coletas pendentes
    When eu solicito a visualização das coletas
    Then eu vejo a localização dos restaurantes  “r1” e “r2” no mapa da UFPE

  Scenario: Sem coletas pendentes
    Given Estou na página de visualização de mapa do ResS
    And não existem coletas pendentes
    When eu solicito a visualização das coletas
    Then eu vejo uma mensagem sinalizando que não há restaurantes com coletas pendentes


 #Controller

  Scenario: Buscar endereços de coleta
    Given existem coletas pendentes
    When eu solicito os locais de coletas pendentes
    Then o sistema retorna os endereços de coletas pendentes


  Scenario: Buscar endereços de coleta
    Given não existem coletas pendentes
    When eu solicito os locais de coletas pendentes
    Then o sistema nada retorna



