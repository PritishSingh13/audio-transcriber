import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  base: './', // 👈 VERY IMPORTANT for correct asset loading in production
  plugins: [react()],
})
