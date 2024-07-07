Solution eBank:
Solution eBank est une application bancaire en ligne complète qui permet aux utilisateurs de gérer leurs comptes bancaires, leurs cartes bancaires et leurs transferts d'argent de manière sécurisée et efficace. Ce projet est construit en utilisant Spring Boot et Hibernate, et inclut des fonctionnalités pour la création et la gestion de comptes bancaires, de cartes, de bénéficiaires et de transactions.

Fonctionnalités

Gestion des Comptes
Créer un Compte : Les utilisateurs peuvent créer un nouveau compte bancaire (par exemple, courant, épargne) avec un solde initial et une date de création.
Voir le Solde et les Transactions : Les utilisateurs peuvent consulter le solde et l'historique des transactions de leurs comptes.
Fermer un Compte : Les utilisateurs peuvent fermer leurs comptes bancaires et fournir une raison de fermeture.

Gestion des Cartes Bancaires
Voir les Informations de la Carte : Les utilisateurs peuvent voir les détails de leurs cartes bancaires (numéro, date d'expiration, type).
Activer/Désactiver la Carte : Les utilisateurs peuvent activer ou désactiver leurs cartes bancaires.
Bloquer la Carte : Les utilisateurs peuvent bloquer leurs cartes en cas de perte ou de vol, en fournissant une raison du blocage.

Transferts d'Argent
Transferts Internes : Les utilisateurs peuvent transférer de l'argent entre leurs propres comptes.
Transferts Externes : Les utilisateurs peuvent transférer de l'argent vers des comptes dans d'autres banques.
Gérer les Bénéficiaires : Les utilisateurs peuvent ajouter, modifier ou supprimer des bénéficiaires pour faciliter les transferts externes.
Structure du Projet



solution-ebank
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ebank
│   │   │       └── solution
│   │   │           ├── model
│   │   │           │   ├── Account.java
│   │   │           │   ├── Beneficiary.java
│   │   │           │   ├── Card.java
│   │   │           │   ├── Transaction.java
│   │   │           │   └── User.java
│   │   │           ├── repository
│   │   │           │   ├── AccountRepository.java
│   │   │           │   ├── BeneficiaryRepository.java
│   │   │           │   ├── CardRepository.java
│   │   │           │   ├── TransactionRepository.java
│   │   │           │   └── UserRepository.java
│   │   │           ├── service
│   │   │           │   ├── AccountService.java
│   │   │           │   ├── BeneficiaryService.java
│   │   │           │   ├── CardService.java
│   │   │           │   ├── TransactionService.java
│   │   │           │   └── UserService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── ebank
│               └── solution
│                   ├── service
│                   │   ├── AccountServiceTest.java
│                   │   ├── BeneficiaryServiceTest.java
│                   │   ├── CardServiceTest.java
│                   │   ├── TransactionServiceTest.java
│                   │   └── UserServiceTest.java
└── pom.xml


Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.
Spring Boot
Hibernate
Mockito
JUnit 

Contact
Pour toute question ou suggestion, veuillez contacter imanebahy91@gmail.com.
