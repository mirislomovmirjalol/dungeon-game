import { Button, Dialog, Flex } from '@radix-ui/themes';

export function MessageDialog({ message, onClose }: { message: string, onClose: () => void }) {
  return (
    <Dialog.Root open>
      <Dialog.Content>
        <Dialog.Title>
          {message}
        </Dialog.Title>
        <Dialog.Close onClick={onClose}>
          <Flex justify="end">
            <Button>Close</Button>
          </Flex>
        </Dialog.Close>
      </Dialog.Content>
    </Dialog.Root>
  )
}