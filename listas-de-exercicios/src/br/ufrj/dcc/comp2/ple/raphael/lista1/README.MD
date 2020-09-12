# Questionario

1 - Considerei o último programa o mais dificil de se fazer. Acho que optei pelo caminho mais dificil de se fazer isso, pois quis que o programa identificasse sozinho qual figura foi passada através das coordenadas passadas. Identificar os quadriláteros foi o mais difícil, pois não tinha jeitos fáceis de se fazer. Também achei um pouco complicado me organizar com tantas classes no programa.

2 - Na verdade usei os métodos scanner.nextInt() e scanner.nextDouble(). O primeiro para receber o tamanho da lista e o segundo para receber cada um dos numeros da lista. As exceções possíveis de ocorrer, especificamente neste programa, é somente a InputMismatchException.

3 - Assim que o código "pega" a exceção, se executa o metodo scanner.nextLine() para "limpar o buffer" e pede pro usuario digitar o numero novamente.

4 - O fim da entrada é o fim de linha. O programa recebe todas as palavras, separadas por espaço, até o fim de linha e as guarda para seguir a execução.

5 - Após guardar as palavras num ArrayLisy<String> utilizei o método Collections.sort() com a lista e o Comparator String.CASE_INSENSITIVE_ORDER. Assim ordenando em ordem alfabetica as palavras, independente da capitalização.

6 - Os vértices foram representados pela classe Ponto2d(ponto no plano cartesiano). Recebi as coordenadas pela entrada padrao e as dividi, em pares, a fim de obter os vertices.

7 - Tem um vertice representado por um ponto no plano cartesiano(Ponto2D) torna o calculo geometrico muito mais generalizado. Calcular distancias, paralelismo, perpendicularidade fica mais prático, pois existem ferramentas matematicas boas para isso.

8 - Recebi um "spoiler" seu, entao nao tive muitas dificuldades no calculo de area e perimetro. So demorei um pouco pra descobrir como usar a formula de Heron para a area do trapezio.
