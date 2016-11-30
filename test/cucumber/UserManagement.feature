Feature: Gerenciar usuários
  As um usuário
  I want to adicionar, remover e alterar as minhas informações
  So that eu posso me tornar um usuário do sistema como empresa coletora ou gerador de resíduo

#GUI

  Scenario: Realizar cadastro no sistema.
    Given Estou na pagina de cadastro do ResS
    And o usuário com o cnpj "36.682.672/0001-71" ou com o usuário "geradorteste" não estão cadastrados
    When eu informo o nome "Restaurante Universitário" com o seu cnpj "36.682.672/0001-71", o seu endereço "Rua dois" "22" "Dentro do campus" "Cidade Universitária" "Recife" "Pernambuco" "52232-123", o tipo de usuário como "Gerador de Resíduo", e-mail "ruteste@gmail.com", usuário "geradorteste" e senha "pass" e eu tento cadastrar esse usuário
    Then eu posso ver a tela de login e uma mensagem de confirmação

  Scenario: Cadastro de mesmo cnpj para diferentes tipos de usuário
    Given o sistema tem armazenado um usuário do tipo "Empresa Coletora" com o cnpj "81.623.418/0001-57"
    And estou na página de cadastro do ResS
    When eu tento cadastrar um usuário do tipo "Gerador de Resíduo" com o cnpj "81.623.418/0001-57" e login "loginteste"
    Then eu vejo a mesma página de cadastro do ResS
    And eu posso ver uma mensagem avisando que existe uma empresa coletora com o cnpj "81.623.418/0001-57"

  Scenario: Alterar informações de usuário e informar um cnpj já cadastrado
    Given estou logado no sistema como o usuário de tipo "Empresa Coletora" com cnpj "25.296.876/0001-58"
    And o usuário com cnpj "11.022.440/0001-66" já está cadastrado
    And estou na tela de alterar informações
    When eu altero o cnpj "25.296.876/0001-58" para "11.022.440/0001-66"
    Then eu posso ver uma mensagem de confirmação da alteração

#Controle
  Scenario: Remover um usuário empresa coletora com solicitações de coleta confirmadas
    Given a empresa coletora cadastrada com o cnpj "17.337.645/0001-17" tem uma única solicitação de coleta do gerador de resíduo de cnpj "41.367.569/0001-77" confirmada.
    When o cadastro da empresa coletora com o cnpj "17.337.645/0001-17" é deletado do sistema
    Then a solicitação de coleta do gerador de resíduo de cnpj "41.367.569/0001-77" que fora confirmada também é deletada do sistema.