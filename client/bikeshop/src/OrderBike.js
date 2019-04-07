import React, { Component } from 'react';

function OrderBike(props) {
  return (
    <div className="col">
      <form>
        <div className="form-group">
          make:<input type="text" className="form-control"></input><br />
          model:<input type="text" className="form-control"></input><br />
          colour:<input type="color" className="form-control"></input><br />
          style:<input type="checkbox" className="form-control"></input>red<br />
          price:<input type="text" className="form-control"></input><br />
          stock:<input type="text" className="form-control"></input><br />
        </div>
        <input type="submit" value="Submit"></input>
        <input type="reset"></input>
      </form>
    </div>

  );
}

export default OrderBike;