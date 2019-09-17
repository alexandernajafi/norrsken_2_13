import {
  FETCH_WEATHER_DATA_LOADING,
  FETCH_WEATHER_DATA_SUCCESS,
  FETCH_WEATHER_DATA_ERROR,
} from '../actions/types'

const initialState = {
  isLoading: false,
  data: {},
  selectedTime: new Date('2019-09-16T16:30:10+02:00'),
}

const weatherReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case FETCH_WEATHER_DATA_LOADING:
      return {
        ...state,
        isLoading: true,
      }
    case FETCH_WEATHER_DATA_SUCCESS:
      console.log(payload.time)
      return {
        ...state,
        data: {
          ...state.data,
          [payload.time]: payload.data.map(point => ({
            lat: point.latitude,
            lng: point.longitude,
            weight: point.intensity,
          })),
        },
        isLoading: false,
      }
    case FETCH_WEATHER_DATA_ERROR: {
      return {
        ...state,
        isLoading: false,
      }
    }
    default:
      return state
  }
}

export default weatherReducer
