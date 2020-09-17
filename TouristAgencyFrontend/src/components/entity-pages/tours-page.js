import React from 'react';
import EntityPage from './entity-page';

export default class ToursPage extends React.Component {
  columns = [
    {title: 'Id', field: 'id', type: 'numeric', editable: 'never', disableClick: true},
    {title: 'Дата начала', field: 'begin', type: 'date', disableClick: true},
    {title: 'Дата конца', field: 'end', type: 'date', disableClick: true},
    {title: 'Id группы', field: 'groupId', type: 'numeric', disableClick: true},
    {title: 'Id груза', field: 'cargoId', type: 'numeric', disableClick: true},
    {title: 'Id туриста', field: 'touristId', type: 'numeric', disableClick: true},
  ]

  render() {
    return (
      <EntityPage
        columns={this.columns}
        url='/tours'
        tableName='Путевки'
        onRowClick={(event, rowData, togglePanel) => togglePanel()}
      />
    );
  }
}