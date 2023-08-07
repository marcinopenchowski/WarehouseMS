import { Navigate, Route, Routes } from 'react-router-dom'
import { Home, ProductsList, Settings, Login, Profile, Register } from './routes'
import { useState } from 'react'
import { ProductContext } from './contexts/ProductContext.jsx'

function App() {
  const [products, setProducts] = useState([])
  const [isLoggedIn, setIsLoggedIn] = useState(false)

  return (
    <ProductContext.Provider value={{ products, setProducts }}>
      <Routes>
        <Route path="/" element={<Home />} />
        {isLoggedIn ? (
          <>
            <Route path="/products" element={<ProductsList />} />
            <Route path="/settings" element={<Settings />} />
            <Route path="/profile" element={<Profile />} />
          </>
        ) : (
          <Route path="/login" element={<Login />} />
        )}
        <Route path="/*" element={<Navigate to="/login" />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </ProductContext.Provider>
  )
}

export default App
