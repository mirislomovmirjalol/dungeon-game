import { Button, Dialog, Flex, Text } from '@radix-ui/themes';
import { useQuery } from '@tanstack/react-query'

interface LeaderboardEntry {
  id: string
  playerName: string
  level: 'EASY' | 'MEDIUM' | 'HARD'
  score: number
  completedAt: string
}

export function LeaderBoardDialog() {
  const { data: leaderboard, isRefetching } = useQuery<LeaderboardEntry[]>({
    queryKey: ['leaderboard'],
    queryFn: async () => {
      const response = await fetch('http://localhost:8080/api/leaderboard')
      const data = await response.json()
      return data
    }
  })

  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <Button>Leaderboard</Button>
      </Dialog.Trigger>
      <Dialog.Content>
        <Dialog.Title>
          Leaderboard
        </Dialog.Title>

        {isRefetching ? (
          <Flex align="center" justify="center" height="100%">
            <Text size="2">Loading...</Text>
          </Flex>
        ) : (
          <div>
            <table className="w-full">
              <thead>
                <tr className="border-b text-left">
                  <th className="pb-2">Rank</th>
                  <th className="pb-2">Player</th>
                  <th className="pb-2">Level</th>
                  <th className="pb-2">Score</th>
                  <th className="pb-2">Date</th>
                </tr>
              </thead>
              <tbody>
                {leaderboard?.map((entry, index) => (
                  <tr key={entry.id} className="border-b">
                    <td className="py-2">{index + 1}</td>
                    <td className="py-2">{entry.playerName}</td>
                    <td className="py-2">{entry.level}</td>
                    <td className="py-2">{entry.score}</td>
                    <td className="py-2">
                      {new Date(entry.completedAt).toLocaleDateString()}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        <Dialog.Close>
          <Flex justify="end">
            <Button mt="4">Close</Button>
          </Flex>
        </Dialog.Close>
      </Dialog.Content>
    </Dialog.Root >
  )
}
