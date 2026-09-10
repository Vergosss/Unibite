import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table';
import { useState } from 'react';
import PostingModal from './PostingModal';
import { BsFillPencilFill,BsFillTrash3Fill  } from "react-icons/bs";
//
function PostingList({postings,addPosting,deletePosting}){
const details = ['title','notes','portions'];
const [add,setAdd] = useState(false);
//
function createPosting(){
setAdd(true);

}
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
        {postings.map((posting)=><tr>{details.map(detail=><td>{posting[detail]}</td>)} <td><BsFillPencilFill style={{cursor:"pointer"}}/></td> <td>  <BsFillTrash3Fill onClick={()=>{deletePosting(posting.id)}} style={{cursor:"pointer"}}/>  </td> </tr>)}
      </tbody>
        
        </Table>

        <Button style={{width:"100%"}} variant='primary' onClick={createPosting}>Create a new Posting</Button>
        <PostingModal show={add} onClose={()=>{setAdd(false)}} addPosting={addPosting}/>

        </div>
    );
}
export default PostingList;

