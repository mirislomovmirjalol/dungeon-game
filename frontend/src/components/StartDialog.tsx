import React from 'react';
import { Dialog, Flex, Text, Button, TextField, Select } from '@radix-ui/themes';
import { GameLevel } from '../types/game';

interface StartDialogProps {
  open: boolean;
  playerName: string;
  gameLevel: GameLevel;
  onChangeName: (name: string) => void;
  onChangeLevel: (level: GameLevel) => void;
  onStart: () => void;
}

export const StartDialog: React.FC<StartDialogProps> = ({
  open,
  playerName,
  gameLevel,
  onChangeName,
  onChangeLevel,
  onStart
}) => {
  return (
    <Dialog.Root open={open}>
      <Dialog.Content>
        <Dialog.Title>Welcome to Dungeon Game</Dialog.Title>
        <Flex direction="column" gap="2">
          <Text as="div" size="2" weight="bold">Player Name</Text>
          <TextField.Root
            placeholder="Enter your name"
            value={playerName}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => onChangeName(e.target.value)}
          />

          <Text as="div" size="2" weight="bold">Game Level</Text>
          <Select.Root value={gameLevel} onValueChange={(value) => onChangeLevel(value as GameLevel)}>
            <Select.Trigger />
            <Select.Content>
              <Select.Item value="EASY">Easy (3x3)</Select.Item>
              <Select.Item value="MEDIUM">Medium (5x5)</Select.Item>
              <Select.Item value="HARD">Hard (10x10)</Select.Item>
            </Select.Content>
          </Select.Root>

          <Button onClick={onStart} mt="4">Start Game</Button>
        </Flex>
      </Dialog.Content>
    </Dialog.Root>
  );
};