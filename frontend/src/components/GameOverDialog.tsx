import React from 'react';
import { Dialog, Flex, Text, Button } from '@radix-ui/themes';
import { Game } from '../types/game';

interface GameOverDialogProps {
  game: Game;
  onNextLevel: () => void;
  onRetry: () => void;
}

export const GameOverDialog: React.FC<GameOverDialogProps> = ({ game, onNextLevel, onRetry }) => {
  if (game.status === 'IN_PROGRESS') return null;

  const isLevelComplete = game.status === 'LEVEL_COMPLETE';
  const isFinalLevel = game.level === 'HARD';

  return (
    <Dialog.Root open>
      <Dialog.Content>
        <Dialog.Title>
          {isLevelComplete ? 'Congratulations!' : 'Game Over'}
        </Dialog.Title>
        <Flex direction="column" gap="3">
          <Text size="2">
            {isLevelComplete
              ? `You completed level ${game.level} with ${game.powerPoints} power points!`
              : 'You ran out of power points!'}
          </Text>
          <Text size="2">Final Score: {game.score}</Text>
          <Flex gap="3" justify="center">
            {isLevelComplete && !isFinalLevel && (
              <Button onClick={onNextLevel}>Next Level</Button>
            )}
            <Button onClick={onRetry} color={game.status === 'LOST' ? 'red' : undefined}>
              {game.status === 'WON' && isFinalLevel ? 'Play Again' : 'Try Again'}
            </Button>
          </Flex>
        </Flex>
      </Dialog.Content>
    </Dialog.Root>
  );
};