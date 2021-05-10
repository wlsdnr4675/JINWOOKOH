import {React} from 'react';
import {Route} from 'react-router-dom'
import NavBar from 'webapp/common/components/NavBar';
import { Signin, Signup, UserBoard, UserEdit, UserRead } from 'webapp/user/index';

const App = () =>{
  return(<>
  <NavBar/>
    <Route path="/user/signin" component={Signin}></Route>
    <Route path="/user/signup" component={Signup}></Route>
    <Route path="/user/user_board" component={UserBoard}></Route>
    <Route path="/user/user_edit" component={UserEdit}></Route>
    <Route path="/user/user_read" component={UserRead}></Route>
  
  </>)
}
export default App;
