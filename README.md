# Conta bancária

## ✍ Descrição
### Trabalho para o segundo módulo de java da residência serratec

## Contribuidores
:woman: <a href="https://github.com/Gabriela-Oliveira">Gabriela Oliveira</a></br>
:boy: <a href="https://github.com/ciceromngr">Cícero Romão </a></br>
:boy: <a href="https://github.com/BrGUILHERMEHenrique">Guilherme Henrique </a></br>
:boy: <a href="https://github.com/TonyMEsteves">Tony</a></br>
:boy: <a href="https://github.com/PauloDudu">Paulo Eduardo</a></br>

## Instruções:
O servidor roda na porta 9090, então obiavente o caminho a ser utilizado por padrão é: http://localhost:9090/conta;

<strong>GET</strong>: http://localhost:9090/conta (Para listar todas as contas);<br>
<strong>GET</strong>: http://localhost:9090/conta/numero (O numero é uma variável de caminho, e por tanto apenas por passa-lo no caminho já será reconhecido);<br>
<strong>POST</strong>: http://localhost:9090/conta (Deverá ser passado um body para criação de uma conta, o numero já é auto-criado internamente, então dever ser passado somente titular e saldo);<br>
Exemplo body: "titular": "Guilherme",<br>
              "saldo": 5000<br>
<strong>PUT</strong>: http://localhost:9090/conta/numero (O numero novamente uma variável de caminho, ele será passado como uma variável pois poderá ser mudado dentro do body);<br>
Exemplo body: "numero": 12,<br>
              "titular": "Nome Mudado",<br>
              Nota: as únicas informações que podem ser mudados são o numero e o titular, mesmo o saldo sendo passado não será mudado;<br>
<strong>DELETE</strong>: http://localhost:9090/conta/numero (caso o número seja achado ele será apagado e passada uma mensagem de sucesso no response-body);<br>
<strong>POST</strong>: http://localhost:9090/conta/numero/operacao?valor=seuValor (será passado um número como variável de caminho, a operacao será avaliada por um switch case deverá ser passado então o nome da mesma (debito/credito) e a operação de credito não pode ser feita com um valor menor que 50);<br>

### Todas as informações de erros serão passadas nos headers, indicando qual o erro que aconteceu.

### Números menores iguais ou menores que zero não serão aceitos, nos caminhos passados no caso.


![gif java-api](https://github.com/BrGUILHERMEHenrique/serratec-conta-bancaria-java2/blob/master/api-java.gif)

