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
import AddProduct from '../../components/TableData/AddProduct'

export default function Dashboard() {
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
  })
  const [isModalOpen, setIsModalOpen] = useState(false)

  const { products, setProducts } = useContext(ProductContext)
  const fetchAssetData = () => axios.get('http://localhost:8080/asset')
  const fetchAccessoryData = () => axios.get('http://localhost:8080/accessory')
  const fetchSoftwareData = () => axios.get('http://localhost:8080/software')

  useEffect(() => {
    axios
      .all([fetchAssetData(), fetchAccessoryData(), fetchSoftwareData()])
      .then(
        axios.spread((assetRes, accessoryRes, softwareRes) => {
          const assetDataWithType = assetRes.data.map((asset) => ({
            ...asset,
            type: 'asset',
          }))
          const accessoryDataWithType = accessoryRes.data.map((accessory) => ({
            ...accessory,
            type: 'accessory',
          }))
          const softwareDataWithType = softwareRes.data.map((software) => ({
            ...software,
            type: 'software',
          }))

          const combinedData = [
            ...assetDataWithType,
            ...accessoryDataWithType,
            ...softwareDataWithType,
          ]
          setProducts(combinedData)
        })
      )
      .catch((err) => console.log(err))
  }, [setProducts])

  const deleteProduct = async (id, type) => {
    try {
      const endpoint = `http://localhost:8080/${type}/${id}`

      await axios.delete(endpoint)

      const updatedProducts = products.filter((product) => product.id !== id)
      setProducts(updatedProducts)
    } catch (error) {
      console.error('Error deleting product:', error)
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

  const toggleModal = () => {
    setIsModalOpen((prev) => !prev)
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
            paginator
            rows={5}
            rowsPerPageOptions={[2, 5, 10]}
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
        <div className="flex justify-end m-5">
          <Button icon="pi pi-plus" onClick={toggleModal}>
            Add product
          </Button>
          <AddProduct isOpen={isModalOpen} setIsOpen={setIsModalOpen} />
        </div>
      </div>
    </div>
  )
}
