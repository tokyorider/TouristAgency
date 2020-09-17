import React from 'react';
import {Button, Col, Form} from 'react-bootstrap';
import CollapseContainer from "../collapse-container"

export default class AbstractFilter extends React.Component {
    onFormChange = (event) => {
        this.props.setState({[event.target.id]: event.target.value});
    }

    onSubmit = (event) => {
        this.props.onSubmit(this.props.state)
        event.preventDefault()
    }

    onReset = (event) => {
        this.props.setState(this.props.defaultState)
        this.props.onSubmit(null)
        event.preventDefault()
    }

    render() {
        return (
            <tr>
                <td colSpan="100%">
                    <CollapseContainer childId={this.props.formId}>
                        <Form onSubmit={this.onSubmit} id={this.props.formId} onChange={this.onFormChange}>
                            {this.props.children}
                            <Form.Row>
                                <Col>
                                    <Button variant="primary" type="submit">Filter</Button>
                                    <Button variant="danger" onClick={this.onReset}>Reset</Button>
                                </Col>
                            </Form.Row>
                        </Form>
                    </CollapseContainer>
                </td>
            </tr>
        )
    }
}