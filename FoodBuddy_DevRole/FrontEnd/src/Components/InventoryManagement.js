import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';

export const InventoryManagement = () => {
  const [data, setData] = useState([
    { id: 1, name: "", quantity: 0, expiry: "", amount :0 },

  ]);

  const [editIndex, setEditIndex] = useState(-1);
  const [newName, setNewName] = useState("");
  const [newQuantity, setQuantity] = useState("");
  const [newExpiry, setExpiry] = useState(null);
  const [newAmount, setAmount] = useState("");

  const handleEdit = (index) => {
    setEditIndex(index);
    setNewName(data[index].name);
    setQuantity(data[index].quantity);
    setExpiry(data[index].expiry);
    setAmount(data[index].amount);
  };
  const userGroupNumber = useSelector((state) => state.groupManagementReducer.groupCode);
  const handleSave = (index) => {
    const updatedData = [...data];
    updatedData[index] = { id: data[index].id, name: newName, quantity: newQuantity, expiry: newExpiry, amount:newAmount };
    setData(updatedData);
    setEditIndex(-1);
    setNewName("");
    setQuantity("");
    setExpiry("");
    setAmount("");
   // if(userGroupNumber != null){}
    const formData ={
      itemName: newName,
      quantity: newQuantity,
      expDate: newExpiry,
      groupCode: userGroupNumber
      };
      console.log(formData);
      saveItem(formData);
  };

  const saveItem = (formData) => {
    axios.post('/api/v1/inventory/add', formData)
      .then(response => {
        if(response.data.status==='success'){
           console.log('Item added')
          //  
          
        
        }
        else{
           alert('')
        }
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
