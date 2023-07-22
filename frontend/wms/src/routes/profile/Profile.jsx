import React from 'react'
import { Header, Navbar, ProfileCard } from '../../components'

export default function Profile() {
  return (
    <div className="flex h-screen">
      <div className="w-fit">
        <Navbar />
      </div>
      <div className="flex-col w-full h-screen">
        <div>
          <Header />
        </div>
        <div className='flex items-center justify-center'>
          <ProfileCard />
        </div>
      </div>
    </div>
  )
}
