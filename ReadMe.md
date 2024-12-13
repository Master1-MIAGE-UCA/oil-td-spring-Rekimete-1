# Projet "Dice" - Gestion de lancés de dés avec Spring Boot

## Description
Le projet "Dice" est une application construite avec Spring Boot permettant de simuler des lancés de dés et de gérer un historique des résultats en base de données. Ce projet met en œuvre les concepts fondamentaux de Spring Boot, notamment l'injection de dépendances, les services RESTful, les entités JPA et les repositories.

## Étapes de réalisation

### 1. Création du projet Spring Boot
- Utilisez [Spring Initializr](https://start.spring.io/) pour créer le projet.
- Choisissez la dernière version de Spring Boot disponible (LTS).
- Optez pour **Gradle** comme outil de gestion de dépendances.
- Ajoutez les dépendances nécessaires : **Spring Web**, **Spring Data JPA**, **H2 Database**.

J'ai rencontré des problèmes lors de la première initialisation, le build de gradle ne voulait pas se faire.
Je suis donc passé en ligne de commande et j'ai utilisé la commande suivante :
```shell
./gradlew clean --tracktrace
./gradlew build --stacktrace
```
De cette manière j'ai pu réussir a lancé le build. J'ai du relancé IntelliJ pour bien confirmer et le build refonctionnait correctement.

### 2. Configuration du projet
- Configurez l'application pour qu'elle utilise le port **8081**.
- Donnez un nom (**dice**) au projet dans le fichier de configuration :
  - Utilisez **`application.properties`** selon votre préférence.
J'ai ajouté une ligne dans le fichier `application.properties` pour définir le port de l'application.
```properties
server.port=8081
```

### 3. Création de la classe `Dice`
- Implémentez une classe représentant un dé avec les méthodes nécessaires pour effectuer un lancé.
- Marquez cette classe avec l'annotation `@Component` pour pouvoir l'injecter au besoin.

J'ai fait une classe simple qui possède un attribut value avec un getter, puis une meéthode roll 
qui permet d'obtenir une valeur aléatoire et d'utiliser le getter pour retourner la valeur

### 4. Création de l'entité `DiceRollLog`
- Modélisez une entité JPA `DiceRollLog` comprenant les champs suivants :
  - **`id`** : Identifiant unique.
  - **`diceCount`** : Nombre de dés lancés.
  - **`results`** : Liste ou chaîne des valeurs obtenues. Il existe de nombreuses façons de stocker des valeurs simples (simple String), certaines sont plus élégantes (@ElementCollection) que d'autres, vous pouvez choisir la solution qui vous conviendra.
  - **`timestamp`** : Horodatage du lancé.
- Utilisez des annotations JPA comme `@Entity`, `@Id`, `@GeneratedValue`, etc.

Rien de compliqué dans cette méthode, j'ai utilisé une liste d'Integer pour la variable results. Puis j'ai ajouté tous les getters ou setters.

### 5. Création du `Repository`
- Implémentez une interface héritant de `JpaRepository<DiceRollLog, Long>` pour gérer les interactions avec la base de données.
J'ai rien ajouté de spéciale dans cette classe.

### 6. Création du contrôleur REST pour lancer les dés
- Implémentez un contrôleur REST annoté avec `@RestController`.
- Ajoutez les endpoints suivants :
  - **`GET /rollDice`** : Lancer un seul dé.
  - **`GET /rollDices/{X}`** : Lancer X dés (X étant un paramètre dynamique).

J'ai utilisé le service dans les méthodes afin de lancer les dés et de les enregistrer dans la base de données. 
De cette mananière, le controleur ne gère pas directement de lien avec la base de donnée. C'est le rôle du service.

### 7. Création du `Service`
- Créez un service marqué avec `@Service` contenant une méthode :
  - Prend en paramètre le nombre de dés à lancer.
  - Retourne les résultats des lancés au contrôleur.
  - Enregistre l’historique des lancés dans la base via le `Repository`.

Puisque le répository n'est pas complexe, j'ai utilisé AutoWired pour l'injecter dans le service.
Après avoir fait l'historique des logs, j'ai ajouté une méthode pour pouvoir récupérer les lancés de dés.

### 8. Contrôleur pour afficher les historiques
- Ajoutez un autre contrôleur REST permettant d'afficher l'historique des lancés :
  - **`GET /diceLogs`** : Retourne tous les enregistrements de `DiceRollLog` au format JSON.
Pour ne pas avoir à utiliser un repository dans le controleur, j'ai utilisé le service pour récupérer les logs.

### 9. Tests et validation
- Démarrez l'application et testez les endpoints.
- Vérifiez les résultats dans la base de données et les réponses JSON.

### 10. (Bonus) Ajout de fonctionnalités avancées
- **Swagger** :
  - Ajoutez la dépendance Swagger/OpenAPI.
  - Configurez Swagger pour documenter vos endpoints.
  - Accédez à la documentation sur **`http://localhost:8081/swagger-ui.html`**.
- **Lombok** :
  - Utilisez Lombok pour simplifier les getters, setters et constructeurs de vos entités.

## Structure des fichiers

### `src/main/java/miage/m1/dice/Dice.java`
Classe représentant un dé avec une méthode pour effectuer un lancé. Annotée avec `@Component`.

### `src/main/java/miage/m1/dice/DiceRollLog.java`
Entité JPA modélisant un historique de lancé de dés avec les champs `id`, `diceCount`, `results` et `timestamp`.

### `src/main/java/miage/m1/dice/DiceRepository.java`
Interface héritant de `JpaRepository<DiceRollLog, Long>` pour gérer les interactions avec la base de données.

### `src/main/java/miage/m1/dice/DiceService.java`
Service contenant les méthodes pour lancer des dés, retourner les résultats et enregistrer l’historique dans la base de données.

### `src/main/java/miage/m1/dice/DiceController.java`
Contrôleur REST avec les endpoints pour lancer des dés et récupérer les résultats.

### `src/main/resources/application.properties`
Fichier de configuration de l'application, incluant le port et le nom du projet.

## Livrables
- Le code complet du projet, accessible via un dépôt GitHub.
- Un fichier `README.md` décrivant les étapes réalisées.

## Technologies
- **Framework principal** : Spring Boot
- **Base de données** : H2 
- **Documentation API** : Swagger (bonus)
- **Simplification de code** : Lombok (bonus)