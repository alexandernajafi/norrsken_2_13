import {
  FETCH_WEATHER_DATA,
  FETCH_WEATHER_DATA_SUCCESS,
  FETCH_WEATHER_DATA_ERROR,
} from '../actions/types'

const initialState = {
  isLoading: false,
  data: {},
}

const weatherReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case FETCH_WEATHER_DATA:
      return {
        ...state,
        isLoading: true,
      }
    case FETCH_WEATHER_DATA_SUCCESS:
      return {
        ...state,
        data: {
          ...state.data,
          [payload.time]: payload.data,
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
