import React from 'react';
import EntityPage from './entity-page';

export default class HotelsPage extends React.Component {
  columns = [
    {title: 'Id', field: 'id', type: 'numeric', editable: 'never', disableClick: true},
    {title: 'Название', field: 'title', disableClick: true}
  ]

  render() {
    return (
      <EntityPage
        columns={this.columns}
        url='/hotels'
        tableName='Гостиницы'
        onRowClick={(event, rowData, togglePanel) => togglePanel()}
      />
    );
  }
}