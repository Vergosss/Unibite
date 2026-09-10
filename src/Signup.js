import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useState } from 'react';
import Stack from 'react-bootstrap/Stack';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col'
import axios from 'axios'


function Signup(){
const [inputs,setInputs] = useState({});

async function handleInput(event){
    event.preventDefault();
    console.log(inputs);
    try{
        
        const response = await axios.post("http://localhost:5000/users",inputs);
        console.log(response);
    }
    catch(error){
        console.log(error);
    }
}

function handleChange(event){
    const name = event.target.name;
    const value = event.target.value;
    setInputs({...inputs,[name]:value}); // inputs is an object so assign this object a new object with changed pair name:value and keep the rest
}
    return (
        <Form className='col-md-3 position-absolute top-50 start-50 translate-middle'>
            
           <Stack gap={3}>
            <Form.Group>
                <Form.Label>Username:</Form.Label>
                <Form.Control size='sm' type='text' name="username" value={inputs.username} onChange={handleChange} placeholder='Enter your Username'/>
            </Form.Group>
            
             <Form.Group>
                <Form.Label>Email:</Form.Label>
                <Form.Control size='sm' type='email' name="email" onChange={handleChange} value={inputs.email} placeholder='username@example.com'/>
            </Form.Group>

            <Form.Group>
                <Form.Label>Contact Number:</Form.Label>
                <Form.Control size='sm' type='tel' name="telephone" onChange={handleChange} value={inputs.telephone} />
            </Form.Group>

            <Form.Group>

                <Form.Label>Role:</Form.Label>
                <Row>
                <Col>Admin</Col>
               <Col> <Form.Check type='radio' name='role' value='Admin' onChange={handleChange}></Form.Check></Col>
                <Col>Student</Col>
               <Col> <Form.Check type='radio' name='role' value='Student' onChange={handleChange}></Form.Check></Col>
              
                </Row>
            </Form.Group>

            <Form.Group>
                <Form.Label>Password:</Form.Label>
                <Form.Control size='sm' type='password' name="password" onChange={handleChange} value={inputs.password} placeholder='Enter your password'/>
            <Form.Text muted>Your password should contain at minimum 8 characters, with at least one special character and at least one number.</Form.Text>
            </Form.Group>
           
            <Button variant='primary' type='submit' onClick={handleInput}>Signup</Button>

            </Stack>


        </Form>


    );
}
export default Signup