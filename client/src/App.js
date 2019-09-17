import React from 'react'
import HeatMap from './components/HeatMap'
import './App.css'

function App() {
  return (
    <div className="App">
      <HeatMap
        data={[
          { lat: 59.33258, lng: 18.0649 },
          { lat: 58.33258, lng: 18.0649 },
        ]}
      />
    </div>
  )
}

export default App
