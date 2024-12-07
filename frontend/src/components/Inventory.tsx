import React from 'react';
import { Card, Tabs, Text, ScrollArea, Flex } from '@radix-ui/themes';

interface InventoryProps {
  hints: string[];
  answeredQuestions: {
    questionId: string;
    answer: string;
    correct: boolean;
  }[];
}

export const Inventory: React.FC<InventoryProps> = ({
  hints,
  answeredQuestions,
}) => {
  return (
    <Card size="2">
      <Tabs.Root defaultValue="hints">
        <Tabs.List>
          <Tabs.Trigger value="hints">
            Hints ({hints.length})
          </Tabs.Trigger>
          <Tabs.Trigger value="questions">
            Questions ({answeredQuestions.length})
          </Tabs.Trigger>
        </Tabs.List>

        <Tabs.Content value="hints">
          <ScrollArea style={{ height: '200px' }}>
            <Flex direction="column" gap="2" p="2">
              {hints.map((hint, index) => (
                <Card key={index} variant="surface">
                  <Text size="2">{hint}</Text>
                </Card>
              ))}
              {hints.length === 0 && (
                <Text size="2" color="gray">
                  No hints collected yet
                </Text>
              )}
            </Flex>
          </ScrollArea>
        </Tabs.Content>

        <Tabs.Content value="questions">
          <ScrollArea style={{ height: '200px' }}>
            <Flex direction="column" gap="2" p="2">
              {answeredQuestions.map((qa, index) => (
                <Card 
                  key={index}
                  variant="surface"
                  style={{ 
                    borderLeft: '4px solid ' + (qa.correct ? 'var(--green-9)' : 'var(--red-9)')
                  }}
                >
                  <Flex direction="column" gap="1">
                    <Text size="2">
                      Your answer: {qa.answer}
                      {qa.correct ? ' ✓' : ' ✗'}
                    </Text>
                  </Flex>
                </Card>
              ))}
              {answeredQuestions.length === 0 && (
                <Text size="2" color="gray">
                  No questions answered yet
                </Text>
              )}
            </Flex>
          </ScrollArea>
        </Tabs.Content>
      </Tabs.Root>
    </Card>
  );
}; 