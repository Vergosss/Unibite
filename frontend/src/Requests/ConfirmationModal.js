import Modal from "react-bootstrap/Modal"
import Button from "react-bootstrap/Button";
function ConfirmationModal({show,type,onClose,onClick}){

    return (        
    <Modal backdrop="static" keyboard={false} show={show} onHide={onClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Confirm Action</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                This action cannot be undone
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>
                     Cancel
                </Button>
                <Button variant="primary" onClick={()=>onClick(type)}>
                {(type === "Approve")? "Approve Request" : "Reject request"} 
                </Button>
            </Modal.Footer>
</Modal>);
}
export default ConfirmationModal;