import React from 'react';
import {Button, Collapse} from 'react-bootstrap';


export default class CollapseContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            open: false
        }
    }

    render() {
        return (
            <>
                <Button
                    onClick={() => this.setState({open: !this.state.open})}
                    aria-controls={this.props.childId}
                    aria-expanded={this.state.open}
                >{this.props.buttonText || 'Filter'}</Button>
                <Collapse in={this.state.open}>
                    {this.props.children}
                </Collapse>
            </>
        )
    }
}