# Jeu du Sudoku

Ce Sudoku été développé par Amir Kabbouri et Athiran Nagathurai dans le cadre de la SAE lors de notre première année (2023) de BUT Informatique à l'IUT de Fontainebleau.

## Lancement du programme

### Compilation

```bash
make
```

### Lancement du programme pour résoudre la grille

Utiliser la commande suivante pour compiler (si ce n'est pas déjà) fait puis lancer le sprogramme :

```bash
make run1
```



### Lancement du programme pour Crée une grille

Utiliser la commande suivante pour compiler (si ce n'est pas déjà) fait puis lancer le sprogramme :

```bash
make run2
```

## Fonctionnalités

### Fonctionnalités exigées

- Le premier programme servira à l'élaboration des grilles de départ.
  
  Pour construire une grille, on pourra partir d'une grille vide ou charger une grille
  existante depuis un fichier. Il devient alors possible d'ajouter ou d'enlever des numéros
  dans la grille (le programme doit empêcher les placements contradictoires). Une fois la
  grille achevée, elle sera sauvegardée dans un nouveau ou un ancien fichier. Le format
  employé pour ces fichiers est décrit [plus bas](https://iut-fbleau.fr/sitebp/pt21/21_2023/A75DYGZ82RZL3PGH.php#format). La sélection
  d'un fichier en lecture ou en écriture pourra se faire par le biais de la classe [JFileChooser](https://iut-fbleau.fr/docs/java/api/java.desktop/javax/swing/JFileChooser.html).

- Le second programme servira à résoudre une grille.
  
  On commencera par charger une grille depuis un fichier. Puis on choisira si on souhaite
  résoudre la grille manuellement ou automatiquement. En mode automatique, le programme
  affichera la grille résolue et le temps nécessaire à la résolution (on mesurera naïvement
  le temps écoulé à l'aide de la méthode nanoTime de la classe [System](https://iut-fbleau.fr/docs/java/api/java.base/java/lang/System.html)).
  
  En mode manuel, le joueur pourra ajouter des chiffres (mais pas si cela contrevient
  directement aux contraintes d'unicité) et enlever des chiffres (mais pas s'ils
  apparaissent dans la grille de départ). En cas de doute, il pourra aussi faire
  temporairement cohabiter jusqu'à quatre chiffres dans une même case (dans ce cas, les
  chiffres qui cohabitent sont limités par les autres cases, mais ne les contraignent pas
  en retour). Le joueur sera félicité par le programme lorsque toutes les cases
  contiendront un chiffre (et un seul).
  
  Mettez autant de code que possible en commun entre les deux programmes : de
  nombreuses classes devraient être employées par l'un comme par l'autre.
  
  

### Bibliothèque graphique de l'IUT de Fontainebleau

- Documentation : [API Officielle]([Overview (Java SE 21 &amp; JDK 21)](https://iut-fbleau.fr/docs/java/api/index.html))
- Auteur : Oracle