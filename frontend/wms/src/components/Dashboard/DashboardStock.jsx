import React, { useEffect, useContext } from 'react'
import { TABLE_HEAD } from '../../routes/products/ProductsList'
import { Card, Typography } from '@material-tailwind/react'
import axios from 'axios'
import { ProductContext } from '../../contexts/ProductContext'

export default function DashboardStock() {
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
    <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1">
      <strong className="text-gray-700 font-large">Recent Products</strong>
      <div className="border-x border-gray-200 rounded-sm mt-3 text-center">
        <Card className="overflow-scroll h-full w-full">
          <table className="w-full min-w-max table-auto text-center">
            <thead className="bg-gray-300">
              <tr>
                {TABLE_HEAD.map((head) => (
                  <th
                    key={head}
                    className="border-b border-blue-gray-100 bg-blue-gray-50 p-4">
                    <Typography
                      variant="small"
                      color="blue-gray"
                      className="font-normal leading-none opacity-70">
                      {head}
                    </Typography>
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {products.map((item, index) => {
                return (
                  <tr key={index}>
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal">
                        {item.name}
                      </Typography>
                    </td>
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal">
                        {item.price}
                      </Typography>
                    </td>
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal">
                        {item.description}
                      </Typography>
                    </td>
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal">
                        {item.category.name}
                      </Typography>
                    </td>
                    <td className="p-4">
                      <Typography
                        as="a"
                        href="#"
                        variant="small"
                        color="blue"
                        className="font-medium">
                        Edit
                      </Typography>
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </Card>
      </div>
    </div>
  )
}
