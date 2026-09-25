import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Login from './Login';
import Signup from './Signup';
//import Postings from './Postings'
import Header from './Header';
import Requests from './Requests/Requests';
import Navigation from './Navigation/Navigation';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <>
<BrowserRouter>
  <Navigation/>
</BrowserRouter>

  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

