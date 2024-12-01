# Dungeon Game

A single-player dungeon adventure game combining exploration with programming challenges.

## Features

- Multiple difficulty levels with varying map sizes
- Programming challenges with OOP concepts and design patterns
- Dynamic map exploration
- Power points system
- Inventory system for storing hints and answers
- Leaderboard
- Auto-save functionality

## Tech Stack

- Backend: Spring Boot
- Frontend: React with Radix UI
- Database: MongoDB (for questions and game state)
- Docker for containerization

## Getting Started

### Prerequisites

- Docker and Docker Compose

### Running the Application

1. Clone the repository
2. Run the application:
   ```bash
   docker-compose up --build
   ```
3. Access the application:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080

## Game Rules

- Initial power points: 100
- Movement costs 1 power point
- Correct answers give +10 power points
- Wrong answers reduce 20 power points
- Map sizes:
  - Easy: 10x10
  - Medium: 15x15
  - Hard: 20x20 