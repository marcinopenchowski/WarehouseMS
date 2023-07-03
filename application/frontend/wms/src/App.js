import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";
import Login from "./components/Login";
import Products from "./components/Products";
import Settings from "./components/Settings";


function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/products" element={<Products />} />
      <Route path="/login" element={<Login />} />
      <Route path="/settings" element={<Settings />} />


    </Routes>
    // <div className="App">
    //   <Login />
    // </div>
  );
}

export default App;
