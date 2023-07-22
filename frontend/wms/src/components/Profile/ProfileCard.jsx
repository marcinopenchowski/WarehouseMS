import React, { useState } from 'react'
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
} from '@material-tailwind/react'
import { TextField, Button, Snackbar, Alert } from '@mui/material'

export default function ProfileCard() {
  const [open, setOpen] = useState(false)

  const handleClick = () => {
    setOpen(true)
  }

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    setOpen(false)
  }

  return (
    <Card className="p-10 m-10 w-2/3 items-center shadow-xl ">
      <CardHeader color="orange" className="relative h-40 w-40 rounded-full">
        <img
          src="https://source.unsplash.com/160x160?face"
          alt="img-blur-shadow"
          layout="fill"
        />
      </CardHeader>
      <CardBody className="flex flex-col text-center mt-5">
        <Typography variant="h5" color="blue-gray" className="mb-10">
          Hello User!
        </Typography>
        <div className="flex w-72 flex-col items-center  gap-6">
          <TextField id="outlined-basic" label="Username" variant="outlined" />
          <TextField id="outlined-basic" label="Email" variant="outlined" />
          <TextField id="outlined-basic" label="Password" variant="outlined" />
          <Button onClick={handleClick} color='warning' variant="outlined">
            Save
          </Button>
          <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
            <Alert
              onClose={handleClose}
              severity="success"
              sx={{ width: '100%' }}>
              Changes saved successfuly!
            </Alert>
          </Snackbar>
        </div>
      </CardBody>
    </Card>
  )
}
