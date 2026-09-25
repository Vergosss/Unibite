import {Routes, Route} from "react-router-dom";
import Login from "../Login";
import Signup from "../Signup";
import Postings from "../Postings/Postings"
function Navigation(){

    return (
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/signup" element={<Signup/>}/>
            <Route path="/home" element={<Postings/>}/>
        </Routes>
    );
}
export default Navigation;