import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useState } from 'react';
import Stack from 'react-bootstrap/Stack';
import axios from 'axios';

//import Nav from './Nav';


function Login(){
const [inputs,setInputs] = useState({});

async function handleInput(event){
    event.preventDefault();
    try{
        
        const response = await axios.post("https://localhost:5000/signin",inputs);
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
}//[name] computed property name. if i put name:value it would assign each time 'name':value not adding any new fields.

    return (
        <Form className='col-md-3 position-absolute top-50 start-50 translate-middle'> 
           <Stack gap={3}>
            <Form.Group>
                <Form.Label>Username:</Form.Label>
                <Form.Control size='sm' type='text' name="username" value={inputs.username} onChange={handleChange} placeholder='Enter your Username'/>
            </Form.Group>
            
           
            <Form.Group>
                <Form.Label>Password:</Form.Label>
                <Form.Control size='sm' type='password' name="password" onChange={handleChange} value={inputs.password} placeholder='Enter your password'/>
            </Form.Group>
           
            <Button variant='primary' type='submit' onClick={handleInput}>Login</Button>

            <Form.Text className='position-absolute top-100 start-50 translate-middle-x' >New to Unibite? Signup! </Form.Text>
            </Stack>
        </Form>
            );

}
export default Login