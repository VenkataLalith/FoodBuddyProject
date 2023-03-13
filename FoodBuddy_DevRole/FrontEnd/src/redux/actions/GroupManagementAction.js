export const updateGroupName = (groupName) => {
    return{
        type:"GROUP_CREATION_ACTION",
        payload : groupName
    }
}

export const updateGroupNumber = (groupCode) => {
    return{
        type:"GROUP_JOIN_ACTION",
        payload : groupCode
    }
}