export const getSelectedTime = state => state.weather.selectedTime.toISOString()

export const getWeatherDataForSelectedTime = state =>
  state.weather.data[getSelectedTime(state)] || []

export const getTriggers = state => state.weather.triggers
