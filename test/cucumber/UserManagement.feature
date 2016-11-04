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

#  Scenario: Cadastro de mesmo cnpj para diferentes tipos de usuário
#    Given o sistema tem armazenado uma empresa coletora com o cnpj "12.587.230/0001-88".
#    And estou na página de cadastro do ResS
#    When eu tento cadastrar um gerador de resíduo com o cnpj "12.587.230/0001-88".
#    Then eu vejo a mesma página de cadastro do ResS
#    And eu posso ver uma mensagem avisando que existe uma empresa coletora com o cnpj "12.587.230/0001-88".

#  Scenario: Alterar informações de usuário e informar um cnpj já cadastrado
#    Given estou logado no sistema como o usuário de tipo "Empresa Coletora" com cnpj "22.732.062/0001-20"
#    And estou na tela de alterar informações
#    And o usuário com cnpj "50.292.626/0001-97" já está cadastrado
#    When eu altero o cnpj "22.732.062/0001-20" para "50.292.626/0001-97"
#    Then eu posso ver uma mensagem informando que o cnpj "50.292.626/0001-97" já está cadastrado e a alteração não é armazenada.

#Controle
#  Scenario: Remover um usuário empresa coletora com solicitações de coleta não confirmadas
#    Given a empresa coletora cadastrada com o cnpj "12.587.230/0001-88" tem uma única solicitação de coleta do gerador de resíduo de cnpj "50.292.626/0001-97" não confirmada.
#    When o cadastro da empresa coletora com o cnpj "12.587.230/0001-88" é deletado do sistema
#    Then a solicitação de coleta do gerador de resíduo de cnpj "50.292.626/0001-97" que não fora confirmada, se torna pendente.