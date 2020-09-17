import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './abstract-filter';

export default class ExcursionsFilter extends React.Component {
    constructor(props) {
        super(props);

        this.defaultState = {
            arrangementDate: '',
        };
        this.state = this.props.outerState || this.defaultState;
    }

    render() {
        return (
            <AbstractFilter
                formId="excursionsFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="arrangementDate">
                        <Form.Label>Дата </Form.Label>
                        <Form.Control type="date" value={this.state.arrangementDate}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}