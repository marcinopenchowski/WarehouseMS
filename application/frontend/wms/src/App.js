import { Route, Routes } from 'react-router-dom'
import { Home, ProductsList, Settings, Login, Profile } from './routes'
import { useState } from 'react'
import { ProductContext } from './contexts/ProductContext.jsx'

function App() {
  const [product, setProduct] = useState([])

  return (
    <ProductContext.Provider value={{ product, setProduct }}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductsList />} />
        <Route path="/login" element={<Login />} />
        <Route path="/settings" element={<Settings />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </ProductContext.Provider>
  )
}

export default App
