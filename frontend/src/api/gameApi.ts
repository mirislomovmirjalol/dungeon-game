import { Game, GameLevel, Direction } from '../types/game';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const handleResponse = async (response: Response) => {
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message || 'API request failed');
  }
  return response.json();
};

export const gameApi = {
  async startGame(playerName: string, level: GameLevel): Promise<Game> {
    const response = await fetch(`${API_URL}/games`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ playerName, level }),
    });
    return handleResponse(response);
  },

  async getGame(gameId: string): Promise<Game> {
    const response = await fetch(`${API_URL}/games/${gameId}`);
    return handleResponse(response);
  },

  async move(gameId: string, direction: Direction): Promise<Game> {
    const response = await fetch(`${API_URL}/games/${gameId}/move`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ direction }),
    });
    return handleResponse(response);
  },

  async answerQuestion(gameId: string, questionId: string, answer: string): Promise<Game> {
    const response = await fetch(`${API_URL}/games/${gameId}/answer`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ questionId, answer }),
    });
    return handleResponse(response);
  },

  async progressToNextLevel(gameId: string): Promise<Game> {
    const response = await fetch(`${API_URL}/games/${gameId}/next-level`, {
      method: 'POST',
    });
    return handleResponse(response);
  }
}; 