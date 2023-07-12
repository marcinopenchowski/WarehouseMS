import React from 'react'
import { Header, Navbar } from '../../components'

export default function Dashboard() {
  return (
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div className="flex-1">
        <Header />
      </div>
    </div>
  )
}
