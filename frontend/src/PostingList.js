import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table';
import { useState } from 'react';
import PostingModal from './PostingModal';
import { BsFillPencilFill,BsFillTrash3Fill  } from "react-icons/bs";
//
function PostingList({postings,addPosting,deletePosting,updatePosting}){
const details = ['title','notes','portions'];
const [add,setAdd] = useState(false);
const [mode,setMode] = useState(0);
const [editData,setEditData] = useState({});
//
//
    return (
        <div style={{width: "600px" }} className='position-absolute top-50 start-50 translate-middle'>
        <Table responsive='lg' striped hover style={{ height: "100%", width: "100%" }}> 
        <thead>
        <tr>
        {details.map(detail=><th>{detail}</th>)}
        </tr>
      </thead>
      <tbody>
        {postings.map((posting)=><tr>{details.map(detail=><td>{posting[detail]}</td>)} <td><BsFillPencilFill onClick={()=>{setAdd(true);setMode(1);setEditData(posting)}} style={{color:"brown",cursor:"pointer"}}/></td> <td>  <BsFillTrash3Fill onClick={()=>{deletePosting(posting.id)}} style={{color:"red",cursor:"pointer"}}/>  </td> </tr>)}
      </tbody>
        
        </Table>

        <Button style={{width:"100%"}} variant='primary' onClick={()=>{setAdd(true);setMode(0)}}>Create a new Posting</Button>
        <PostingModal show={add} mode={mode} editData={editData} onClose={()=>{setAdd(false)}} addPosting={addPosting} updatePosting={updatePosting} />

        </div>
    );
}
export default PostingList;

