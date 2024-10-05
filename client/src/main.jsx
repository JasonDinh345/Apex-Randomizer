import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import ApexRandomizer from './js/ApexRandomizer'


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ApexRandomizer/>
  </StrictMode>
)
