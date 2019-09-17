import React, { Component } from 'react'
import { connect } from 'react-redux'
import GoogleMap from 'google-map-react'
import { fetchWeatherData } from '../actions/weather'
import {
  getWeatherDataForSelectedTime,
  getSelectedTime,
} from '../reducers/selectors'

class HeatMap extends Component {
  componentDidMount() {
    this.props.fetchWeatherData(this.props.selectedTime)
  }

  render() {
    const { center, zoom, data } = this.props
    return (
      <div style={{ height: '100vh', width: '100%' }}>
        <GoogleMap
          bootstrapURLKeys={{
            key: process.env.REACT_APP_GOOGLE_MAPS_API_KEY,
          }}
          defaultCenter={center}
          defaultZoom={zoom}
          heatmapLibrary={true}
          heatmap={{ positions: data, options: { radius: 20, opacity: 0.6 } }}
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

const mapStateToProps = state => ({
  data: getWeatherDataForSelectedTime(state),
  selectedTime: getSelectedTime(state),
})

const mapDispatchToProps = dispatch => ({
  fetchWeatherData: time => dispatch(fetchWeatherData(time)),
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HeatMap)
