import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    
    proxy: {
      '/api': {
        target: 'http://localhost:8000', // Change this to your backend server URL
        changeOrigin: true, // Needed for virtual hosted sites
        rewrite: (path) => path.replace(/^\/api/, ''), // Optional: rewrite path if necessary
      },
    },
    
  },
  
})
