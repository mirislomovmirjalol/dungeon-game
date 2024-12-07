import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import "@radix-ui/themes/styles.css";
import { Theme } from "@radix-ui/themes";
import { ReactQueryProvider } from './components/ReactQueryProvider.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ReactQueryProvider>
      <Theme
        accentColor='ruby'
        radius='large'
      >
        <App />
      </Theme>
    </ReactQueryProvider>
  </StrictMode>,
)
