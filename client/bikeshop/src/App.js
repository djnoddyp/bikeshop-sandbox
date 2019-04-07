import React, { Component } from 'react';
import './App.css';
import axios from 'axios';
import AddBikes from './AddBikes';
import Stock from './Stock';

class App extends Component {
  state = {
    bikes: this.getAllBikes(),
    selectedPage: "order"
  };

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col">
            <ul className="nav flex-column">
              <li className="nav-item">
                <a className="nav-link" id="stock" onClick={this.handlePageSelect}>Bikes</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" id="order" onClick={this.handlePageSelect}>Orders</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">Repairs</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">Profile</a>
              </li>
            </ul>
          </div>
          <div className="col">
            <Page page={this.state.selectedPage} bikes={this.state.bikes} />
          </div>
        </div>
      </div>
    );
  }

  handleClick = () => {
    axios.get(URL)
      .then(response => this.setState({ message: response.data }))
      .catch(error => console.log(error))
  };

  getAllBikes() {
    let req = new XMLHttpRequest();
    req.open("GET", BIKES_URL, false);
    req.send();
    let bikes = JSON.parse(req.responseText)
    return bikes
  }

  handlePageSelect = (event) => {
    this.setState({
      selectedPage: event.target.id
    })
  };

}

function Page(props) {
  const page = props.page;
  switch (page) {
    case "order": return <AddBikes bikes={props.bikes} />
    case "stock": return <Stock bikes={props.bikes} />
  }
}

const ORDERS_URL = "http://localhost:8090/orders";
const BIKES_URL = "http://localhost:8090/bikes";

export default App;