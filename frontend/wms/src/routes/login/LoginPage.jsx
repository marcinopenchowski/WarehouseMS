import React, { useRef, useState } from 'react'
import { Controller, useForm } from 'react-hook-form'
import { InputText } from 'primereact/inputtext'
import { Password } from 'primereact/password'
import { Toast } from 'primereact/toast'
import { Button } from 'primereact/button'
import * as yup from 'yup'
import { yupResolver } from '@hookform/resolvers/yup'
import api from '../../api/axiosInstance'

export default function LoginPage() {
  const toast = useRef(null)
  const [setToken] = useState(null)

  const validationSchema = yup.object().shape({
    password: yup.string().required('Password is required'),
    login: yup.string().required('Username is required'),
  })

  const {
    control,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationSchema),
  })

  const showErrorToast = () => {
    toast.current.show({
      severity: 'error',
      summary: 'Error',
      detail: 'Something went wrong!',
      life: 3000,
    })
  }

  const onSubmit = async (data) => {
    try {
      console.log(data)
      const endpoint = '/login'
      const res = await api.post(endpoint, data)
      const { token } = res.data
      setToken(token)
      localStorage.setItem('jwtToken', token)
      console.log(token)
    } catch (error) {
      showErrorToast()
    } finally {
      reset()
    }
  }

  return (
    <div className="w-full h-screen flex bg-gray-100">
      <Toast ref={toast} />
      <div className="grid grid-cols-1 md:grid-cols-2 m-auto h-[550px] shadow-lg shadow-gray-500 sm:max-w-[800px]">
        <div className="w-full h-[550px] hidden md:block">
          <img
            className="w-full h-full"
            src="/assets/images/login.jpg"
            alt="/"
          />
        </div>
        <div className="p-4 flex flex-col justify-around bg-white">
          <form
            onSubmit={handleSubmit(onSubmit)}
            className="flex flex-col gap-7 items-center">
            <h2 className="text-4xl font-bold text-center mb-8">WarehouseMS</h2>
            <div>
              <span className="p-float-label">
                <Controller
                  name="login"
                  control={control}
                  defaultValue=""
                  render={({ field, fieldState }) => (
                    <InputText
                      id="login"
                      value={field.value}
                      onChange={(e) => field.onChange(e.target.value)}
                      className={fieldState.invalid ? 'p-invalid' : ''}
                    />
                  )}
                />
                <label htmlFor="login">Username</label>
              </span>
            </div>
            <div>
              <span className="p-float-label">
                <Controller
                  name="password"
                  control={control}
                  render={({ field, fieldState }) => (
                    <Password
                      value={field.value}
                      onChange={(e) => field.onChange(e.target.value)}
                      className={fieldState.invalid ? 'p-invalid' : ''}
                      feedback={false}
                    />
                  )}
                />
                <label htmlFor="password">Password</label>
              </span>
            </div>
            <Button type="submit" label="Log In" icon="pi pi-check" />
          </form>
          <ul className="text-center">
            {errors.login && (
              <li className="error-message text-red-500">
                {errors.login.message}
              </li>
            )}
            {errors.password && (
              <li className="error-message text-red-500">
                {errors.password.message}
              </li>
            )}
          </ul>
          <p className="text-center">
            Don't have an account?
            <a
              href="/register"
              className="font-medium text-yellow-500 dark:text-yellow-500 hover:underline">
              {' '}
              Sign Up
            </a>
          </p>
        </div>
      </div>
    </div>
  )
}
