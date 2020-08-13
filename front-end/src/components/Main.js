import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import InputForm from './InputForm';
import History from './History';


class Main extends Component{

    render() {
        return (
            <Router>
                <>
                    <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h1 class="display-4">Analization Application</h1>
                        <p class="lead">A warm welcome! Counting words is our passion.</p>
                    </div>
                    </div>
                    <div className="container">
                        <Switch>
                            <Route path="/" exact component={InputForm} />
                            <Route path="/history" component={History} />
                        </Switch>
                    </div>
                    
                </>
            </Router>
        )
    }



}

export default Main;