import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {userService} from 'webapp/user/index';

export const getUserList = createAsyncThunk(
    "users/findAll", 
    async () => {
    const response = await userService.findAll()
    return response.data
  })
  export const signup = createAsyncThunk(
    "users/signup",
    async (user) =>{
      alert(`2 user : ` + JSON.stringify(user))
      const response = await userService.signup(user)
      return response.data
    }
  )
  
  const isRejectedAction = action => (action.type.endsWith('rejected'))
  const userSlice  = createSlice({
    name: 'users',
    initialState: [],
    reducers: {},
    extraReducers: (builder) => {
      builder
      .addCase(getUserList.fulfilled, (state, { payload }) =>{
        alert(`3 리덕스 내부 회원 목록 조회 성공 ${ payload }`)
        return [...payload]
      })
      .addCase(signup.fulfilled, (state, {payload}) =>{
        alert(`fdsafdsafdsafdsa: ${payload}`)
        return [...state, payload]
      } )
      .addMatcher(isRejectedAction, () => {})
      .addDefaultCase((state, action) => {})
    }
  })
  
  const { actions, reducer } = userSlice
  export const { } = actions
  export default reducer