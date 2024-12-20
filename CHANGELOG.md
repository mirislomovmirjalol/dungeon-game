# Changelog

All notable changes to this project will be documented in this file.

## [unreleased]

### 🚀 Features

- Initialize spring boot and docker
- Frontend setup
- Add core domain models for game entities
- Core game logic with repository and service layer
- Implement core of game handlers
- Default error messages on backend
- Global error handling
- Not found exception, handle null states on game service
- Implement factory pattern to create item
- Implement observer pattern to listen game events
- Add positioning in the game
- Setup global cors config
- Implement mongo initializer to seed questions into db
- Handling next level on backend
- Handle game level as enum intead string
- Implement factory pattern to create items
- Add current status of the game, add default values to game (map grid, move cost, min questions per level)
- Implement inventory model
- Update score on event listener
- Radix custom theming, react query provider
- API requests layer on frontend
- Current question modal window
- Game grid with movement controllers
- Game info bar
- Game over model
- Game inventory
- Game start dialog
- Implement game state hook which communicates api layer
- Leaderboard modal implement
- Add message to game API response
- Handle messages from API on frontend
- Add hints to questions
- Unit tests for gameservice
- Implement game controller tests
- Implement cell model tests
- Implement gameMap model tests
- Implement game model tests
- Swagger gobal config

### 🐛 Bug Fixes

- Check correct asnwered questions not asnwered questions on canWin function
- Return friend item on item factory
- Set message to null when new movement happens

### 📚 Documentation

- Add javadocs, swagger api info on game controller
- Add javadocs, swagger api info on leaderboard controller
- Add javadocs, swagger api info on question controller
- Add javadocs on game service interface
- Add javadocs with small explanations to core classes
- Update readme file, add manual instalation instruction, add UI screenshot showcasae
- Add author section

### ⚙️ Miscellaneous Tasks

- Packages setup and radix themes setup
- Full docker setup with docker compose
- Setup tailwind, setup configs
- Remove hardcoded cors config from leaderboard controller
- Remove unused import
- Remove cors config from question controller
- Ts types
- Add logger to mongo initializer
- Change hero icons to more compatable ones
- Change game boxes width and height to auto
- Swagger added to the dependencies

<!-- generated by git-cliff -->
