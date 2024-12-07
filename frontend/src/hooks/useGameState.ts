import { useState } from 'react';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { gameApi } from '../api/gameApi';
import { Direction, GameLevel } from '../types/game';

export function useGameState() {
  const queryClient = useQueryClient();
  const [gameId, setGameId] = useState<string | null>(null);
  const [startDialogOpen, setStartDialogOpen] = useState(true);
  const [playerName, setPlayerName] = useState('');
  const [gameLevel, setGameLevel] = useState<GameLevel>('EASY');
  const [error, setError] = useState<string | null>(null);

  const { data: game } = useQuery({
    queryKey: ['game', gameId],
    queryFn: () => gameId ? gameApi.getGame(gameId) : null,
    enabled: !!gameId,
  });

  const startGameMutation = useMutation({
    mutationFn: (params: { playerName: string; level: GameLevel }) =>
      gameApi.startGame(params.playerName, params.level),
    onSuccess: (newGame) => {
      setGameId(newGame.id);
      queryClient.setQueryData(['game', newGame.id], newGame);
      setStartDialogOpen(false);
    },
    onError: () => setError('Failed to start game. Please try again.')
  });

  const moveMutation = useMutation({
    mutationFn: ({ gameId, direction }: { gameId: string; direction: Direction }) =>
      gameApi.move(gameId, direction),
    onSuccess: (updatedGame) => {
      queryClient.setQueryData(['game', updatedGame.id], updatedGame);
      if (updatedGame.status !== 'IN_PROGRESS' && updatedGame.status !== 'LEVEL_COMPLETE') {
        queryClient.invalidateQueries({ queryKey: ['game', updatedGame.id] });
      }
    },
    onError: () => setError('Failed to move. Please try again.')
  });

  const answerMutation = useMutation({
    mutationFn: ({ gameId, questionId, answer }: { gameId: string; questionId: string; answer: string }) =>
      gameApi.answerQuestion(gameId, questionId, answer),
    onSuccess: (updatedGame) => {
      queryClient.setQueryData(['game', updatedGame.id], updatedGame);
      if (updatedGame.status !== 'IN_PROGRESS') {
        queryClient.invalidateQueries({ queryKey: ['game', updatedGame.id] });
      }
    },
    onError: () => setError('Failed to answer question. Please try again.')
  });

  const handleStartGame = () => {
    if (!playerName.trim()) {
      setError('Please enter your name');
      return;
    }
    startGameMutation.mutate({ playerName, level: gameLevel });
  };

  const handleMove = (direction: Direction) => {
    if (!gameId) return;
    moveMutation.mutate({ gameId, direction });
  };

  const handleAnswer = (questionId: string, answer: string) => {
    if (!gameId) return;
    answerMutation.mutate({ gameId, questionId, answer });
  };

  const handleGameOver = () => {
    setGameId(null);
    setStartDialogOpen(true);
  };

  const handleNextLevel = () => {
    if (!game) return;
    const nextLevel = game.level === 'EASY' ? 'MEDIUM' : 'HARD';
    setGameLevel(nextLevel);
    startGameMutation.mutate({ playerName, level: nextLevel });
  };

  return {
    game,
    error,
    startDialogOpen,
    playerName,
    gameLevel,
    setPlayerName,
    setGameLevel,
    handleStartGame,
    handleMove,
    handleAnswer,
    handleGameOver,
    handleNextLevel,
  };
}