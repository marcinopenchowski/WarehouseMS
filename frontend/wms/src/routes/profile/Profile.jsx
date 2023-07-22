import React from 'react'
import { Header, Navbar } from '../../components'
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
} from '@material-tailwind/react'
import { TextField } from '@mui/material'

export default function Profile() {
  return (
    <div className="flex">
      <div>
        <Navbar />
      </div>
      <div className="flex-1">
        <Header />
        <div className="flex justify-center h-full">
          <Card className="m-10 w-2/3 items-center shadow-xl">
            <CardHeader
              color="orange"
              className="relative h-40 w-40 rounded-full">
              <img
                src="https://source.unsplash.com/160x160?face"
                alt="img-blur-shadow"
                layout="fill"
              />
            </CardHeader>
            <CardBody className="text-center mt-5">
              <Typography variant="h5" color="blue-gray" className="mb-2">
                Hello User!
              </Typography>
              <TextField id="outlined-basic" label="Username" variant="outlined" />
            </CardBody>
          </Card>
        </div>
      </div>
    </div>
  )
}
