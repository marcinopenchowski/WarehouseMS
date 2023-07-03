import React from "react";
import Navbar from "../../components/Navigation/Navbar";

export default function Dashboard() {
  return (
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div>
        <h1>Products page</h1>
      </div>
    </div>
  );
}
