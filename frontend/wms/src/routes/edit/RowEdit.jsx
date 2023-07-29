import { Header, Navbar } from '../../components'
import React, { useState } from 'react'
import { Button } from 'primereact/button'
import { Dialog } from 'primereact/dialog'
import { InputText } from 'primereact/inputtext'

export default function RowEdit() {
  const [visible, setVisible] = useState(true)
  const [value, setValue] = useState('');
  const footerContent = (
    <div className='flex justify-end items-center mt-6'>
      <Button
        label="Cancel"
        icon="pi pi-times"
        onClick={() => setVisible(false)}
        className="p-button-text"
      />
      <Button
        label="Save"
        icon="pi pi-save"
        onClick={() => setVisible(false)}
        autoFocus
      />
    </div>
  )

  return (
    <div className="flex h-screen">
      {/* <div className="w-fit">
        <Navbar />
      </div>
      <div className="flex-col w-full h-screen">
        <div>
          <Header />
        </div>
        <div className="flex items-center justify-center">
          <div className="card flex justify-content-center">
            <Button
              label="Show"
              icon="pi pi-external-link"
              onClick={() => setVisible(true)}
            /> */}
            <Dialog
              header="Edit product"
              visible={visible}
              style={{ width: '50vw' }}
              onHide={() => setVisible(false)}
              footer={footerContent}>
              <div className='flex flex-col gap-7 justify-center items-center p-8'>
                <span className='p-float-label'>
                <InputText id='itemName' value={value} onChange={(e) => setValue(e.target.value)}/>
                <label htmlFor="itemName">Product name</label>
                </span>
                <span className='p-float-label'>
                <InputText id='itemPrice' value={value} onChange={(e) => setValue(e.target.value)}/>
                <label htmlFor="itemPrice">Price</label>
                </span>
                <span className='p-float-label'>
                <InputText id='itemDescription' value={value} onChange={(e) => setValue(e.target.value)}/>
                <label htmlFor="Description">Description</label>
                </span>
              </div>
            </Dialog>
          </div>
    //     </div>
    //   </div>
    // </div>
  )
}
