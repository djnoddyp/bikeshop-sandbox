import React, { Component } from 'react';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-balham.css';


class Stock extends Component {
  state = {
    bikes: this.props.bikes,
    sortMake: 0,
    sortPrice: 0,
    columnDefs: [{
      headerName: "Make", field: "make"
    }, {
      headerName: "Model", field: "model"
    }, {
      headerName: "Price", field: "price"
    }],
    rowData: [{
      make: "Toyota", model: "Celica", price: 35000
    }, {
      make: "Ford", model: "Mondeo", price: 32000
    }, {
      make: "Porsche", model: "Boxter", price: 72000
    }]
  };

  render() {
    return (
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col" id="make" onClick={this.sortMakes}>make</th>
            <th scope="col">model</th>
            <th scope="col">style</th>
            <th scope="col">colour</th>
            <th scope="col">stock</th>
            <th scope="col" id="price" onClick={this.sortPrices}>price</th>
          </tr>
        </thead>
        <tbody>
          {this.state.bikes.map(b =>
            <tr>
              <td>{b.id}</td>
              <td>{b.make}</td>
              <td>{b.model}</td>
              <td>{b.style}</td>
              <td>{b.colour}</td>
              <td>{b.stock}</td>
              <td>£{b.price}</td>
            </tr>)}
        </tbody>
      </table>
    )
  }

  sortMakes = () => {
    this.setState({
      bikes: this.state.bikes.sort((a, b) => {
        var nameA = a.make.toUpperCase(); // ignore upper and lowercase
        var nameB = b.make.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        // names must be equal
        return 0;
      })
    });
  };

  sortPrices = () => {
    this.setState({
      bikes: this.state.bikes.sort((a, b) => a.price < b.price)
    });
  };

}

export default Stock;