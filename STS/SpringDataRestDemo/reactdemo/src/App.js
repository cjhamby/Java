import React from 'react';
import './App.css';
import Fetcher from './components/Fetcher';
import Getter from './components/Getter';

/*
 * demonstrates two ways to get data from a REST API
 */
class App extends React.Component{

  render() {
    return (
      <div>App.js
        <Getter />
        <Fetcher />
      </div>
      );  
  }
}

export default App;
