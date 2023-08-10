import React, { useEffect, useState } from 'react'
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
} from '@material-tailwind/react'
import { getToken } from '../../utils/auth'
import jwtDecode from 'jwt-decode'
import api from '../../api/axiosInstance'
import { Controller, useForm } from 'react-hook-form'
import { InputText } from 'primereact/inputtext'
import { Password } from 'primereact/password'
import { Button } from 'primereact/button'

export default function ProfileCard() {
  const [token] = useState(getToken())
  const [username, setUsername] = useState('')
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm()

  useEffect(() => {
    if (token) {
      try {
        const decodedToken = jwtDecode(token)
        console.log(decodedToken)
        if (decodedToken) {
          setUsername(decodedToken.sub)
        }
      } catch (error) {
        console.error('Error decoding token:', error)
      }
    }
  })

  const onSubmit = async (data) => {
    try {
      const endpoint = '/updateUser'
      const res = await api.post(endpoint, data)
      console.log(res.data)
    } catch (error) {
      console.error('Error changing user details:', error)
    }
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
          Hello {username}!
        </Typography>
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="flex w-72 flex-col items-center  gap-6">
          <div>
            <span className="p-float-label">
              <Controller
                name="newLogin"
                control={control}
                defaultValue=""
                render={({ field, fieldState }) => (
                  <InputText
                    id="newLogin"
                    value={field.value}
                    onChange={(e) => field.onChange(e.target.value)}
                    className={fieldState.invalid ? 'p-invalid' : ''}
                  />
                )}
              />
              <label htmlFor="newLogin">Username</label>
            </span>
          </div>
          <div>
            <span className="p-float-label">
              <Controller
                name="newPassword"
                control={control}
                defaultValue=""
                render={({ field, fieldState }) => (
                  <Password
                    value={field.value}
                    onChange={(e) => field.onChange(e.target.value)}
                    className={fieldState.invalid ? 'p-invalid' : ''}
                    feedback={false}
                  />
                )}
              />
              <label htmlFor="newPassword">Password</label>
            </span>
          </div>
          <Button type="submit" label="Save" icon="pi pi-check" />
        </form>
      </CardBody>
    </Card>
  )
}
