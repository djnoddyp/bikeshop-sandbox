import React, { Component } from 'react';

class AddBikes extends Component {
  state = {
    models: [],
    makes: new Set(this.props.bikes.map(b => b.make))
  };

  render() {
    return (
      <div className="col">
        <form>
          <div className="form-group">
            <label htmlFor="makeSelect">Make</label>
            <select className="form-control" id="makeSelect" onChange={this.handleMakeSelect}>
              <option selected="selected">select one</option>
              {this.mapMakes(this.state.makes)}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="modelSelect">Model</label>
            <select className="form-control" id="modelSelect">
              {this.state.models.map(m => <option>{m}</option>)}
            </select>
          </div>
          {/* <div className="form-group">
            <label for="colour">Colour</label>
            <input type="color" className="form-control" id="colour"></input>
          </div>
          <div className="form-group">
            <label for="style">Style</label>
            <input type="checkbox" className="form-control" id="style"></input>
          </div>
          <div className="form-group">
            <label for="stock">Stock</label>
            <input type="text" className="form-control" id="stock"></input>
          </div> */}
          <input type="submit" value="Submit"></input>
          <input type="reset"></input>
        </form>
      </div>
    );
  }

  mapMakes(makes) {
    let makesArray = []
    for (let v of makes.values()) {
      makesArray.push(<option>{v}</option>)
    }
    return makesArray
  }

  handleMakeSelect = (event) => {
    let mods = this.props.bikes.filter(b => b.make === event.target.value).map(b => b.model + "\t£" + b.price)
    this.setState({
      models: mods
    })
  };

}

export default AddBikes;