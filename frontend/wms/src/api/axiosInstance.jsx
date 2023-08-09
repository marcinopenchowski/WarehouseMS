import axios from 'axios'
import { getToken } from '../utils/auth'

const api = axios.create({
  baseURL: 'http://localhost:8080',
})

// Add a request interceptor to update the Authorization header
api.interceptors.request.use(
  (config) => {
    const token = getToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

export default api
