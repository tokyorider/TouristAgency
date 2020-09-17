import React from 'react';
import EntityPage from './entity-page';

export default class ExcursionsPage extends React.Component {
  columns = [
    {title: 'Id', field: 'id', type: 'numeric', editable: 'never', disableClick: true},
    {title: 'Название', field: 'title', disableClick: true},
    {title: 'Описание', field: 'description', disableClick: true},
    {title: 'Id агентства', field: 'agencyId', type: 'numeric', disableClick: true},
    {title: 'Дата проведения', field: 'arrangementDate', type: 'date', disableClick: true},
    {title: 'Стоимость билета', field: 'price', type: 'numeric', disableClick: true}
  ]

  render() {
    return (
      <>
        <EntityPage
          columns={this.columns}
          url='/excursions'
          tableName='Экскурсии'
          //filterForm={<ExcursionsFilter/>}
          //onRowClick={(event, rowData, togglePanel) => togglePanel()}
        />
      </>
    );
  }
}