import React from 'react';
import {Link} from 'react-router-dom'

const NavBar = () => {
    return (<>
    <div className="container">
        <ul>
        <li><Link to={"/user/signup"}><button>SignUp</button></Link></li>
        <li><Link to={"/user/signin"}><button>SignIn</button></Link></li>
        <li><Link to={"/user/user_board"}><button>UserBoard</button></Link></li>
        </ul>
      
      

      </div>
    </>);
}
 
export default NavBar;