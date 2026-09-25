
import Form from 'react-bootstrap/Form';
//

function PostingForm({handleChange,posting}){
//
//

    return (
        <Form>
            <Form.Group>
                <Form.Label>Title:</Form.Label>
                <Form.Control size='sm' type='text' name="title" value={posting.title ?? ""} placeholder='Enter a title for your Posting' onChange={handleChange}/>
            </Form.Group>
            
             <Form.Group>
                <Form.Label>Notes:</Form.Label>
                <Form.Control size='sm' as="textarea" name="notes" value={posting.notes ?? ""} placeholder='Enter a description of your posting. Do not forget to mention allergens that your meal may contain' onChange={handleChange}/>
            </Form.Group>

            <Form.Group>
                <Form.Label>Portions</Form.Label>
                <Form.Control size='sm' type='tel' name="portions" value={posting.portions ?? ""} onChange={handleChange}/>
            </Form.Group>


        </Form>
    );
}
export default PostingForm;