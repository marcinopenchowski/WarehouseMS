import React, { useRef } from 'react'
import { InputText } from 'primereact/inputtext'
import { Password } from 'primereact/password'
import { Divider } from 'primereact/divider'
import { Toast } from 'primereact/toast'
import { Button } from 'primereact/button'
import * as yup from 'yup'
import { yupResolver } from '@hookform/resolvers/yup'
import { useForm, Controller } from 'react-hook-form'
import api from '../../api/axiosInstance'

export default function RegisterForm() {
  const toast = useRef(null)

  const validationSchema = yup.object().shape({
    password: yup
      .string()
      .required('Password is required')
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,
        'Password must contain at least one lowercase, one uppercase, one numeric, and be at least 8 characters long'
      ),
    username: yup.string().required('Username is required'),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref('password'), null], 'Passwords must match')
      .required('Confirm password is required'),
  })

  const {
    control,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationSchema),
  })

  const showSuccess = () => {
    toast.current.show({
      severity: 'success',
      summary: 'Form Submitted',
      detail: 'The form data has been submitted successfully!',
      life: 3000,
    })
  }

  const showError = () => {
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
      const { username, password } = data
      const requestData = { username, password }
      console.log(requestData)
      const endpoint = `/register`
      await api.post(endpoint, requestData)
      showSuccess()
    } catch (error) {
      showError()
    } finally {
      reset()
    }
  }

  const header = <div className="font-bold mb-3">Pick a password</div>
  const footer = (
    <>
      <Divider />
      <p className="mt-2">Suggestions</p>
      <ul className="pl-2 ml-2 mt-0 line-height-3">
        <li>At least one lowercase</li>
        <li>At least one uppercase</li>
        <li>At least one numeric</li>
        <li>Minimum 8 characters</li>
      </ul>
    </>
  )

  return (
    <div className="w-full h-screen flex bg-gray-100">
      <Toast ref={toast} />
      <div className="grid grid-cols-1 md:grid-cols-2 m-auto h-[550px] shadow-lg shadow-gray-500 sm:max-w-[800px]">
        <div className="w-full h-[550px] hidden md:block">
          <img
            className="w-full h-full"
            src="/assets/images/register.jpg"
            alt="/"
          />
        </div>
        <div className="flex flex-col gap-7 items-center bg-white">
          <h2 className="text-4xl font-bold text-center my-8">WarehouseMS</h2>
          <form
            onSubmit={handleSubmit(onSubmit)}
            className="flex flex-col gap-7 items-center">
            <div className="mx-5">
              <span className="p-float-label">
                <Controller
                  name="username"
                  control={control}
                  defaultValue=""
                  render={({ field, fieldState }) => (
                    <InputText
                      id="username"
                      value={field.value}
                      onChange={(e) => field.onChange(e.target.value)}
                      className={fieldState.invalid ? 'p-invalid' : ''}
                    />
                  )}
                />
                <label htmlFor="username">Username</label>
              </span>
            </div>

            <div className="mx-5">
              <span className="p-float-label">
                <Controller
                  name="password"
                  control={control}
                  defaultValue=""
                  render={({ field, fieldState }) => (
                    <Password
                      value={field.value}
                      onChange={(e) => field.onChange(e.target.value)}
                      header={header}
                      footer={footer}
                      className={fieldState.invalid ? 'p-invalid' : ''}
                    />
                  )}
                />
                <label htmlFor="password">Password</label>
              </span>
            </div>

            <div className="mx-5">
              <span className="p-float-label">
                <Controller
                  name="confirmPassword"
                  control={control}
                  defaultValue=""
                  render={({ field, fieldState }) => (
                    <Password
                      inputId="confirmPassword"
                      value={field.value}
                      onChange={(e) => {
                        field.onChange(e.target.value)
                      }}
                      header="Confirm your password"
                      className={fieldState.invalid ? 'p-invalid' : ''}
                      feedback={false}
                    />
                  )}
                />
                <label htmlFor="password">Confirm password</label>
              </span>
            </div>

            <ul>
              {errors.username && (
                <li className="error-message text-red-500">
                  {errors.username.message}
                </li>
              )}
              {errors.password && (
                <li className="error-message text-red-500">
                  {errors.password.message}
                </li>
              )}
              {errors.confirmPassword && (
                <li className="error-message text-red-500">
                  {errors.confirmPassword.message}
                </li>
              )}
            </ul>
            <Button label="Submit" type="submit" icon="pi pi-check" />
          </form>
        </div>
      </div>
    </div>
  )
}
