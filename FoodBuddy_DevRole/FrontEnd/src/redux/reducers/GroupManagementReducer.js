
const defaultstate = {
        groupName:"",
        groupCode:""
}

export const GroupManagementReducer = (state = defaultstate ,action) => {

    switch(action.type){
        case 'GROUP_CREATION_ACTION' :
            return {
                groupName : action.payload
            }

        default : return state
     }

}