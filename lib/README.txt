-------Comment installer et utiliser EnsiScolarité ?---------

 1- Prérequis :

	* Détenir une Base de donnée préalablement installé sur la machine
	* Avoir pour cette base installé, un utilisateur avec le login root
	  et sans mot de passe
	* Dézippez le fichier EnsiScolarite.jar
	* Placez le fichier EnsiScolarite.jar dans un dossier que vous nommerez Application
 	* Démmarez les services MySQL

2- Installation :

    * Importer la Base de données
	1- Ouvrez une invite de commande dans le dossier application.
	2- Tapez la commande : jar xvf EnsiScolarite.jar ensiscolarite.sql
	3- Ouvrez PhpMyAdmin et créez une base de donnée vide
	   que vous nommerez ensiscolarite
	4- Importez la base de donnée a l'aide du fichier ensiscolarite.sql

    * Extraire la librairie
    	1- Extraire le dossier lib a l'aide de la commande : jar xvf EnsiScolarite.jar /lib/

3- Utilisation :
	1- Ouvrez une invite de commande dans le dossier Application
	2- Effectuez la commande suivante pour accéder à l'application :
	   java -jar EnsiScolarite.jar

4- Accéder à la documentation : 
	1- Ouvrez une invite de commande dans le fichier Application
	2- Tapez la commande : jar xvf EnsiScolarite.jar /doc/
	3- Ouvrez le dossier doc et ouvrez la page index.html dans un navigateur
	   internet.