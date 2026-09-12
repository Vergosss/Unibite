
import Map from './Map';
import PostingList from './PostingList';
import { useEffect,useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

function Postings(){

const [postings,setPostings] = useState([]);
   useEffect(()=>{
    const fetchPostings = async() =>{
    try{
      const response = await axios.get('http://localhost:5000/postings');
      setPostings(response.data); // if you try here to return html it wont work. but if not here when? the returned html is in the render so
     console.log(postings); //indirectly we 'save' the markers via state then we exploit the state to render the markers
    }
    catch(error){
      console.log(error);
    }
    }
    fetchPostings();
  },[]);
  //

async function addPosting(posting){
    try{
        const response = await axios.post('http://localhost:5000/postings',posting);
        setPostings([...postings,response.data]);
        
    }
    catch(error){
        alert('error');
    }
}
  //
 async function deletePosting(id){
        console.log(id);
        try{  
          const response = await axios.delete(`http://localhost:5000/postings/${id}`);

          setPostings(postings.filter((posting)=>posting.id !==id));
        }
        catch(error){
          console.log(error);
        }
}
  //
async function updatePosting(posting){
  try{
      
      const response = await axios.put(`http://localhost:5000/postings/${posting.id}`,posting);
      setPostings(postings.map((item)=>{if (item.id === posting.id){Object.assign(item,posting)} return item })); //if update succeeds in the backend/database change the state so table can refresh
  }
  catch(error){
    console.log(error);
  }
}
  //
    return (
      <div>
      {/*  
      className='d-flex flex-row-reverse justify-content-between position-absolute top-50 start-50 translate-middle'
      <Map markers={postings}/>*/}      
        <PostingList postings={postings} addPosting={addPosting} deletePosting={deletePosting} updatePosting={updatePosting}/>
        </div>
    );
}
export default Postings;