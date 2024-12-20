import { Container, Heading, Flex, Text } from '@radix-ui/themes';
import { useGameState } from './hooks/useGameState';
import { GameGrid } from './components/GameGrid';
import { Inventory } from './components/Inventory';
import { StartDialog } from './components/StartDialog';
import { GameInfoBar } from './components/GameInfoBar';
import { CurrentQuestionDialog } from './components/CurrentQuestionDialog';
import { GameOverDialog } from './components/GameOverDialog';
import { LeaderBoardDialog } from './components/LeaderBoardDialog';
import { MessageDialog } from './components/MessageDialog';

export default function App() {
  const {
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
    message,
    closeMessage,
  } = useGameState();

  return (
    <Container>
      <Flex direction="column" gap="4" style={{ padding: '20px' }}>
        <Heading align="center" size="6">Dungeon Game</Heading>
        <Flex justify="end">
          <LeaderBoardDialog />
        </Flex>

        {error && (
          <Text color="red" size="2" align="center">
            {error}
          </Text>
        )}

        {game ? (
          <>
            <GameInfoBar game={game} />
            <GameGrid
              cells={game.map.cells}
              size={game.map.size}
              currentPosition={game.currentPosition}
              powerPoints={game.powerPoints}
              onMove={handleMove}
            />
            <Inventory
              hints={game.inventory.hints}
              answeredQuestions={game.inventory.answeredQuestions}
            />
            <CurrentQuestionDialog game={game} onAnswer={handleAnswer} />
            <GameOverDialog game={game} onNextLevel={handleNextLevel} onRetry={handleGameOver} />
          </>
        ) : (
          <StartDialog
            open={startDialogOpen}
            playerName={playerName}
            gameLevel={gameLevel}
            onChangeName={setPlayerName}
            onChangeLevel={setGameLevel}
            onStart={handleStartGame}
          />
        )}

        {message && (
          <MessageDialog message={message} onClose={closeMessage} />
        )}
      </Flex>
    </Container>
  );
}