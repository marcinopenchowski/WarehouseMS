import React from 'react'

export const productsListData = [
  {
    product_id: '1',
    product_category_id: '3',
    sku: '23143',
    product_name: 'Macbook Air',
    price: '$999.99',
    description: "Apple Macbook Air 13' Midnight",
    status: 'IN_STOCK',
  },
  {
    product_id: '2',
    product_category_id: '3',
    sku: '23143',
    product_name: 'Macbook Pro',
    price: '$1599.99',
    description: "Apple Macbook Pro 15' Rose gold",
    status: 'ORDERED',
  },
  {
    product_id: '3',
    product_category_id: '2',
    sku: '23143',
    product_name: 'iPhone 14 Pro Max',
    price: '$1299.00',
    description: 'iPhone 14 Pro Max 256GB Matte black',
    status: 'ORDERED',
  },
  {
    product_id: '4',
    product_category_id: '2',
    sku: '23143',
    product_name: 'iPhone 12',
    price: '$999.99',
    description: "Apple Macbook Air 15'",
    status: 'IN_STOCK',
  },
  {
    product_id: '5',
    product_category_id: '1',
    sku: '23143',
    product_name: 'AirPods Max',
    price: '$799.99',
    description: 'Apple AirPods Max 2022',
    status: 'OUT_OF_STOCK',
  },
]
const productsStatusStyleMap = {
  ORDERED: 'text-sky-600 bg-sky-100',
  OUT_OF_STOCK: 'text-red-600 bg-orange-100',
  IN_STOCK: 'text-green-600 bg-green-100',
}

export default function DashboardStock() {
  return (
    <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1">
      <strong className="text-gray-700 font-large">Recent Products</strong>
      <div className="border-x border-gray-200 rounded-sm mt-3 text-center">
        <table className="w-full text-gray-700">
          <thead>
            <tr>
              <th>ID</th>
              <th>Product Name</th>
              <th>Product Category</th>
              <th>Price</th>
              <th>Description</th>
              <th>Availability</th>
            </tr>
          </thead>
          <tbody>
            {productsListData.map((product) => (
              <tr key={product.product_id}>
                <td>{product.product_id}</td>
                <td>{product.product_name}</td>
                <td>{product.product_category_id}</td>
                <td>{product.price}</td>
                <td>{product.description}</td>
                <td>
                  <span
                    className={`capitalize py-1 px-2 rounded-md text-xs ${
                      productsStatusStyleMap[product.status]
                    }`}>
                    {product.status.replaceAll('_', ' ').toLowerCase()}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
