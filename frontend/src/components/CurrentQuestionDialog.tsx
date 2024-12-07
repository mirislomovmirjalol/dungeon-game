import React from 'react';
import { Dialog, Flex, Text, Card } from '@radix-ui/themes';
import { Game } from '../types/game';

interface CurrentQuestionDialogProps {
  game: Game;
  onAnswer: (questionId: string, answer: string) => void;
}

export const CurrentQuestionDialog: React.FC<CurrentQuestionDialogProps> = ({
  game,
  onAnswer
}) => {
  const question = game.currentQuestion;
  if (!question) return null;

  const randomizedOptions = question.options.sort(() => Math.random() - 0.5);

  return (
    <Dialog.Root open={!!question}>
      <Dialog.Content>
        <Dialog.Title>Answer the Question</Dialog.Title>
        <Flex direction="column" gap="3">
          <Text size="2" mb="2">{question.question}</Text>
          {randomizedOptions.map((option, index) => (
            <Card
              key={index}
              onClick={() => onAnswer(question.id, option)}
              variant="classic"
            >
              {option}
            </Card>
          ))}
        </Flex>
      </Dialog.Content>
    </Dialog.Root>
  );
};