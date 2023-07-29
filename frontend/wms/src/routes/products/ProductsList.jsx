import React, { useEffect, useContext, useState } from 'react'
import { Header, Navbar } from '../../components'
import axios from 'axios'
import { ProductContext } from '../../contexts/ProductContext.jsx'
import { FilterMatchMode } from 'primereact/api'
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import { InputText } from 'primereact/inputtext'
import 'primereact/resources/themes/saga-orange/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'

export default function Dashboard() {
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
  })
  const { products, setProducts } = useContext(ProductContext)
  const fetchAssetData = () => axios.get('http://localhost:8080/asset')
  const fetchAccessoryData = () => axios.get('http://localhost:8080/accessory')
  const fetchSoftwareData = () => axios.get('http://localhost:8080/software')

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
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div className="flex-1">
        <Header />
        <div className="m-5">
          <div className="flex justify-content-end mb-5">
            <span className="p-input-icon-left">
              <i className="pi pi-search" />
              <InputText
                onInput={(e) =>
                  setFilters({
                    global: {
                      value: e.target.value,
                      matchMode: FilterMatchMode.CONTAINS,
                    },
                  })
                }
                placeholder="Keyword Search"
              />
            </span>
          </div>
          <DataTable
            removableSort
            filters={filters}
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
    </div>
  )
}
