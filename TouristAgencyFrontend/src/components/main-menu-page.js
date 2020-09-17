import React from 'react';

class MainMenuPage extends React.Component {
  constructor(props) {
    super(props);

    this.handleEntity = this.handleEntity.bind(this);
  }

  handleEntity(name) {
    window.location.assign(name);
  }

  render() {
    return (
      <div>
        <h1>
          Главное меню
        </h1>
        <button onClick = {this.handleEntity.bind(this, 'tourists')}>
          Туристы
        </button>
        <button onClick = {this.handleEntity.bind(this, 'tours')}>
          Путевки
        </button>
        <button onClick = {this.handleEntity.bind(this, 'excursions')}>
          Экскурсии
        </button>
        <button onClick = {this.handleEntity.bind(this, 'hotels')}>
          Гостиницы
        </button>
        <button onClick = {this.handleEntity.bind(this, 'cargoes')}>
          Грузы
        </button>
      </div>
    );
  }
}

export default MainMenuPage;