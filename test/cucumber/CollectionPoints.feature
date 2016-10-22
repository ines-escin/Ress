Feature: Map Visualization
  As uma empresa
  I want to visualizar os restaurantes com coletas pendentes
  So that eu posso saber onde ficam os pontos de coleta

  #GUI

#  Scenario: Visualizar locais
#    Given Estou na página de visualização de mapa do ResS
#    And os restaurantes de login "r1" e "r2" possuem coletas pendentes
#    When eu solicito a visualização das coletas
#    Then eu vejo a localização dos restaurantes de login "r1" e "r2" no mapa da UFPE
#
#  Scenario: Sem coletas pendentes
#    Given Estou na página de visualização de mapa do ResS
#    And não existem locais com coletas pendentes
#    When eu solicito a visualização das coletas
#    Then eu vejo uma mensagem sinalizando que não há restaurantes com coletas pendentes


 #Controller

  Scenario: Buscar endereços de coleta pendentes
    Given o local de login "ru1" possui coletas pendentes
    When eu solicito os enderecos dos locais com coletas pendentes
    Then o sistema retorna os enderecos do locais de login "ru1"


  Scenario: busca de enderecos nao havendo coletas pendentes
    Given não existem coletas pendentes
    When  eu solicito os enderecos dos locais com coletas pendentes
    Then o sistema nada retorna



