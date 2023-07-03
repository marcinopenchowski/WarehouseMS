import React from "react";
import Navbar from "../../components/Navigation/Navbar";

export default function Dashboard() {
  return (
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div>
        <h1>Settings page</h1>
      </div>
    </div>
  );
}
