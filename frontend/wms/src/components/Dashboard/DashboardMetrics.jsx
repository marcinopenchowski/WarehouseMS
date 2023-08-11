import React, { useEffect, useState } from 'react'
import {
  IoBagHandle,
  IoPeople,
  IoPricetagsSharp,
  IoBarChart,
} from 'react-icons/io5'
import api from '../../api/axiosInstance'
import { getToken } from '../../utils/auth'

export default function DashboardStatsGrid() {
  const [totalExpenses, setTotalPrice] = useState('')
  const [totalUsers, setTotalUsers] = useState('')
  const [avgExpenses, setAvgExpenses] = useState('')
  const [totalProducts, setTotalProducts] = useState('')

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log(getToken())
        const totalPriceRes = await api.get('/item/totalPrice')
        const totalProductsRes = await api.get('/item/totalCount')
        const avgExpensesRes = await api.get('/item/totalAverage')
        const totalUsersRes = await api.get('/owner/totalCount')
        setTotalPrice(totalPriceRes.data)
        setTotalUsers(totalUsersRes.data)
        setAvgExpenses(avgExpensesRes.data)
        setTotalProducts(totalProductsRes.data)
      } catch (error) {
        console.error('Error fetching data:', error)
      }
    }
    fetchData()
  }, [])
  return (
    <div className="flex gap-4 w-full m-4">
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-sky-500 mr-2">
          <IoBarChart className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">
            Average Expenses
          </span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              {avgExpenses}
            </strong>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-orange-500 mr-2">
          <IoBagHandle className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">
            Total Expenses
          </span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              {totalExpenses}
            </strong>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-yellow-500 mr-2">
          <IoPeople className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">Total Users</span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              {totalUsers}
            </strong>
          </div>
        </div>
      </BoxWrapper>
      <BoxWrapper>
        <div className="rounded-full h-12 w-12 flex items-center justify-center bg-green-600 mr-2">
          <IoPricetagsSharp className="text-2xl text-white" />
        </div>
        <div>
          <span className="text-sm text-gray-500 font-light">
            Total Products
          </span>
          <div className="flex items-center">
            <strong className="text-xl text-gray-700 font-semibold">
              {totalProducts}
            </strong>
          </div>
        </div>
      </BoxWrapper>
    </div>
  )
}

function BoxWrapper({ children }) {
  return (
    <div className="bg-white rounded-sm p-4 flex-1 border border-gray-200 flex items-center">
      {children}
    </div>
  )
}
