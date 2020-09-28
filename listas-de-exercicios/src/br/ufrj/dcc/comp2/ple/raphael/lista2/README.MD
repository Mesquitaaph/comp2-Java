# Questionário

1 - Não tenho muita certeza qual foi mais o difícil. Passei um bom tempo planejando antes de cada uma a forma que o código ficaria mais fluído e a parte da implementação acabava ficando só trabalhosa. No entanto, conforme duvidas minhas e de outros colegas foram aparecendo, tive problemas, na questão 1, de entender como implementar as interfaces no meu código sem que ficasse redundante. Sinceramente, ter que usar interfaces só atrapalhou e, por isso, eu não sei se não entendi muito bem como usar ou o jeito que fiz o programa tornou realmente inútil a utilização de interfaces.

2 - Diagrama da questão 1

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista2/diagrama1.png"/>

3 - Utilizei atributos de classe para definir alguns valores constantes e para receber os dados do usuário. Na classe principal, por conta da main() os dados são atribuidos a atributos estáticos da classe. Dava para fazer de outra maneira sem usar atributos estáticos, mas não vi muita diferença no resultado final. Além disso, ainda na classe principal, defini algumas constantes a serem utilizadas, que são duas listas: um dos tipos de caminhos possíveis e dos climas possíveis na viagem. Isso mantém um padrão, evita valores soltos pelo código e por conta de estar associado a um atributo "fácil" de se acessar, sempre saberemos o que está sendo usado e possibilita adicionar mais opções posteriormente de forma mais clara.
Outro uso dos atributos de classe foi nas interfaces, que assim como na classe principal, guardavam os tipos de caminho e climas que tal capacidade permite. (OffRoad viaja em rodovia e estrada de terra, por exemplo).

4 - Diagrama da questão 2

<img src="https://github.com/Riukkon/comp2-Java/blob/master/listas-de-exercicios/src/br/ufrj/dcc/comp2/ple/raphael/lista2/diagrama2.png"/>

5 - A abordagem numa forma geral foi simples. Primeiro inicializo uma matriz String[][] de tamanho 11. Em seguida o preenche com muros: primeiro os muros "borda", representados por "X" no mapa, que preenchem toda a extremidade da matriz, fazendo com que o tamanho efetivo da matriz seja 10; depois são posicionados muros de formatos e tamanhos aleatórios, através de um algoritmo que fiz(maiores explicações nas descrições dos métodos <i>+criaMuros()</i> e -criaMuro()), representados por "x" no mapa.
Após os muros, são posicionados os monstros e os itens de recuperação. Ao final o protagonista é posicionado.<br>
Cada monstro foi associado a um id e com essa informação busca-se em uma lista de monstros o índice correspondende ao id, permitindo que cada monstro seja único e tenha uma "memória" durante a partida, mesmo sendo um tabuleiro só com String.<br>
Já o protagonista não é efetivamente posicionado no tabuleiro. Ao longo do jogo, apenas se verifica onde ele estaria pisando se tivesse no tabuleiro, respeitando quaisquer obstáculo e interação.

6 - A dificuldade do jogo pode ser controlada alterando a quantidade de monstros, a proporção dos tipos de monstros(se todos os monstros fossem dragoes o jogo seria impossível...), a quantidade de vida e ataque de cada monstro e protagonista e a quantidade de itens de recuperação e quanto cada um recupera de vida. E para garantir que o jogador seja capaz de vencer, fixei valores para cada um desses atributos, sendo o ataque e vida do protagonista variando entre 10-20 e 90-80, respectivamente, e estudei como seria o jogo com alguns valores chave de ataque(10, 13, 15, 20) e modifiquei os atributos conforme a necessidade. O jogo é mais dificil quanto menos ataque o protagonista tiver, sendo 10ATK muito difícil e 20ATK muito fácil.
