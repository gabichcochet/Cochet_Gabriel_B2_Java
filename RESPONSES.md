# RESPONSES

1) Pourquoi le mot-clé volatile est-il indispensable sur l'instance dans le Double Checked Locking ?

`volatile` garantit que toutes les écritures du constructeur sont visibles avant que l'instance soit publiée. Sans ce mot-clé, un thread pourrait voir une référence partiellement initialisée à l'objet singleton.

2) Donne une alternative plus simple et tout aussi thread-safe en Java. Pourquoi est-elle préférable ?

On peut utiliser une initialisation statique immédiate (`private static final GameServer INSTANCE = new GameServer();`) ou un `enum` singleton. C’est préférable car ce mécanisme est plus simple, fiable et ne demande pas de synchronisation explicite ni de double-check.

3) Quelle est la différence entre le pattern State et un simple if/else sur un attribut enum ? Donne un avantage concret du State dans ce contexte.

Avec le pattern State, chaque état encapsule son propre comportement dans une classe dédiée, tandis qu’un `if/else` centralise toute la logique dans un seul endroit. Concrètement, cela facilite l’ajout d’un nouvel état sans modifier un grand bloc conditionnel et améliore la lisibilité du code.

4) Pourquoi faut-il appeler `field.setAccessible(true)` avant `field.get(objet)` pour les champs privés ?

`field.setAccessible(true)` autorise l’accès à un champ privé via réflexion en levant la barrière d’accessibilité. Sans cet appel, `field.get(objet)` échouerait sur les champs non publics.

5) Cite un risque de sécurité lié à l'utilisation de `setAccessible()` en production.

En production, `setAccessible()` peut casser l’encapsulation et permettre à du code d’accéder ou de modifier des champs privés, ce qui peut exposer des informations sensibles ou altérer des invariants.

6) Le RestEngine lit les annotations `@Rest`, `@Get`, `@Post` via réflexion pour construire la table de routage. Explique en 3 lignes comment fonctionne ce mécanisme.

Le RestEngine parcourt les classes pour détecter celles marquées `@Rest`, puis inspecte leurs méthodes annotées `@Get` et `@Post`. Il extrait le chemin de la route et les paramètres associés à partir des annotations, puis il construit une table de correspondance URL/méthode. Lorsqu’une requête arrive, il utilise cette table pour invoquer la méthode appropriée par réflexion.
