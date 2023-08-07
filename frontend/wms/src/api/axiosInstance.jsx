import axios from 'axios'
import { getToken } from '../utils/auth'

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    common: {
      Authorization: `Bearer ${getToken()}`,
    },
  },
})

export default api
