import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import PostingForm from './PostingForm';
import { useEffect, useState } from 'react';
function PostingModal({show,mode,editData,onClose,addPosting,updatePosting}){
//
const [posting,setPosting] = useState({});
//
useEffect(()=>{
console.log('Render only after clicking a different pencil or close',editData);
setPosting(editData); // Pass to the form the row's data
},[editData]);

//
const num_regex = /^[1-9]\d*$/;
const string_regex = /^[^0-9]+/;
//
function handleChange(event){
    const name = event.target.name;
    const value = event.target.value;
    setPosting({...posting,[name]:value});
}
//
function handleClick(){
    //input check first
    if(mode === 0 ){
    if(!num_regex.test(posting.portions) || !string_regex.test(posting.title) || !string_regex.test(posting.notes)){ 
    alert('Error!')
    onClose();
    return;
    }
    console.log(posting);
    return;
    addPosting(posting);
    setPosting({});
}
else{
    console.log(posting);
    //updatePosting();
}
}
//
return (
        <Modal backdrop="static" keyboard={false} show={show} onHide={onClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Create a Posting</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <PostingForm handleChange={handleChange} posting={posting}/>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>
                     Close
                </Button>
                <Button variant="primary" onClick={handleClick}>
                {(mode === 0 )? "Add Posting" : "Submit Changes"}
                </Button>
            </Modal.Footer>
</Modal>

);

}
export default PostingModal;