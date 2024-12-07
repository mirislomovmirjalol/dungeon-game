import React from 'react';
import { Flex, Text } from '@radix-ui/themes';
import { Game } from '../types/game';

interface GameInfoBarProps {
  game: Game;
}

export const GameInfoBar: React.FC<GameInfoBarProps> = ({ game }) => {
  return (
    <Flex justify="between" align="center">
      <Text size="3">Power Points: {game.powerPoints}</Text>
      <Text size="3">Score: {game.score}</Text>
      <Text size="3">Level: {game.level}</Text>
    </Flex>
  );
};