import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import ApexRandomizer from './js/ApexRandomizer'

import "./css/main.css"
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ApexRandomizer/>
  </StrictMode>
)
