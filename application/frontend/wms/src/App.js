import { Route, Routes } from 'react-router-dom'
import { Home, ProductsList, Settings, Login } from './routes'
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
    </Routes>
    </ProductContext.Provider>
  )
}

export default App
