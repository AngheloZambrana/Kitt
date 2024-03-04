# Kitt
Implementación del código:
    El código proporcionado resuelve el problema de comparar dos textos para encontrar similitudes y palabras mal escritas. Aplica programación dinámica para calcular el porcentaje de similitud entre los textos y para identificar palabras mal escritas.
    Se implementan tres funciones principales: readTextFromFile, calculateSimilarityPercentage e identifyMisspelledWords, cada una cumpliendo una parte específica del problema.
    El código recibe dos archivos de texto como entrada y produce el porcentaje de similitud entre los textos junto con las palabras mal escritas en el segundo texto.

Explicación de la elección del algoritmo de programación dinámica:
    El algoritmo de programación dinámica se utiliza para calcular el porcentaje de similitud entre los textos, lo cual es una aplicación típica del algoritmo de Longest Common Subsequence (LCS). Este enfoque permite optimizar la solución del problema descomponiéndolo en subproblemas más pequeños y evitando la recalculación de soluciones redundantes.

Complejidad temporal de la solución:
    La complejidad temporal de la solución es O(n×m)O(n×m), donde nn y mm son las longitudes de los dos textos, respectivamente. Esto se debe a que la función calculateSimilarityPercentage utiliza una matriz de tamaño (n+1)×(m+1)(n+1)×(m+1) y realiza un bucle anidado para llenarla.

Alternativa sin programación dinámica:
    Una alternativa sin programación dinámica podría ser un enfoque ingenuo que compare cada palabra del segundo texto con todas las palabras del primer texto. Sin embargo, este enfoque sería menos eficiente y más propenso a errores. No aprovecharía las subestructuras óptimas del problema y requeriría repetir cálculos innecesarios. Comparado con la solución basada en programación dinámica, sería menos eficiente.


PROBLEMAS:

Tuve problemas con la lectura de archivos no se que pasaba que no reconocia la ruta intente de varias maneras y no logre que
se leyeran de manera correcta los archivos me parecio muy raro

Realice una clases de testeo para poder realizar el seguimiento a los proces que realice pero no se si estaran correctos
No pude realizar ninguna prueba muy seria por culpa del primer error, mil disculpas
