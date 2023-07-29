import { Route, Routes } from 'react-router-dom'
import { Home, ProductsList, Settings, Login, Profile, RowEdit } from './routes'
import { useState } from 'react'
import { ProductContext } from './contexts/ProductContext.jsx'

function App() {
  const [products, setProducts] = useState([])

  return (
    <ProductContext.Provider value={{ products, setProducts }}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductsList />} />
        <Route path="/login" element={<Login />} />
        <Route path="/settings" element={<Settings />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/edit" element={<RowEdit />} />
      </Routes>
    </ProductContext.Provider>
  )
}

export default App
