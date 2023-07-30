import React, { useRef, useState, useEffect } from 'react'
import { Button } from 'primereact/button'
import { Dialog } from 'primereact/dialog'
import { InputText } from 'primereact/inputtext'
import { Toast } from 'primereact/toast'
import { Dropdown } from 'primereact/dropdown'
import { Calendar } from 'primereact/calendar'
import { useForm, Controller } from 'react-hook-form'
import axios from 'axios'
import { classNames } from 'primereact/utils'

export default function AddProduct({ isOpen, setIsOpen }) {
  const types = [
    { name: 'asset', code: 'Asset' },
    { name: 'software', code: 'Software' },
    { name: 'accessory', code: 'Accessory' },
  ]

  const {
    control,
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm()

  const toast = useRef(null)
  const [selectedOwner, setSelectedOwner] = useState(null)
  const [selectedCategory, setSelectedCategory] = useState(null)
  const [selectedType, setSelectedType] = useState(null)
  const [date, setDate] = useState(null)
  const [owner, setOwner] = useState([])
  const [category, setCategory] = useState([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const ownerRes = await axios.get('http://localhost:8080/owner')
        const categoryRes = await axios.get('http://localhost:8080/category')
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
      const endpoint = `http://localhost:8080/${type}`
      await axios.post(endpoint, data)
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
                  className={
                    (classNames({ 'p-invalid': fieldState.invalid }), 'w-full')
                  }
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
              }}
              render={({ field, fieldState }) => (
                <InputText
                  id={field.price}
                  {...field}
                  type="number"
                  autoFocus
                  className={
                    (classNames({ 'p-invalid': fieldState.invalid }), 'w-full')
                  }
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
          <span className="p-float-label w-1/2">
            <InputText
              {...register('description')}
              id="description"
              className="w-full"
            />
            <label htmlFor="description">Description</label>
          </span>
        </div>
        <span className="p-float-label w-1/2">
          <InputText
            {...register('purchase_value')}
            id="purchase_value"
            className="w-full"
          />
          <label htmlFor="purchase_value">Purchase value</label>
        </span>
        <Calendar
          value={date}
          id="purchase_date"
          onChange={(e) => setDate(e.value)}
          showIcon
          className="w-1/2"
          {...register('purchase_date')}
          dateFormat="yy/mm/dd"
          placeholder="Purchase Date"
        />
        <span className="p-float-label w-1/2">
          <Dropdown
            inputId="owner"
            value={selectedOwner}
            onChange={(e) => setSelectedOwner(e.value)}
            options={owner.map((option) => ({
              value: option.id,
              label: `${option.firstName} ${option.lastName}`,
            }))}
            optionLabel="label"
            placeholder="Select product owner"
            className="w-full"
            {...register('owner', {
              required: 'Product owner is required',
            })}
          />
          <label htmlFor="owner">Product Owner</label>
          {errors.owner && (
            <span className="text-red-500 text-sm">{errors.owner.message}</span>
          )}
        </span>
        <span className="p-float-label w-1/2">
          <Dropdown
            inputId="category"
            value={selectedCategory}
            onChange={(e) => setSelectedCategory(e.value)}
            options={category.map((option) => ({
              value: option.id,
              label: `${option.name}`,
            }))}
            optionLabel="label"
            placeholder="Select product category"
            className="w-full"
            {...register('category', {
              required: 'Product category is required',
            })}
          />
          <label htmlFor="category">Product category</label>
          {errors.category && (
            <span className="text-red-500 text-sm">
              {errors.category.message}
            </span>
          )}
        </span>
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
