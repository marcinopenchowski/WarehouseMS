import React, { useEffect, useContext } from 'react'
import { ProductContext } from '../../contexts/ProductContext'
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import api from '../../api/axiosInstance'
import axios from 'axios'

export default function DashboardStock() {
  const { products, setProducts } = useContext(ProductContext)
  const fetchAssetData = () => api.get('/asset')
  const fetchAccessoryData = () => api.get('/accessory')
  const fetchSoftwareData = () => api.get('/software')

  useEffect(() => {
    axios
      .all([fetchAssetData(), fetchAccessoryData(), fetchSoftwareData()])
      .then(
        axios.spread((assetRes, accessoryRes, softwareRes) => {
          const combinedData = [
            ...assetRes.data,
            ...accessoryRes.data,
            ...softwareRes.data,
          ]
          setProducts(combinedData)
        })
      )
      .catch((err) => console.log(err))
  }, [setProducts])
  return (
    <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1">
      <strong className="text-gray-700 font-large">Recent Products</strong>
      <div className="border-x border-gray-200 rounded-sm mt-3 text-center">
        <DataTable
          removableSort
          sortMode="multiple"
          value={products}
          style={{ minWidth: '50rem' }}>
          <Column
            field="name"
            header="Product Name"
            sortable
            style={{ width: '25%' }}></Column>
          <Column
            field="price"
            header="Price"
            sortable
            style={{ width: '25%' }}
          />
          <Column
            field="description"
            header="Description"
            sortable
            style={{ width: '25%' }}
          />
          <Column
            field="category.name"
            header="Category"
            sortable
            style={{ width: '25%' }}
          />
        </DataTable>
      </div>
    </div>
  )
}
