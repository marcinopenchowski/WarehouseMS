import axios from 'axios'
import React, { useState } from 'react'
import api from '../../api/axiosInstance'

export default function Login() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [token, setToken] = useState(null)

  async function handleLogin(event) {
    event.preventDefault()

    try {
      const credentials = {
        login: username,
        password: password,
      }
      const loginUrl = `/login`
      const res = await api.post(loginUrl, credentials)
      const { token } = res.data
      setToken(token)
      localStorage.setItem('jwtToken', token)
      console.log(token)
    } catch (error) {
      console.error('Error logging in:', error)
    }
  }

  return (
    <div className="w-full h-screen flex bg-gray-100">
      <div className="grid grid-cols-1 md:grid-cols-2 m-auto h-[550px] shadow-lg shadow-gray-500 sm:max-w-[800px]">
        <div className="w-full h-[550px] hidden md:block">
          <img
            className="w-full h-full"
            src="/assets/images/login.jpg"
            alt="/"
          />
        </div>
        <div className="p-4 flex flex-col justify-around bg-white">
          <form>
            <h2 className="text-4xl font-bold text-center mb-8">WarehouseMS</h2>
            <div>
              <input
                className="border p-2 my-2 w-full"
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <input
                className="border p-2 my-2 w-full"
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <button
              className="w-full py-2 my-4 bg-gray-300 hover:bg-gray-400"
              onClick={handleLogin}>
              Sign in
            </button>
            <p className="text-center">Forgot Username or Password?</p>
          </form>
          <p className="text-center">Sign Up</p>
        </div>
      </div>
    </div>
  )
}
