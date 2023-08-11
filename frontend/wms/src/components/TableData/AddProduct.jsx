import React, { useRef, useState, useEffect, useContext } from 'react'
import { Button } from 'primereact/button'
import { Dialog } from 'primereact/dialog'
import { InputText } from 'primereact/inputtext'
import { Toast } from 'primereact/toast'
import { Dropdown } from 'primereact/dropdown'
import { Calendar } from 'primereact/calendar'
import { useForm, Controller } from 'react-hook-form'
import { classNames } from 'primereact/utils'
import api from '../../api/axiosInstance'
import { getToken } from '../../utils/auth'
import { ProductContext } from '../../contexts/ProductContext'

export default function AddProduct({ isOpen, setIsOpen }) {
  const types = [
    { name: 'asset', code: 'Asset' },
    { name: 'software', code: 'Software' },
    { name: 'accessory', code: 'Accessory' },
  ]

  const { products, setProducts } = useContext(ProductContext)

  const {
    control,
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm()

  const token = getToken()
  const toast = useRef(null)
  // const [selectedOwner, setSelectedOwner] = useState(null)
  // const [selectedCategory, setSelectedCategory] = useState(null)
  const [selectedType, setSelectedType] = useState(null)
  const [date, setDate] = useState(null)
  const [owner, setOwner] = useState([])
  const [category, setCategory] = useState([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const ownerRes = await api.get('/owner')
        const categoryRes = await api.get('/category')
        setOwner(ownerRes.data)
        setCategory(categoryRes.data)
      } catch (error) {
        console.error('Error fetching data:', error)
      }
    }
    fetchData()
  }, [])

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
      const type = selectedType ? selectedType.name : ''
      const endpoint = `/${type}`
      console.log(data)
      const response = await api.post(endpoint, data)

      const newProduct = response.data

      setProducts([...products, newProduct])

      showSuccess()
    } catch (error) {
      showError()
    } finally {
      reset()
    }
  }

  const getFormErrorMessage = (name) => {
    return (
      errors[name] && <small className="p-error">{errors[name].message}</small>
    )
  }

  return (
    <Dialog
      header="Add product"
      visible={isOpen}
      style={{ width: '50vw' }}
      onHide={() => setIsOpen(false)}>
      <div className="flex flex-col justify-center items-center p-2">
        <p className="m-8">What product do you want to add?</p>
        <span className="p-float-label">
          <Dropdown
            inputId="type"
            value={selectedType}
            onChange={(e) => {
              setSelectedType(e.value)
            }}
            options={types.map((type) => ({
              value: type,
              label: `${type.code}`,
            }))}
            optionLabel="label"
            placeholder="Select product type"
            className="w-full"
          />
          <label htmlFor="type">Product Type</label>
        </span>
      </div>
      <form
        onSubmit={handleSubmit(onSubmit)}
        noValidate
        className="flex flex-col gap-7 justify-center items-center p-8">
        <Toast ref={toast} />
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="name"
              control={control}
              rules={{ required: 'Name is required' }}
              render={({ field, fieldState }) => (
                <InputText
                  id={field.name}
                  {...field}
                  autoFocus
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                />
              )}
            />
            <label
              htmlFor="name"
              className={classNames({ 'p-error': errors.name })}>
              Product name
            </label>
          </span>
          {getFormErrorMessage('name')}
        </div>
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="price"
              control={control}
              rules={{
                required: 'Price cannot be empty',
                min: {
                  value: 0,
                  message: 'Price cannot be less than 0',
                },
                pattern: {
                  value: /^[0-9]*$/,
                  message: 'Please enter only numbers',
                },
              }}
              render={({ field, fieldState }) => (
                <InputText
                  id={field.price}
                  {...field}
                  type="text"
                  autoFocus
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                />
              )}
            />
            <label
              htmlFor="price"
              className={classNames({ 'p-error': errors.price })}>
              Price
            </label>
          </span>
          {getFormErrorMessage('price')}
        </div>
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="description"
              control={control}
              rules={{
                required: 'Description cannot be empty',
                minLength: 10,
                maxLength: 100,
              }}
              render={({ field, fieldState }) => (
                <InputText
                  id={field.description}
                  {...field}
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                />
              )}
            />
            <label
              htmlFor="description"
              className={classNames({ 'p-error': errors.description })}>
              Description
            </label>
          </span>
          {getFormErrorMessage('description')}
        </div>
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="purchase_value"
              control={control}
              rules={{
                required: 'Purchase value cannot be empty',
                pattern: {
                  value: /^[0-9]*$/,
                  message: 'Please enter only numbers',
                },
              }}
              render={({ field, fieldState }) => (
                <InputText
                  id={field.purchase_value}
                  {...field}
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                  type="text"
                />
              )}
            />
            <label
              htmlFor="purchase_value"
              className={classNames({ 'p-error': errors.purchase_value })}>
              Purchase value
            </label>
          </span>
          {getFormErrorMessage('purchase_value')}
        </div>
        <div className="field w-1/2">
          <Calendar
            value={date}
            id="purchase_date"
            onChange={(e) => setDate(e.value)}
            showIcon
            className="w-full"
            {...register('purchase_date')}
            dateFormat="yy/mm/dd"
            placeholder="Purchase Date"
          />
        </div>
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="owner"
              control={control}
              rules={{ required: 'Product owner is required' }}
              render={({ field, fieldState }) => (
                <Dropdown
                  inputId="owner"
                  {...field}
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                  options={owner.map((option) => ({
                    value: option,
                    label: `${option.firstName} ${option.lastName}`,
                  }))}
                  optionLabel="label"
                />
              )}
            />
            <label
              htmlFor="owner"
              className={classNames({ 'p-error': errors.owner })}>
              Product Owner
            </label>
          </span>
          {getFormErrorMessage('owner_id')}
        </div>
        <div className="field w-1/2">
          <span className="p-float-label">
            <Controller
              name="category"
              control={control}
              rules={{ required: 'Product category is required' }}
              render={({ field, fieldState }) => (
                <Dropdown
                  inputId="category"
                  {...field}
                  className={fieldState.invalid ? 'p-invalid w-full' : 'w-full'}
                  options={category.map((option) => ({
                    value: option,
                    label: `${option.name}`,
                  }))}
                  optionLabel="label"
                />
              )}
            />
            <label
              htmlFor="category"
              className={classNames({ 'p-error': errors.owner })}>
              Product category
            </label>
          </span>
          {getFormErrorMessage('category_id')}
        </div>
        <div className="flex justify-end items-center mt-6">
          <Button
            type="button"
            label="Cancel"
            icon="pi pi-times"
            onClick={() => setIsOpen(false)}
            className="p-button-text"
          />
          <Button type="submit" label="Save" icon="pi pi-save" autoFocus />
        </div>
      </form>
    </Dialog>
  )
}
