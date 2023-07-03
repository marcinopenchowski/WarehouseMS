import React from "react";
import { DashboardMetrics, DashboardStock, Header, Navbar } from "../../components"

export default function Home() {
  return (
    <div className="flex flex-row bg-neutral-100 h-screen w-screen overflow-hidden">
      <Navbar />
      <div className="flex-1">
        <Header />
        <div className="flex gap-4">
          <DashboardMetrics />
        </div>
        <div className="flex flex-row gap-4 m-4">
          <DashboardStock />
        </div>
      </div>
    </div>
  );
}
