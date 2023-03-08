import React, { useState } from 'react';
export const GroupManagement = () => {
  const [groupName, setGroupName] = useState("");
  const [groupCode, setGroupCode] = useState("");
  const [joinCode, setJoinCode] = useState("");
  const[displayCreateGroup, setdisplayCreateGroup] = useState(false);
  const[displayJoinGroup, setdisplayJoinGroup] = useState(false);
  const [formSubmitted, setFormSubmitted] = useState(false);

  const createGroup = (event) => {
    event.preventDefault();
    console.log(`Creating the group with: ${groupName},and password: ${groupCode}`);
    setFormSubmitted(true);
    setGroupName("");
    setGroupCode("");
  }

  const joinGroup = (event) => {
    event.preventDefault();
    console.log(`Joining the group with: ${joinCode}`);
    setFormSubmitted(true);
    setJoinCode("");
  }

  const submitCreate = () => {
    setdisplayCreateGroup(true);
    setdisplayJoinGroup(false);
  }

  const submitJoin = () => {
    setdisplayJoinGroup(true);
    setdisplayCreateGroup(false);
  }

//   const closeForms = () =>{
//     setdisplayCreateGroup(false);
//     setdisplayJoinGroup(false);
//   }

  
    return(
        <div>
            {/* <div>
      <nav style={{ display: 'flex', justifyContent: 'flex-start', alignItems: 'center', backgroundColor: 'rgb(162, 162, 244)', margin: 0, padding: '1rem' }}>
       
        <ul style={{ listStyle: 'none', display: 'flex', gap: '1rem', margin: 0, padding: 0 }}>
          <li><a href="/">Home</a></li>
          <li><a href="/">Grocery Management</a></li>
          
        </ul>
      </nav>
      <main style={{ height: '100%', marginBottom: '2rem', display: 'flex' }}>
      
      </main>
      <footer style={{ backgroundColor: 'rgb(162, 162, 244)', margin: 0, padding: '1rem' }}>
       
        <p>FoodBuddy App</p>
      </footer>
    </div> */}
    <div className='centerit'>
    <button style={{ marginLeft:"639px", marginTop:" 4%", marginRight: "33px"}} onClick={submitCreate}> Create a Group</button>
            <button className='input1'  onClick={submitJoin}> Join a Group</button>
    </div>
            
            { displayCreateGroup && (
            <div class="center" style={{marginLeft:"57%"}}>
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
              <button type="submit" onClick={createGroup}>Create Group</button>
              <button style={{marginLeft: "5%"}} onClick={() => setdisplayCreateGroup(false)}>Close</button>
            </form>
            {formSubmitted && (
             <div> <p>Group created successfully!</p> </div> )}
            </div>
)}
            { displayJoinGroup && (
            <div className='center' style={{marginLeft:"57%"}}>
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
            {/* {(displayCreateGroup || displayJoinGroup) && (
        <div onClick={closeForms}  ></div>
      )} */}
        </div>
            
    )
}