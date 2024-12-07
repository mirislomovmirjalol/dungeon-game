import React from 'react';
import { Grid, Button, Box, Text, Progress } from '@radix-ui/themes';
import { Cell, Position, Direction } from '../types/game';
import { ArrowUpIcon, ArrowDownIcon, ArrowLeftIcon, ArrowRightIcon } from '@radix-ui/react-icons';

interface GameGridProps {
  cells: Cell[];
  size: number;
  currentPosition: Position;
  powerPoints: number;
  onMove: (direction: Direction) => void;
}

export const GameGrid: React.FC<GameGridProps> = ({
  cells,
  size,
  currentPosition,
  powerPoints,
  onMove,
}) => {
  const getCellContent = (x: number, y: number) => {
    const cell = cells.find(c => c.x === x && c.y === y);
    if (!cell) return '?';

    if (x === currentPosition.x && y === currentPosition.y) {
      return '👤';
    }

    if (!cell.explored) return '❓';

    switch (cell.type) {
      case 'WALL': return '🧱';
      case 'ENTRANCE': return '🚪';
      case 'EXIT': return '🚪';
      default: return cell.type === 'TEACHER' ? '👨‍🏫' :
        cell.type === 'FRIEND' ? '👬' : '⬜';
    }
  };

  const handleKeyDown = (e: KeyboardEvent) => {
    switch (e.key) {
      case 'ArrowUp':
        onMove(Direction.UP);
        break;
      case 'ArrowDown':
        onMove(Direction.DOWN);
        break;
      case 'ArrowLeft':
        onMove(Direction.LEFT);
        break;
      case 'ArrowRight':
        onMove(Direction.RIGHT);
        break;
    }
  };

  React.useEffect(() => {
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, []);

  return (
    <Box>
      <Text size="2" mb="2">Power Points: {powerPoints}</Text>
      <Progress value={powerPoints} />
      <Grid columns={`${size}`} gap="1" mt="4" mb="4">
        {Array.from({ length: size * size }, (_, i) => {
          const x = Math.floor(i / size);
          const y = i % size;
          return (
            <Box
              key={`${x}-${y}`}
              style={{
                width: '40px',
                height: '40px',
                border: '1px solid var(--gray-5)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                fontSize: '1.5em',
                background: x === currentPosition.x && y === currentPosition.y
                  ? 'var(--blue-3)'
                  : 'var(--gray-1)',
              }}
            >
              {getCellContent(x, y)}
            </Box>
          );
        })}
      </Grid>
      <Grid columns="3" gap="2" width="auto" style={{ maxWidth: '150px', margin: '0 auto' }}>
        <Box></Box>
        <Button variant="soft" onClick={() => onMove(Direction.UP)}>
          <ArrowUpIcon />
        </Button>
        <Box></Box>
        <Button variant="soft" onClick={() => onMove(Direction.LEFT)}>
          <ArrowLeftIcon />
        </Button>
        <Button variant="soft" onClick={() => onMove(Direction.DOWN)}>
          <ArrowDownIcon />
        </Button>
        <Button variant="soft" onClick={() => onMove(Direction.RIGHT)}>
          <ArrowRightIcon />
        </Button>
      </Grid>
    </Box>
  );
}; 