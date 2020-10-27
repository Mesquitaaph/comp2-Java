# Questionário
<p>
1 - Acredito que minha maior dificuldade foi aplicar os padrões de projeto. Já apliquei o <b>MVC</b> ou semelhante em outros projetos, mas mesmo assim não foi algo tão intuitivo quanto pensei que seria. No entanto, depois que finalizei a parte <i>model</i> fluiu melhor.
</p>
<p>Ainda nos padrões de projeto, foi difícil achar um segundo padrão que fosse realmente servir para algo no programa. Acabei utilizando o padrão comportamental <b>Observer</b> na parte de emprestar livros à estudantes na fila de empréstimos.</p>
<div>
  <p>
      Outra dificuldade foi para tornar o programa escalável, mais especificamente com relação ao formato do dataset. Um <i>.tsv</i> não permite modificações tão facilmente. Para alterar um registro no dataset era preciso ler e escrever o arquivo inteiro. Mas para uma quantidade de registros que pode facilmente ficar muito grande, como o dataset de empréstimos, pode pesar muito na memória do computador. Pensei em algumas soluções, apesar de não aplicá-las:
  </p>
  <ul>
    <li>
      Criar um arquivo temporário e concatenar, nele, as linhas lidas do dataset a fim lê-lo e escrever no arquivo original, modificando o necessário;
    </li>
    <li>
      Utilizar um banco de dados apropriado. Tive um tempo antes do PLE para dar uma estudada nisso e concluí que um banco de dados relacional seria o ideal para esse programa. (Não cheguei a pesquisar se o <b>JAVA</b> tem uma biblioteca para realizar conexões com bancos de dados);
    </li>
  </ul>
</div>

2 - Diagrama de classes.

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista4/Diagrama de classe.png"/>
<br>

3 - Diagrama de sequência para o caso da tentativa de empréstimo de um livro cujos exemplares estão esgotados.</p>

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista4/Diagram de sequencia.png"/>
<br>

4 - Como não tive muitas informações sobre como os pontos foram atribuídos inicialmente e não pude confirmar se os que já tinham eram um sistema de <i>rating</i>, pensei em utilizar os pontos dos estudantes como uma forma de dar preferência na fila de empréstimos. Mas não acho que seria justo com os estudantes que acabaram de se cadastrar no sistema da biblioteca. Então pensei numa solução simples que abrange estudantes e livros:
<ul>
  <li>
    Estudantes com mais de 850 pontos podem pegar 3 livros sem precisar devolver outro antes, diferente da quantidade padrão(2).<br>
  </li>
  <li>
    Livros com mais de 750 pontos ganham mais um exemplar na biblioteca. Ficando, então, um total de 3 exemplares do livro.<br>
  </li>
  <li>
    Cada vez que há uma devolução, 10 pontos são adicionados ao estudante e ao livro envolvidos.
  </li>
</ul>
<p>
5 - Utilizei o padrão de projeto <b>MVC</b> e o padrão comportamental <b>Observer</b>:
</p>
<p>
  Com o <b>MVC</b>, usei como <i>Model</i> as classes que representam os registros de cada dataset. Ex.: no "students.tsv" os registros possuem o id, nome, sobrenome etc, do estudante. A classe <i>Student</i> faz parte do <i>Model</i> e é responsável por informar ao programa como ler e guardar os dados no dataset.<br>
  Usei como <i>Controller</i> as classes responsáveis por manipular o dataset e relacionar seus dados, com auxílio das <i>models</i>. Tais dados podem ser de um mesmo registro, dataset ou de diferentes registros e dataset's. Ex.: a classe <i>StudentsController</i> registra estudantes, modificando o "students.tsv e verifica os estudantes que mais pegaram livros emprestados, relacionando os dataset's de estudantes e empréstimos.<br>
  Já na parte de <i>View</i>, criei classes que interagem com o usuário, recebendo algum comando e decidindo, dentre as funções contidas nelas, o que mostrar pro usuário ou receber dele. Ex.: a classe <i>StudentsView</i> é responsável por pedir os dados de cadastro de um estudante, recebê-los e enviá-los para o <i>Controller</i> registrá-lo. Também é responsável por receber um N inteiro do usuário, pedir ao <i>Controller</i> e mostrar os N estudantes que mais pegaram livros emprestados.
</p>
<p>
  O <b>Observer</b> foi utilizado para notificar um estudante que está na fila de empréstimos para pegar o livro emprestado.<br>
  Quando um livro é devolvido é chamado o método <i>update()</i> das classes que implementam o <b>Observer</b>. No caso desse programa apenas uma classe implementa o <b>Observer</b>, mas pensando num sistema mais elaborado, seria possível chamar diferentes métodos para notificar o estudante, ex.: notificar por e-mail, sms, WhatsApp, etc.
</p>
