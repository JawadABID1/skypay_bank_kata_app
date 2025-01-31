# SkyPay Bank Kata App

## Description

SkyPay Bank Kata App est une application de gestion bancaire simple, développée en suivant l'approche Test-Driven Development (TDD) et utilisant des pratiques Mockist. L'application permet de gérer des transactions bancaires, de les enregistrer et d'afficher un état des comptes.

## Fonctionnalités

- Dépôt d'argent sur le compte
- Retrait d'argent du compte
- Affichage de l'historique des transactions avec une méthode `printStatement`

## Architecture du projet

L'application suit une architecture simple avec les composants suivants :

- **AccountService** : Service qui gère les dépôts, retraits et l'affichage de l'état des comptes.
- **StatementPrinter** : Responsable de l'affichage des relevés de compte.
- **TransactionRepository** : Responsable de la gestion des transactions.
- **Transaction** : Modèle représentant une transaction (date et montant).

### Diagramme de classes

Voici une vue d'ensemble des principales classes impliquées dans ce projet :

- **Transaction**
  - `date` : Date de la transaction.
  - `amount` : Montant de la transaction.
  
- **AccountService**
  - Méthodes : `deposit(int amount)`, `withdraw(int amount)`, `printStatement()`.
  
- **StatementPrinter**
  - Méthodes : `print(List<Transaction> transactions)`.

- **TransactionRepository**
  - Méthodes : `addTransaction(Transaction transaction)`.

## Installation

1. Clonez ce dépôt sur votre machine locale :

   ```bash
   git clone https://github.com/ton-utilisateur/ton-depot.git
 
