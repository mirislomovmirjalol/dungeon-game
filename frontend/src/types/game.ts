export type CellType = 'EMPTY' | 'WALL' | 'ENTRANCE' | 'EXIT' | 'TEACHER' | 'FRIEND';
export type ItemType = 'TEACHER' | 'FRIEND' | 'EMPTY';
export type GameLevel = 'EASY' | 'MEDIUM' | 'HARD';
export type GameStatus = 'IN_PROGRESS' | 'LEVEL_COMPLETE' | 'WON' | 'LOST';

export enum Direction {
  UP = 'UP',
  DOWN = 'DOWN',
  LEFT = 'LEFT',
  RIGHT = 'RIGHT'
}

export interface Position {
  x: number;
  y: number;
}

export interface Item {
  type: ItemType;
  questionId?: string;
  hint?: string;
}

export interface Cell {
  x: number;
  y: number;
  type: CellType;
  item?: Item;
  explored: boolean;
}

export interface AnsweredQuestion {
  questionId: string;
  answer: string;
  correct: boolean;
}

export interface Inventory {
  hints: string[];
  answeredQuestions: AnsweredQuestion[];
}

export interface Question {
  id: string;
  category: string;
  difficulty: string;
  question: string;
  options: string[];
  correctAnswer: string;
  explanation: string;
  points: number;
}

export interface Game {
  id: string;
  playerName: string;
  level: GameLevel;
  powerPoints: number;
  map: {
    size: number;
    cells: Cell[];
  };
  inventory: Inventory;
  currentPosition: Position;
  status: GameStatus;
  currentQuestion?: Question;
  questionsAnswered: number;
  correctAnswers: number;
  score: number;
  startedAt: string;
  usedQuestionIds: string[];
} 