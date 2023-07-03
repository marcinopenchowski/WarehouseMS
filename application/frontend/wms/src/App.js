import { Route, Routes } from "react-router-dom";
import { Home, ProductsList, Settings, Login } from "./routes"

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/products" element={<ProductsList />} />
      <Route path="/login" element={<Login />} />
      <Route path="/settings" element={<Settings />} />
    </Routes>
  );
}

export default App;
