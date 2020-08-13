import React, { Component } from 'react';
import axios from 'axios';
import Output from './Output';

class History extends Component{

    constructor(props) {
        super(props)
        this.state = {
            history: [],
        }
    }
    componentDidMount(){
        axios.get(`http://localhost:8080/api/texts/`)
        .then(
            response => {
                console.log(response.data)
                this.setState({ history: response.data })
            }
            
        )
    }

    getBack=()=>{
        this.props.history.push(`/`);
    }

    render(){
        return(
            <div>
                <button className="btn btn-light" onClick={this.getBack} type="button" >Go back</button>
                <h3>All requests and responses</h3>
                <div className="container">
                {
                 this.state.history.map(
                element =>
                <ul>
                    <Output
                    initialString={element.initialString}
                    charWordSet={element.charWordSet}
                    />
                </ul>
                )}

                </div>
            </div>
        )
    }

}
export default History;