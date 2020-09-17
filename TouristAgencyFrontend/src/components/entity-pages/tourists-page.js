import React from 'react';
import EntityPage from './entity-page';

export default class TouristsPage extends React.Component {
  columns = [
    {title: 'Id', field: 'id', type: 'numeric', editable: 'never', disableClick: true},
    {title: 'Имя', field: 'name', disableClick: true},
    {title: 'Фамилия', field: 'surname', disableClick: true},
    {title: 'Отчество', field: 'patronymic', disableClick: true},
    {title: 'Пол', field: 'sex', disableClick: true},
    {title: 'Серия паспорта', field: 'passportSeries',  disableClick: 'true'},
    {title: 'Номер паспорта', field: 'passportId', disableClick: 'true'}
  ]

  render() {
    return (
      <EntityPage
        columns={this.columns}
        url='/tourists'
        tableName='Туристы'
        onRowClick={(event, rowData, togglePanel) => togglePanel()}
      />
    );
  }
}