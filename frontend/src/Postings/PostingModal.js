import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import PostingForm from './PostingForm';
import { useEffect, useState } from 'react';
function PostingModal({show,mode,editData,onClose,addPosting,updatePosting,deletePosting}){
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
    console.log('Inserting...',posting);
    addPosting(posting);
    //setPosting({});
    onClose();
}
else if(mode === 1){
    console.log('Editing...',posting);
    updatePosting(posting);
    onClose();
}
else{
    console.log('Deleting...',posting);
    deletePosting(posting.id);
    onClose();
}
}
//
return (
        <Modal backdrop="static" keyboard={false} show={show} onHide={onClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>{(()=>{switch(mode){
                    case 0:
                        return "Create Posting";
                    case 1:
                        return "Edit Posting";
                    case 2:
                        return "Delete Posting";
                    default:
                        return "Error";

                }})()}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              {(mode === 0 || mode === 1) ? <PostingForm handleChange={handleChange} posting={posting}/> : "This action cannot be undone"}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>
                     Cancel
                </Button>
                <Button variant="primary" onClick={handleClick}>
                {(() => {switch(mode){
                    case 0:
                        return "Add Posting"; 
                    case 1:
                        return "Submit Changes"; 
                    case 2:
                        return "Delete Posting"; 
                    default:
                        return "Error"; 
                }})()}
                </Button>
            </Modal.Footer>
</Modal>

);

}
export default PostingModal;