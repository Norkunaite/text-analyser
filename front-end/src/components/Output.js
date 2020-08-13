import React from 'react';

function Output(props){
    if(props.initialString===''){
        return(
            <div>
            </div>
        )
    }else{
        return(
            <div>
                <li style={{ listStyleType: "none" }} className="h5">Request: </li>
                <li style={{ listStyleType: "none" }}>{props.initialString}</li>
                <li style={{ listStyleType: "none" }} className="h5">Response: </li>
                <ul>
                    {props.charWordSet.map(
                        char=>
                        <li style={{ listStyleType: "none" }}>{char.lastChar} {char.wordCount} {char.wordList.map(
                            word=>
                            <span>{word} </span>
                        )}</li>
                    )}
                </ul>
            </div>
        )
    }
    

}

export default Output;