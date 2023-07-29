import React, { useEffect, useContext, useState } from 'react'
import { Header, Navbar } from '../../components'
import axios from 'axios'
import { ProductContext } from '../../contexts/ProductContext.jsx'
import { FilterMatchMode } from 'primereact/api'
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column'
import { InputText } from 'primereact/inputtext'
import { Button } from 'primereact/button'
import 'primereact/resources/themes/saga-orange/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'
import RowEdit from '../edit/RowEdit'

export default function Dashboard() {
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
  })

  const { products, setProducts } = useContext(ProductContext)
  const fetchAssetData = () => axios.get('http://localhost:8080/asset')
  const fetchAccessoryData = () => axios.get('http://localhost:8080/accessory')
  const fetchSoftwareData = () => axios.get('http://localhost:8080/software')

  const fetchDataAndUpdateState = () => {
    axios
      .all([fetchAssetData(), fetchAccessoryData(), fetchSoftwareData()])
      .then(
        axios.spread((assetRes, accessoryRes, softwareRes) => {
          const assetDataWithType = assetRes.data.map((asset) => ({
            ...asset,
            type: 'asset',
          }));
          const accessoryDataWithType = accessoryRes.data.map((accessory) => ({
            ...accessory,
            type: 'accessory',
          }));
          const softwareDataWithType = softwareRes.data.map((software) => ({
            ...software,
            type: 'software',
          }));
  
          const combinedData = [
            ...assetDataWithType,
            ...accessoryDataWithType,
            ...softwareDataWithType,
          ];
          setProducts(combinedData);
        })
      )
      .catch((err) => console.log(err));
  };
  

  const deleteProduct = async (id, type) => {
    try {
      // Determine the appropriate endpoint based on the product type
      const endpoint = `http://localhost:8080/${type}/${id}`

      // Make a DELETE request to the server endpoint to delete the product
      await axios.delete(endpoint)

      // If the deletion was successful, update the state with the modified array
      const updatedProducts = products.filter((product) => product.id !== id)
      setProducts(updatedProducts)
      fetchDataAndUpdateState()
    } catch (error) {
      console.error('Error deleting product:', error)
      // Handle any error scenarios if necessary
    }
  }

  const actionBodyTemplate = (rowData) => {
    return (
      <div className="flex flex-row gap-4">
        <Button type="button" icon="pi pi-pencil" rounded />
        <Button
          type="button"
          icon="pi pi-trash"
          style={{ backgroundColor: 'var(--red-300)' }}
          rounded
          onClick={() => deleteProduct(rowData.id, rowData.type)}
        />
      </div>
    )
  }

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
            <Column body={actionBodyTemplate} />
          </DataTable>
        </div>
      </div>
    </div>
  )
}