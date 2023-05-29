import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />

    </Routes>
    // <div className="App">
    //   <Login />
    // </div>
  );
}

export default App;
