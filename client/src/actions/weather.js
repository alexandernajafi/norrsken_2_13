import {
  FETCH_WEATHER_DATA_LOADING,
  FETCH_WEATHER_DATA_SUCCESS,
  FETCH_WEATHER_DATA_ERROR,
} from '../actions/types'

export const fetchWeatherDataLoading = () => ({
  type: FETCH_WEATHER_DATA_LOADING,
})

export const fetchWeatherDataSuccess = (time, data) => ({
  type: FETCH_WEATHER_DATA_SUCCESS,
  payload: { time, data },
})

export const fetchWeatherDataError = error => ({
  type: FETCH_WEATHER_DATA_ERROR,
  payload: error,
})

export const fetchWeatherData = time => dispatch => {
  dispatch(fetchWeatherDataLoading())

  /*return dispatch(
    fetchWeatherDataSuccess(time, [
      {
        longitude: 17.906385542168675,
        latitude: 59.41068965517241,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.906385542168675,
        latitude: 59.4151724137931,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.906385542168675,
        latitude: 59.41965517241379,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.906385542168675,
        latitude: 59.42413793103448,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.40620689655172,
        intensity: 0.6000000238418579,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.41068965517241,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.4151724137931,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.41965517241379,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.42413793103448,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.915060240963854,
        latitude: 59.42862068965517,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.923734939759036,
        latitude: 59.41068965517241,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.923734939759036,
        latitude: 59.4151724137931,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.923734939759036,
        latitude: 59.41965517241379,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.923734939759036,
        latitude: 59.42413793103448,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.923734939759036,
        latitude: 59.42862068965517,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.932409638554216,
        latitude: 59.41068965517241,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.932409638554216,
        latitude: 59.4151724137931,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.932409638554216,
        latitude: 59.41965517241379,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.932409638554216,
        latitude: 59.42413793103448,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.941084337349395,
        latitude: 59.40620689655172,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.941084337349395,
        latitude: 59.41068965517241,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.941084337349395,
        latitude: 59.4151724137931,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.941084337349395,
        latitude: 59.41965517241379,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.941084337349395,
        latitude: 59.42413793103448,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.949759036144577,
        latitude: 59.41068965517241,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.949759036144577,
        latitude: 59.4151724137931,
        intensity: 0.8999999761581421,
      },
      {
        longitude: 17.949759036144577,
        latitude: 59.41965517241379,
        intensity: 0.800000011920929,
      },
      {
        longitude: 17.958433734939756,
        latitude: 59.41068965517241,
        intensity: 0.699999988079071,
      },
      {
        longitude: 17.958433734939756,
        latitude: 59.4151724137931,
        intensity: 0.800000011920929,
      },
      {
        longitude: 18.07987951807229,
        latitude: 59.46,
        intensity: 0.699999988079071,
      },
      {
        longitude: 18.07987951807229,
        latitude: 59.46448275862069,
        intensity: 0.699999988079071,
      },
      {
        longitude: 18.08855421686747,
        latitude: 59.46,
        intensity: 0.6000000238418579,
      },
      {
        longitude: 18.08855421686747,
        latitude: 59.46448275862069,
        intensity: 0.699999988079071,
      },
    ])
  )*/

  fetch(
    `https://afternoon-dawn-61570.herokuapp.com/api/weather?time=${encodeURIComponent(
      time
    )}`
  )
    .then(response => {
      if (!response.ok) {
        throw Error(response.statusText)
      }
      return response
    })
    .then(response => response.json())
    .then(data => dispatch(fetchWeatherDataSuccess(time, data.points)))
    .catch(error => dispatch(fetchWeatherDataError(error)))
}
