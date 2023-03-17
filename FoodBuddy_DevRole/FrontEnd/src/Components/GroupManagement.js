import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import { updateGroupName } from '../redux/actions/GroupManagementAction';
import { updateGroupNumber } from '../redux/actions/GroupManagementAction';
import React, { useState, useEffect, useLayoutEffect } from 'react';
import { DisplayGroupDetails } from './DisplayGroupDetails'
import Layout from './Layout';
import { Login } from './Login';

export const GroupManagement = () => {
  const [groupName, setGroupName] = useState("");
  const [groupCode, setGroupCode] = useState("");
  const [joinCode, setJoinCode] = useState("");
  const[displayCreateGroup, setdisplayCreateGroup] = useState(false);
  const[displayJoinGroup, setdisplayJoinGroup] = useState(false);
  const [formSubmitted, setFormSubmitted] = useState(false);
  const [isUserPartOfGroup, setIsUserPartOfGroup] = useState()
  const dispatch = useDispatch();
  const userName = useSelector((state) => state.loginLogoutReducer.emailId);
  const userGroupName = useSelector((state) => state.groupManagementReducer.groupName);
  const userGroupNumber = useSelector((state) => state.groupManagementReducer.groupCode);
  const userGroupCode="";

  const navigate = useNavigate();
  const createGroup = (event) => {

        event.preventDefault();
            console.log(`Creating the group with: ${groupName},and password: ${groupCode}`);

            setFormSubmitted(true);
            setGroupName(groupName);
            setGroupCode(groupCode);

            const formData ={
                    groupName: groupName,
                    groupCode: groupCode,
                    userName: userName
                    };
            callCreateGroupApi(formData);
            setdisplayCreateGroup(false)
            setdisplayJoinGroup(false)

  }
  const callCreateGroupApi = (formData) => {
       axios.post('/api/v1/groupApi/Create', formData)
         .then(response => {
           console.log(response);
           if(response.data.message==="Group created successfully"){
                dispatch(updateGroupNumber(groupCode))
                alert(`${groupName} Group created successfully with code`)
                navigate('/home')
           }
         })
         .catch(error => {
           console.log(error);
         });
     };

  const joinGroup = (event) => {
        event.preventDefault();
            console.log(`Joining the group with: ${joinCode}`);
            setFormSubmitted(true);
            setJoinCode(joinCode);
            const formDataJoin ={
                        groupCode: joinCode,
                        userName: userName
                        };
            callJoinGroupApi(formDataJoin);
    }

    const callJoinGroupApi = (formDataJoin) => {
           axios.post('/api/v1/groupApi/Join', formDataJoin)

             .then(response => {
              console.log(response)
               if(response.data.message==="Joined successfully"){
                    dispatch(updateGroupNumber(joinCode))
                    alert('User joined successfully')
                     setdisplayCreateGroup(false)
                     setdisplayJoinGroup(false)
                     navigate('/home')
               }

             })
             .catch(error => {
               console.log(error);
             });
         };

const handleLeaveGroup = () => {
  alert('User left successfully')
  userGroupCode=""
}

  const submitCreate = () => {
    setdisplayCreateGroup(true);
    setdisplayJoinGroup(false);
  }

  const submitJoin = () => {
    setdisplayJoinGroup(true);
    setdisplayCreateGroup(false);
  }

  const CreateJoinFunctionality = () => {

    useEffect(() => {
     if(userName===""){
      navigate('/')
     }
    });

    console.log('User group code '+userGroupNumber)
    return(
      <div>
        <div className='centerit'>
    <button  style={{  marginTop:" 4%", marginRight: "33px"}} onClick={submitCreate}> Create a Group</button>
    <button className='input1' onClick={submitJoin}> Join a Group</button>
    </div>
            
            { displayCreateGroup && (
            <div class="center" style={{marginLeft:"25%"}}>
            <form>
                <h2> Create a Group</h2>
                <label> Group Name:
              <input style={{marginLeft: "15px", marginBottom:"10px"}} type="input" placeholder='Enter group name' value={groupName} onChange={(e) => setGroupName(e.target.value)} />
              </label>
                 <br />
                <label> Group Code:
            <input style={{marginLeft: "15px", marginBottom:"10px"}} placeholder="Enter group code" type="input" value={groupCode} onChange={(e) => setGroupCode(e.target.value)} />
            </label>
              <br />
              <button  type="submit" onClick={createGroup}>Create Group</button>
              <button style={{marginLeft: "5%"}} onClick={() => setdisplayCreateGroup(false)}>Close</button>
            </form>
            {formSubmitted && (
             <div> <p>Group created successfully!</p> </div> )}
            </div>
)}
            { displayJoinGroup && (
            <div className='center' style={{marginLeft:"25%"}}>
            <form>
                <h2> Join a Group</h2>
                <label> Group Code:
              <input  style={{marginLeft: "15px", marginBottom:"10px"}} type="input" placeholder='Enter the group code ' value={joinCode} onChange={(e) => setJoinCode(e.target.value)} />
              </label>
                 <br />
             
              <button type="submit" onClick={joinGroup}>Join Group</button>
              <button  style={{marginLeft: "5%"}} onClick={() => setdisplayCreateGroup(false)}>Close</button>
            </form>
            {formSubmitted && (
             <div> <p>Group joined successfully!</p> </div> )}
            </div>
)}
      </div>
    )
  }

  const DisplayGroupManagementFunctionality = () => {
    return(
      <div>

      {/* {(displayCreateGroup || displayJoinGroup) && (
  <div onClick={closeForms}  ></div>
)} */}
<Layout />

{(userGroupNumber==="" || userGroupNumber===null)?<CreateJoinFunctionality/>:<div><DisplayGroupDetails/><button onClick={handleLeaveGroup}>Leave Group</button></div>}
  </div>
    )
  }


    return(
       <div>

        {(userName==="")?<div></div>:<DisplayGroupManagementFunctionality/>}
        </div>
    )
}