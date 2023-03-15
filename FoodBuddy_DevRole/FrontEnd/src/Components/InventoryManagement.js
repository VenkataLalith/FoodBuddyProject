import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import axios from 'axios';

export const InventoryManagement = () => {
  const [data, setData] = useState([
  ]);

  const [editIndex, setEditIndex] = useState(-1);
  const [newName, setNewName] = useState("");
  const [newQuantity, setQuantity] = useState("");
  const [newExpiry, setExpiry] = useState(null);
  const [newAmount, setAmount] = useState("");
  const [newGroupCode, setGroupCode] = useState("");

  const handleEdit = (index) => {
    setEditIndex(index);
    setNewName(data[index].name);
    setQuantity(data[index].quantity);
    setExpiry(data[index].expiry);
    setAmount(data[index].amount);
  };

  const handleSave = (index) => {
    const updatedData = [...data];
    updatedData[index] = { id: data[index].id, name: newName, quantity: newQuantity, expiry: newExpiry, amount:newAmount };
    const newGroupCode="99999";
    const formData = {itemName:newName, quantity:newQuantity, expDate:newExpiry, amount:newAmount,groupCode:newGroupCode };
    setData(updatedData);
    callAddItemApi(formData);
    setEditIndex(-1);
    setNewName("");
    setQuantity("");
    setExpiry("");
    setAmount("");
  };
  const callAddItemApi = (formData) => {
      axios.post('/api/v1/inventory/add', formData)
        .then(response => {
          console.log(response);
          alert('Item Added successfully')
        })
        .catch(error => {
          console.log(error);
        });
    };


  const handleCancel = () => {
    setEditIndex(-1);
    setNewName("");
    setQuantity("");
    setExpiry("");
    setAmount("");
  };

  const handleDelete = (index) => {
    const updatedData = data.filter((item) => item.id !== data[index].id);
    setData(updatedData);
  };
  const handleNotification = (index) => {
  const updatedData = [...data];
  updatedData[index] = { id: data[index].id, name: data[index].name, quantity: newQuantity, expiry: newExpiry, amount:newAmount };
  const newGroupCode="99999";
  const formDataNotify = {groupCode:newGroupCode,itemName:data[index].name}
  callSendNotificationApi(formDataNotify);
 };
 const callSendNotificationApi = (formDataNotify) => {
       axios.post("/api/v1/inventory/notify", formDataNotify)
         .then(response => {
           console.log(response);
           alert('Sending in progress')
         })
         .catch(error => {
           console.log(error);
         });
     };



  const handleAdd = () => {
    const newId = data.length + 1;
    const newData = { id: newId, name: "", quantity: "",expiry:"",amount:"" };
    setData([...data, newData]);
    setEditIndex(newId - 1);
  };

  const handleDateSelect = (event) => {
      setExpiry(event.target.value);
    };

  return (
    <div>
      <table>
      <button onClick={handleAdd}>Add Item</button>
        <thead>
          <tr>
            <th>ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Expiry Date</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>
                {editIndex === index ? (
                  <input
                    type="text"
                    value={newName}
                    onChange={(e) => setNewName(e.target.value)}
                  />
                ) : (
                  item.name
                )}
              </td>
              <td>
                {editIndex === index ? (
                  <input
                    type="number"
                    value={newQuantity}
                    onChange={(e) => setQuantity(e.target.value)}
                  />
                ) : (
                  item.quantity
                )}
              </td>
              {/*

              <td>
                  {editIndex === index ? (
                    <input
                      type="text"
                      value={newExpiry}
                      onChange={(e) => setExpiry(e.target.value)}
                    />
                  ) : (
                    item.expiry
                  )}
                </td>*/}
                <td><input type="date" id="date" onChange={handleDateSelect} /></td>
              <td>
                {editIndex === index ? (
                  <input
                    type="text"
                    value={newAmount}
                    onChange={(e) => setAmount(e.target.value)}
                  />
                ) : (
                  item.amount
                )}
              </td>
              <td>
                {editIndex === index ? (
                  <>
                    <button onClick={() => handleSave(index)}>Save</button>
                    <button onClick={handleCancel}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button onClick={() => handleEdit(index)}>Edit</button>
                    <button onClick={() => handleDelete(index)}>Delete</button>
                    <button onClick={() => handleNotification(index)}>Send Notification</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
};

export default InventoryManagement;
