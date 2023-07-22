import React, { useEffect, useContext } from 'react'
import { Header, Navbar } from '../../components'
import { Card, Typography } from '@material-tailwind/react'
import axios from 'axios'
import { ProductContext } from '../../contexts/ProductContext.jsx'

export const TABLE_HEAD = [
  'ID',
  'Product Name',
  'Price',
  'Description',
  'Category Name',
  '',
]

export default function Dashboard() {
  const { product, setProduct } = useContext(ProductContext)

  useEffect(() => {
    axios
      .get('https://mocki.io/v1/00197dfd-a8af-4ecc-a2b4-111d3faed23b')
      .then((res) => setProduct(res.data))
      .catch((err) => console.log(err))
  }, [setProduct])

  return (
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div className="flex-1">
        <Header />
        <div className="m-5">
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
    </div>
  )
}
