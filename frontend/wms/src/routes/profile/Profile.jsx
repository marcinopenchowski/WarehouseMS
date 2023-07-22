import React from 'react'
import { Header, Navbar, ProfileCard } from '../../components'

export default function Profile() {
  return (
    <div className="flex h-screen">
      <div>
        <Navbar />
      </div>
      <div className="flex-1">
        <Header />
        <ProfileCard />
      </div>
    </div>
  )
}
