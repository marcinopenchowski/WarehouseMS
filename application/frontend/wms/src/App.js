import { Route, Routes } from "react-router-dom";
import Main from "./components/Main";
import Login from "./components/Login";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/login" element={<Login />} />
    </Routes>
    // <div className="App">
    //   <Login />
    // </div>
  );
}

export default App;
