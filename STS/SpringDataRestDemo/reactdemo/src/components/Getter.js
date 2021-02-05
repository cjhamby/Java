import React from 'react';
import axios from 'axios';

/*
 * demonstrates CORS with the Axios Web Api
 * Axios is very browser-friendly
 * 
 * to use axios, install it with npm as follows
 * cd /reactdemo
 * npm install axios
*/
class Getter extends React.Component{

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
    * check FETCHER.JS for more information
    */
    componentDidMount(){
        /*
        * axios.get returns a Promise for the HTTP Response
        * once the Response Promise is fulfilled, then things happen...
        * each THEN represents a link in the promise chain
        */
        axios.get('/api/myOrders')
        .then(res => {
            console.log("Getter Response: ", res);
            console.log("Getter MyOrders: ", res.data._embedded.myOrders);
            return res.data._embedded.myOrders;
        })
        .then(myorders => {
            this.setState({
                isLoading: false,
                orders: myorders
            });
        })
    }

    render(){
        const {error, orders, isLoading} = this.state;

        if(error) {
            return(<p>Getter: an error occurred</p>);
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
            return <p>Something Weird Happened</p>;
        }
    }
}

export default Getter;