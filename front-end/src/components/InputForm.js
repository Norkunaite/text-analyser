import React, { Component } from 'react';
import { Formik, Form, Field } from 'formik';
import axios from 'axios';
import Output from './Output';

class InputForm extends Component{

    constructor(props){
        super(props)
        this.state={
            id:'',
            initialString:'',
            charWordSet:[],
        }
        
    }

    onSubmit=(values)=> {
        let initialText = {
            content: values.content,
        }

        axios.post(`http://localhost:8080/api/texts/`, initialText)
        .then(
            response => {
                console.log(response.data)
                this.setState({ id: response.data })
                this.getAnalysis(response.data);
            }
            
        )
        
    }

    getAnalysis=(id)=>{
        axios.get(`http://localhost:8080/api/texts/${id}`)
        .then(  
            response => {
                this.setState({ 
                    initialString:response.data.initialString,
                    charWordSet: response.data.charWordSet })
            }
            
        )
    }

    getHistory=()=>{
        this.props.history.push(`/history`);

    }

    render(){
        let { initialString, id } = this.state
        return(
            <div>
                <Formik initialValues={{initialString, id}} 
                    onSubmit={this.onSubmit}>
                    {
                        (props)=>(
                            <Form>
                                <fieldset className="form-group">
                                    <label className="h3">Enter text to analyse</label>
                                    <Field className="form-control" type="text" name="content" />
                                </fieldset>
                                <button className="btn btn-light" type="submit">Analyse</button>
                                <button className="btn btn-light mx-5" onClick={this.getHistory} type="button" >History</button>
                            </Form>
                        )
                    }
                </Formik>
                <div className="my-3">
                    <Output 
                    initialString={this.state.initialString}
                    charWordSet={this.state.charWordSet}/>
                </div>
                
            </div>
        );
    }
}

export default InputForm;