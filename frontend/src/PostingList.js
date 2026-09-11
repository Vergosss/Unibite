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

        <Button style={{width:"100%"}} variant='primary' onClick={()=>{setAdd(true);setMode(0);setEditData({});}}>Create a new Posting</Button>
        <PostingModal show={add} mode={mode} editData={editData} onClose={()=>{setAdd(false);setEditData({})}} addPosting={addPosting} updatePosting={updatePosting} />
{/**After each close we reset the editdata show changes to 0 so postinglist rerenders passing null editdata. 
 * if i dont do this <editData> never changes so modals useffect never runs never changing the state thus keeping the unsaved data.
 * while if i nullify the editData it changes so useffect can run setting the form to the row's data and not what was typed but unsaved 
 * 
 */}
        </div>
    );
}
export default PostingList;

