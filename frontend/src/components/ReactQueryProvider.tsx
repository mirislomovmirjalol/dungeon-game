import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { useState } from "react";

export const ReactQueryProvider = ({ children }: { children: React.ReactNode }) => {
  const [queryClient] = useState(new QueryClient({
    defaultOptions: {
      queries: {
        refetchOnWindowFocus: false,
        retry: 1,
        staleTime: 10 * (60 * 1000),
      },
    },
  }));
  return <QueryClientProvider client={queryClient}>
    {children}
  </QueryClientProvider>
}