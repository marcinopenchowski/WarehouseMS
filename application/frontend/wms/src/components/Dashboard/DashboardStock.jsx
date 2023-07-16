import React, { useEffect, useContext } from 'react'
import { TABLE_HEAD } from '../../routes/products/ProductsList'
import { Card, Typography } from '@material-tailwind/react'
import axios from 'axios'
import { ProductContext } from '../../contexts/ProductContext'

// export const productsListData = [
//   {
//     product_id: '1',
//     product_category_id: '3',
//     sku: '23143',
//     product_name: 'Macbook Air',
//     price: '$999.99',
//     description: "Apple Macbook Air 13' Midnight",
//     status: 'IN_STOCK',
//   },
//   {
//     product_id: '2',
//     product_category_id: '3',
//     sku: '23143',
//     product_name: 'Macbook Pro',
//     price: '$1599.99',
//     description: "Apple Macbook Pro 15' Rose gold",
//     status: 'ORDERED',
//   },
//   {
//     product_id: '3',
//     product_category_id: '2',
//     sku: '23143',
//     product_name: 'iPhone 14 Pro Max',
//     price: '$1299.00',
//     description: 'iPhone 14 Pro Max 256GB Matte black',
//     status: 'ORDERED',
//   },
//   {
//     product_id: '4',
//     product_category_id: '2',
//     sku: '23143',
//     product_name: 'iPhone 12',
//     price: '$999.99',
//     description: "Apple Macbook Air 15'",
//     status: 'IN_STOCK',
//   },
//   {
//     product_id: '5',
//     product_category_id: '1',
//     sku: '23143',
//     product_name: 'AirPods Max',
//     price: '$799.99',
//     description: 'Apple AirPods Max 2022',
//     status: 'OUT_OF_STOCK',
//   },
// ]
// const productsStatusStyleMap = {
//   ORDERED: 'text-sky-600 bg-sky-100',
//   OUT_OF_STOCK: 'text-red-600 bg-orange-100',
//   IN_STOCK: 'text-green-600 bg-green-100',
// }

export default function DashboardStock() {
  const { product, setProduct } = useContext(ProductContext)
  useEffect(() => {
    axios
      .get('https://mocki.io/v1/00197dfd-a8af-4ecc-a2b4-111d3faed23b')
      .then((res) => setProduct(res.data))
      .catch((err) => console.log(err))
  }, [setProduct])
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
              {product.map((item, index) => {
                return (
                  <tr key={index}>
                    <td className="p-4">
                      <Typography
                        variant="small"
                        color="blue-gray"
                        className="font-normal">
                        {item.id}
                      </Typography>
                    </td>
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
