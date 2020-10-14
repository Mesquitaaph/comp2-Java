# Questionário
<p>
1 - Tive uma dificuldade inicial de se ler, linha a linha, o caso.csv.gz. Tentei ler incialmente utilizando o <b>FileInputStream</b> sozinho e converter os bytes para caractere não estava funcionando muito bem: caracteres com acento eram convertidos incorretamente, já que os bytes obtidos representavam valores na tabela ASCII e acentos pertecem ao unicode; bytes zero faziam a leitura ficar completamente instável.<br>
Então achei a classe <b>BufferdReader</b> que já fazia isso tudo de forma muito mais rápida e simples.
</p>
<p>O resto das dificuldades foram problemas causados pelos dados não-perfeitos e questões de organização do código, como diminuir a quantidade de vezes que o dataset é lido.</p>

<p>Para os dados "ruins", verifiquei se o que era lido era suficiente pra ser utilizado, caso contrário calculava o que faltava ou nem utilizava caso os cálculos não fossem possíveis. Ex.: se faltasse o número de habitantes para o cálculo de casos/100k ou se o número de mortes fosse maior que o número de casos para o cálculo da mortalidade.</p>

2 - Diagrama de classes.

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista3/Diagrama de classe.png"/>
<br>

3 - Diagrama de sequência para o caso de construção da página web com o gráfico relativo ao estado do Rio de Janeiro.</p>

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista3/Diagrama de sequencia.png"/>
<br>

4 - Utilizei as classes <code>ArrayList<E></code>( em <code>ArrayList<List<String>></code> ) e <code>TreeMap<K,V></code> ( em <code>TreeMap<String, Integer></code> ). Utilizei a interface <code>List<E></code>( em <code>ArrayList<List<String>></code> ).
