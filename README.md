# HOWMANY_SUDOKU
this program count all possible sudoku grids.
This program is design to recheck the result of 2005, Bertram Felgenhauer and Frazer Jarvis found 6670903752021072936960.

This program found this number, the fastest version is:


ComputeAllSolution_HALF.java

The calculation was performed on a single CPU (i7-7820x). The program was run 15 times to perform the calculation in parallel. The whole thing lasted about 9 days.

This program builds the files that count the solutions of the sub-grids. (see Results folder).

The Validate_HALF program is used to add the partial results.

------------------------------------------------------------------------


ce programme a rev�rifi� le r�sultat de 2005, Bertram Felgenhauer et Frazer Jarvis ont trouv� 6670903752021072936960.

Ce programme rev�rifie ce nombre, la version la plus rapide  est:


ComputeAllSolution_HALF.java

Le calcul a �t� effectu� sur un seul CPU (i7-7820x). Le programme a �t� lanc� 15 fois pour effectuer le calcul en parall�le. L'ensemble a dur� environ 9 jours.

Ce programme � construit les fichiers qui comptent les solutions des sous-grilles. (voir folder Results).

Le programme Validate_HALF permet de sommer les r�sultats partielles.



fact9: 362880
perm2x3: 36
permB5B9: 2
sumdiag          : 1748364208
sumbig           : 7093166782680
nbsymetries      : 470292480
permB5B9         : 2
nbsudokuJG       : 6670903752021072936960
totsdkto be found: 6670903752021072936960
BUILD SUCCESSFUL (total time: 4 seconds)


