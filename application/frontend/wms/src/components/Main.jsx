import React from "react";
import Navbar from "./shared/Navbar";
import Header from "./shared/Header";
import DashboardStatsGrid from "./DashboardStatsGrid";
import RecentProducts from "./RecentProducts";

export default function Main() {
  return (
    <div className="flex flex-row bg-neutral-100 h-screen w-screen overflow-hidden">
      <Navbar />
      <div className="flex-1">
        <Header />
        <div className="flex gap-4">
          <DashboardStatsGrid />
        </div>
        <div className="flex flex-row gap-4 m-4">
          <RecentProducts />
        </div>
      </div>
    </div>
  );
}
