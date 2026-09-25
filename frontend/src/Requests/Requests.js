import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table';
import { useState, useEffect } from "react";
import axios from 'axios';
import {MdCancel} from "react-icons/md";
import { SiTicktick } from "react-icons/si";
import ConfirmationModal from "./ConfirmationModal";
function Requests(){

    const details = ["requesterId","postingId"];
    const [myRequests,setMyRequests] = useState([]); //myrequests is an array of request objects
    const [show,setShow] = useState(0);
    const [type,setType] = useState("");
    useEffect(()=>{
        console.log('First render after unmount!');
        //fetch my requests load them into the table to initialize
        const getRequests = async function(){
            try{
            const response = await axios.get("http://localhost:5000/requests");
            setMyRequests(response.data);
            }
            catch(error){
                console.log(error);
            }


        }
        getRequests();
        //create a SSE connection
        const eventSource = new EventSource("http://localhost:5000/events/requests");
        eventSource.onopen = ()=>{console.log("Opened");}
        eventSource.onmessage = (event)=>{
        console.log('How many times');
        console.log(event.data);
        //need parsing
        const update = JSON.parse(event.data);
        setMyRequests((old)=>[...old,update]);//witout old i have stale state issues!
        } 


        //cleanup. when the component unmounts close the connection
        return ()=>{
            eventSource.close();
        }
    },[]);
//

function approveReject(type){
    setShow(0); // close panel
    type === "Approve" ? console.log('Approving Request and reducing portions...') : console.log('Cancelling request and freeing portion');
}




    //
    return (
    <div style={{width: "600px" }} className='position-absolute top-50 start-50 translate-middle'>
    <Table responsive='lg' striped hover style={{ height: "100%", width: "100%" }}> 
        <thead>
        <tr key="head">
        {details.map(detail=><th key={detail}>{detail}</th>)}
        <th>Approve</th>
        <th>Reject</th>

        </tr>
      </thead>
      <tbody>
        {myRequests.map((request)=><tr key={request.id}>{details.map(detail=><td>{request[detail]}</td>)}<td><SiTicktick style={{color:"green",cursor:"pointer"}} onClick={()=>{setShow(1);setType("Approve")}} /></td><td><MdCancel style={{color:"red",cursor:"pointer"}} onClick={()=>{setShow(1);setType("Reject")}}/></td></tr>)}
      </tbody>
        </Table>
    <ConfirmationModal show={show} type={type} onClose={()=>{setShow(0)}} onClick={approveReject}/>
    </div>

    );


}

export default Requests;