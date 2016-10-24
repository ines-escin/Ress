Feature: Route visualization
    As uma empresa de coleta de residuos
    I want to visualizar uma rota para realizar a coleta
    So that eu possa saber o caminho para a coleta dos resíduos

  #GUI
    Scenario: aparecimento de rota no mapa
      Given eu estou na tela de visualização de pontos de coleta
      And existem coletas pendentes para a empresa "xyz" nos pontos "Restauramte Universitário" e "Cantina da Área II"
      When eu seleciono a opção de visualização de rotas
      Then o mapa mostra a rota "Avenida dos reitores > Avenida Jornalista Aníbal Fernandes", passando pelos pontos "Restaurante Universitário" e "Cantina da Área II"


    Scenario: ver rotas sem coletas pendentes
        Given eu estou na tela de visualização de pontos de coleta
        And não existem coletas pendentes
        When eu seleciono a opção de visualização de rotas
        Then o sistema exibe uma mensagem de erro

  #controle
    Scenario: solicitação de rotas
        Given existem coletas pendentes
        When eu solicito a rota para as coletas pendentes
        Then o sistema retorna a rota para as coletas pendentes

    Scenario: solicitação de rotas
        Given não existem coletas pendentes
        When eu solicito a rota para as coletas pendentes
        Then o sistema retorna um codigo de erro