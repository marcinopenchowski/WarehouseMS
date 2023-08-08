import React, { useState, useEffect } from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import {
  Home,
  ProductsList,
  Settings,
  Login,
  Profile,
  Register,
} from './routes'
import { ProductContext } from './contexts/ProductContext.jsx'
import { getToken } from './utils/auth'
import { useNavigate } from 'react-router-dom'

function App() {
  const [products, setProducts] = useState([])
  const isLoggedIn = !!getToken()
  const navigate = useNavigate()

  const productContextValue = {
    products,
    setProducts,
  }

  useEffect(() => {
    const allowedRoutes = ['/login', '/register']

    if (!isLoggedIn && !allowedRoutes.includes(window.location.pathname)) {
      navigate('/login')
    }
  }, [isLoggedIn, navigate])

  return (
    <ProductContext.Provider value={productContextValue}>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/*"
          element={isLoggedIn ? null : <Navigate to="/login" />}
        />

        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductsList />} />
        <Route path="/settings" element={<Settings />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </ProductContext.Provider>
  )
}

export default App
