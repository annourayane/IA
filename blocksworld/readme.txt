projet réalisé par : 
ANNOU Rayane    GROUPE : 2A
Emam Mohamed Elmamy    GROUPE 2A 

Nous avons terminé les deux TP : le premier sur la planification, dont les tests ont réussi, et le second sur la représentation des variables et des contraintes, qui a également passé tous ses tests.
et nous avons aussi  terminé les deux derniers Tp : Problemes de satisfaction de contraintes et extraction de connaissances dont tous les tests ont réussi.

Merci 

******Compilation et execution ***********
d'abord on ouvre le terminal dans src 
///// pour le package modelling : 
pour compiler le main : 
javac  -d ../build modelling/Main.java
pour executer le main : 
java  -cp ../build modelling.Main 

pour compiler le test : jar
javac -d ../build -cp ../lib/modellingtests.jar modelling/Test.java
pour executer le test : 
java -cp ../build:../lib/modellingtests.jar modelling.Test 

//////// pour le package planning : 
pour compiler le main : 
javac  -d ../build planning/Main.java
pour executer le main :
java  -cp ../build planning.Main  

 pour compiler le test : 
javac -d ../build -cp ../lib/planningtests.jar planning/Test.java
 pour executer le test : 
java -cp ../build:../lib/planningtests.jar planning.Test


/////////////////////////////////pour le package cp 
faut d'abord compiler les classes de packages cp 
pour compiler le test :  
javac -d ../build -cp ../lib/cptests.jar cp/Test.java
pour executer le test : 
java -cp ../build:../lib/cptests.jar cp.Test

//////////////////////////////// pour le package  datamining 
faut d'abord compiler les classes de packages datamining 
pour compiler le test : 
javac -d ../build -cp ../lib/dataminingtests.jar datamining/Test.java
pour executer le test : 
java -cp ../build:../lib/dataminingtests.jar datamining.Test
//////////////////////////fil rouge 
pour compiler tous les classes de la partie 4 : 
javac -d ../build -cp .:../lib/bwgenerator.jar:../lib/blocksworld.jar ./blocksworld/*.java
pour executer App (le main de la partie 1): 
java -cp ../build blocksworld.App
pour executer la App2 (le main de la partie 2): 
java -cp ../build/.:../lib/blocksworld.jar blocksworld.App2
pour executer App3 (le main de la partie 3) : 
java -cp ../build/.:../lib/blocksworld.jar blocksworld.App3
pour executer le main de la partie 4 (le main de la partie 4) : 
java -cp ../build/.:../lib/bwgenerator.jar blocksworld.App4

