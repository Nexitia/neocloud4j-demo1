import React, { Component } from 'react';
import {commons} from "_helpers/commons";

class GenericHomePage extends Component {

    render () {
        const isConnectedUser = commons.isLoggedInAsUser(this.props.userContext);
        const applicationFriendlyName = "Mon app custom";
        if(applicationFriendlyName){
            document.title = "Neocloud4J - " + applicationFriendlyName;
        }

        return <>
        	<div style={{marginTop: '15%', border: '1px solid #dedede', padding: '2rem'}}>
                <center style={{marginBottom: '4rem'}}>
                    <h1>Projet demo application 1 custom!</h1>
                    <br/>

                    <p style={{
                        fontSize: '26px',
                        lineHeight: '1.34',
                        fontWeight: '200'
                    }}>
                        Cette page est custom, à vous de jouer
                    </p>

                    <br/>
                </center>
            </div>
        </>
    }
}

export default GenericHomePage;
