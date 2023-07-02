import React from "react";
import { IoBagHandle } from "react-icons/io5";

export default function DashboardStatsGrid() {
  return (
    <div className="flex gap-4 w-full m-4">
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-sky-500 mr-2">
          <IoBagHandle className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">Total Sales</span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              $34525.60
            </strong>
            <span className="text-sm text-green-500 pl-2">+$1337</span>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-orange-500 mr-2">
          <IoBagHandle className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">Total Expenses</span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              $20025.60
            </strong>
            <span className="text-sm text-green-500 pl-2">+$234</span>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-yellow-500 mr-2">
          <IoBagHandle className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">Total Customers</span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              153
            </strong>
            <span className="text-sm text-green-500 pl-2">+11</span>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-green-600 mr-2">
          <IoBagHandle className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">Total Orders</span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              1337
            </strong>
            <span className="text-sm text-red-500 pl-2">-13</span>
          </div>
        </div>
      </BoxWrapper>
    </div>
  );
}

function BoxWrapper({ children }) {
  return (
    <div className="bg-white rounded-sm p-4 flex-1 border border-gray-200 flex items-center">
      {children}
    </div>
  );
}
