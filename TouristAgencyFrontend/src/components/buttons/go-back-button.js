import React from 'react';

export default class GoBackButton extends React.Component {
  render() {
    return (
      <button onClick={() => window.location.assign('..')}>
        Назад
      </button>
    );
  }
}

