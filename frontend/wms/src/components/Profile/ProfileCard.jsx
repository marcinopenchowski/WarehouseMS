import React, { useEffect, useState, useRef } from 'react'
import { useNavigate } from 'react-router-dom'
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
import { Toast } from 'primereact/toast'

export default function ProfileCard() {
  const toast = useRef(null)
  const [token] = useState(getToken())
  const [username, setUsername] = useState('')
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm()
  const navigate = useNavigate()

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

  const showSuccess = () => {
    toast.current.show({
      severity: 'success',
      summary: 'Success!',
      detail: 'Account details changed successfuly!',
      life: 2000,
    })
  }

  const showError = () => {
    toast.current.show({
      severity: 'error',
      summary: 'Error',
      detail: 'Something went wrong!',
      life: 2000,
    })
  }

  const onSubmit = async (data) => {
    try {
      const updateEndpoint = '/updateUser'
      await api.post(updateEndpoint, data)
      const logoutRes = await api.post('/logout')
      if (logoutRes.status === 200) {
        localStorage.removeItem('jwtToken')
        showSuccess()
        setTimeout(() => {
          navigate('/login')
        }, 2000)
      } else {
        showError()
      }
    } catch (error) {
      showError()
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
        <Typography variant="h5" color="blue-gray" className="mb-2">
          Hello {username}!
        </Typography>
        <Typography variant="h6" className="mb-10">
          Change your account details!
        </Typography>
        <Toast ref={toast} />
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
