import React, { useState } from 'react';

export const GroupManagement = () => 
{
  const [groupName, setGroupName] = useState("");
  const [groupCode, setGroupCode] = useState("");
  const [joinCode, setJoinCode] = useState("");
  const [displayCreateGroup, setdisplayCreateGroup] = useState(false);
  const [displayJoinGroup, setdisplayJoinGroup] = useState(false);
  const [formSubmitted, setFormSubmitted] = useState(false);
  const [groupNameError, setGroupNameError] = useState(false);
  const [groupCodeError, setGroupCodeError] = useState(false);
  const [joinCodeError, setJoinCodeError] = useState(false);

  const createGroup = (event) => 
  {
    event.preventDefault();
    if (!groupName.trim()) {
      setGroupNameError(true);
      return;
    }
    if (!groupCode.trim()) {
      setGroupCodeError(true);
      return;
    }
    console.log(`Creating the group with: ${groupName}, and password: ${groupCode}`);
    setFormSubmitted(true);
    setGroupName("");
    setGroupCode("");
 }

  const joinGroup = (event) => {
    event.preventDefault();
    if (!joinCode.trim()) {
      setJoinCodeError(true);
      return;
    }
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

  return (
    <div>
      <div>
        <nav style={{ display: 'flex', justifyContent: 'flex-start', alignItems: 'center', backgroundColor: 'rgb(162, 162, 244)', margin: 0, padding: '1rem' }}>
          {/* Navigation bar content */}
          <ul style={{ listStyle: 'none', display: 'flex', gap: '1rem', margin: 0, padding: 0 }}>
            <li><a href="/">Home</a></li>
            <li><a href="/">Grocery Management</a></li>
            {/* <li><a href="/register">Budget</a></li> */}
          </ul>
        </nav>
        <main style={{ height: '100%', marginBottom: '2rem', display: 'flex' }}>
          {/* {children} */}
        </main>
        <footer style={{ backgroundColor: 'rgb(162, 162, 244)', margin: 0, padding: '1rem' }}>
          {/* Footer content */}
          <p>FoodBuddy App</p>
        </footer>
      </div>
      <button onClick={submitCreate}> Create a Group</button>
      <button style={{ marginLeft: "5%" }} onClick={submitJoin}> Join a Group</button>

      {displayCreateGroup && (
        <div className="center">
          <form>
            <h2> Create a Group</h2>
            <label>
              Group Name:
              <input style={{ marginLeft: "15px", marginBottom: "10px" }} type="input" placeholder='Enter group name' value={groupName} onChange={(e) => { setGroupName(e.target.value); setGroupNameError(false); }} />
              {groupNameError && <div style={{ color: "red"}}>
              Please enter a group name
        </div>}
            </label>
            <label>
            Password:
            <input style={{ marginLeft: "30px", marginBottom: "10px" }} type="password" placeholder='Enter group password' value={groupCode} onChange={(e) => { setGroupCode(e.target.value); setGroupCodeError(false); }} />
            {groupCodeError && <div style={{ color: "red" }}>Please enter a group password</div>}
            </label>
            <button style={{ marginTop: "10px" }} type="submit" onClick={createGroup}>Create Group</button>
            </form>
            </div>
            )}
              {displayJoinGroup && (
    <div className="center">
      <form>
        <h2>Join a Group</h2>
        <label>
          Group Code:
          <input style={{ marginLeft: "15px", marginBottom: "10px" }} type="input" placeholder='Enter group code' value={joinCode} onChange={(e) => { setJoinCode(e.target.value); setJoinCodeError(false); }} />
          {joinCodeError && <div style={{ color: "red" }}>Please enter a group code</div>}
        </label>
        <button style={{ marginTop: "10px" }} type="submit" onClick={joinGroup}>Join Group</button>
      </form>
    </div>
  )}

    </div>
  )
 }
