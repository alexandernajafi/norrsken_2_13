import React, { Component } from 'react'
import GoogleMap from 'google-map-react'

class HeatMap extends Component {
  render() {
    const { center, zoom } = this.props
    return (
      <div style={{ height: '100vh', width: '100%' }}>
        <GoogleMap
          bootstrapURLKeys={{
            key: process.env.REACT_APP_GOOGLE_MAPS_API_KEY,
          }}
          defaultCenter={center}
          defaultZoom={zoom}
        ></GoogleMap>
      </div>
    )
  }
}

HeatMap.defaultProps = {
  center: {
    lat: 59.33258,
    lng: 18.0649,
  },
  zoom: 11,
}

export default HeatMap
