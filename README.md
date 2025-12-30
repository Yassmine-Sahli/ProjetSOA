# 🚀 Gestion des Personnes - REST & React

Une application web complète permettant de gérer une liste de personnes (CRUD : Créer, Lire, Mettre à jour, Supprimer). Ce projet démontre l'intégration entre une API RESTful classique en Java et un frontend moderne en React.

## 📋 Description

L'application permet aux utilisateurs de :
- Visualiser la liste des personnes enregistrées.
- Rechercher une personne par nom ou ID en temps réel.
- Ajouter une nouvelle personne.
- Modifier les informations d'une personne existante.
- Supprimer une personne.

Le design reprend l'esthétique originale de l'application mais implémentée avec des composants React réactifs.

## 🛠️ Technologies Utilisées

### Backend (API REST)
- **Java 8**
- **Jersey 1.19** (Implémentation JAX-RS)
- **Maven** (Gestion de projet et dépendances)
- **Tomcat 7** (Serveur d'application via plugin Maven)
- **MySQL** (Base de données)
- **JDBC** (Connexion BDD)

### Frontend (Client Web)
- **React 18**
- **Vite** (Build tool rapide)
- **CSS3** (Styles personnalisés, animations)
- **Fetch API** (Communication avec le backend)

## ⚙️ Instructions d'Installation et d'Exécution

### 1. Prérequis
- Java JDK 8 ou supérieur
- Maven 3.x
- Node.js et npm
- Serveur MySQL

### 2. Base de Données
Exécutez le script SQL fourni (`database.sql`) à la racine du projet pour créer la base de données et la table nécessaire :
```sql
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
-- Crée la table persons...
```

### 3. Lancer le Backend
Ouvrez un terminal à la racine du projet :
```bash
mvn tomcat7:run
```
Le serveur démarrera sur **http://localhost:8090/REST**.

### 4. Lancer le Frontend
Ouvrez un **nouveau** terminal dans le dossier `frontend` :
```bash
cd frontend
npm install  # Seulement la première fois
npm run dev
```
Ouvrez votre navigateur sur l'URL indiquée (ex: **http://localhost:5173**).

## 🎥 Démonstration

[![Voir la vidéo de démonstration](https://img.youtube.com/vi/PLACEHOLDER_VIDEO_ID/0.jpg)](https://www.youtube.com/watch?v=PLACEHOLDER_VIDEO_ID)

*(Lien vers la vidéo de démonstration à insérer ici)*

## 📸 Captures d'écran

*(Optionnel : Insérez ici des images de l'interface)*
"# ProjetSOA" 
