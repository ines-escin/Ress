Feature: Route visualization
    As uma empresa de coleta de residuos
    I want to visualizar uma rota para realizar a coleta
    So that eu possa saber o caminho para a coleta dos resíduos

#  #GUI
#    Scenario: aparecimento de rota no mapa
#      Given existem coletas pendentes para o local "ru"
#      And eu estou na pagina de visualização de mapas do ResS
#      When eu seleciono a opção de visualização de rotas
#      Then eu vejo a rota passando por "ru" no mapa

   Scenario: ver rotas sem coletas pendentes
       Given eu estou na tela de visualização de mapas do ResS
       And não existem coletas pendentes
       When eu seleciono a opção de visualização de rotas
       Then o sistema exibe uma mensagem de erro

  #controle
    Scenario: solicitação de rotas
        Given o local "ru" possui coletas pendentes
        When eu solicito a rota passando pelos locais com coletas pendentes
        Then o sistema retorna a rota que passa por "ru"

    Scenario: solicitação de rotas sem coletas
        Given não ha coletas pendentes
        When eu solicito a rota entre os locais com coletas pendentes
        Then o sistema retorna um codigo de erro