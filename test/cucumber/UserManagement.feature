Feature: Gerenciar usuários
  As um usuário
  I want to adicionar, remover e alterar as minhas informações
  So that eu posso me tornar um usuário do sistema como empresa coletora ou gerador de resíduo

#GUI
  Scenario: Realizar cadastro no sistema.
    Given Estou na pagina de cadastro do ResS
    And o usuário com o cnpj "36.682.672/0001-71" ou com o usuário "ruteste" não estão cadastrados
    When eu informo o nome "Restaurante Universitário" com o seu cnpj "36.682.672/0001-71", o seu endereço "Rua dois" "22" "Dentro do campus" "Cidade Universitária" "Recife" "Pernambuco" "52232-123", o tipo de usuário como "Gerador de Resíduo", e-mail "ruteste@gmail.com", usuário "ruteste" e senha "pass"
    And tento cadastrar esse usuário
    Then eu posso ver a tela de login

  Scenario: Cadastro de mesmo cnpj para diferentes tipos de usuário
    Given o sistema tem armazenado um usuário do tipo "Empresa Coletora" com o cnpj "73.572.673/0001-90"
    And estou na página de cadastro do ResS
    When eu tento cadastrar um usuário do tipo "Gerador de Resíduo" com o cnpj "73.572.673/0001-90" e login "teste"
    Then eu vejo a mesma página de cadastro do ResS
    And eu posso ver uma mensagem avisando que existe uma empresa coletora com o cnpj "73.572.673/0001-90"

    @ignore
  Scenario: Alterar informações de usuário e informar um cnpj já cadastrado
    Given estou logado no sistema como o usuário de tipo "Empresa Coletora" com cnpj "73.572.673/0001-90"
    And estou na tela de alterar informações
    And o usuário com cnpj "72.517.376/0001-89" já está cadastrado
    When eu altero o cnpj "73.572.673/0001-90" para "72.517.376/0001-89"
    Then eu posso ver os mesmos dados que eu via antes da alteração do usuário com cnpj "73.572.673/0001-90"

#Controle
  Scenario: Remover um usuário empresa coletora com solicitações de coleta confirmadas
    Given a empresa coletora cadastrada com o cnpj "73.572.673/0001-90" tem uma única solicitação de coleta do gerador de resíduo de cnpj "72.517.376/0001-89" confirmada.
    When o cadastro da empresa coletora com o cnpj "73.572.673/0001-90" é deletado do sistema
    Then a solicitação de coleta do gerador de resíduo de cnpj "72.517.376/0001-89" que fora confirmada também é deletada do sistema.