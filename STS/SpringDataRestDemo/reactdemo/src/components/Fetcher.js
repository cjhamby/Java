import React from 'react';

/*
 * demonstrates CORS with the Fetch Web Api
 * note that this is not entirely mobile-friendly
 * mobile firefox doesn't like it, for example
*/
class Fetcher extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      error: null,
      orders: [],
      isLoading: true
    };
  }

/* 
 * executed when the component is created and inserted into the DOM
 * demonstrates PROMISE CHAINING 
*/
  componentDidMount() {
   
    /*
    * fetch() returns a Promise for the HTTP Response
    * once the Response Promise is fulfilled, then things happen...
    * each THEN represents a link in the promise chain
    */
    fetch('/api/myOrders')

    /* 
     * res.json reads the entire body of the response as a stream,
     * and parses it to JSON
     * note that res.json is the source of some incompatibility
    */
    .then(res => { 
        console.log("Fetcher Response", res);
        return res.json(); 
    })  
    
    /*
     * this link finds the relevant data in the JSON-ified body object
     * finding where the data is, is a matter of digging through the log
    */
    .then(result => result._embedded.myOrders)

    /*
     * this link updates the component's state to include the orders
    */
    .then(orders => {
        this.setState({
            isLoading: false,
            orders: orders
        });
    },

    /*
     * if an error occurs at any time in the promise chain, 
     * this callback is executed instead
    */
    (error) => {
        this.setState({
            isLoading: false,
            error
        });
    })
  }


  render() {
    const {error, orders, isLoading} = this.state;

    if(error) {
      return(<p>Fetcher: an error occurred</p>);
    }
    else if(isLoading) {
      return <p>Loading...</p>;
    }
    else if(orders != null){
      return (
            <ul>
            {orders.map(order => 
                    (<li key={order._links.self.href}>
                        {order.orderName} spent {order.cost}
                    </li>) 
                )}
            </ul>
      )
    } else {
        return <p>Fetcher: Something Weird Happened</p>;
    }
  }
}

export default Fetcher;
