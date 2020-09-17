import React from 'react';
import EntityPage from './entity-page';

export default class CargoesPage extends React.Component {
  columns = [
    {title: 'Id', field: 'id', type: 'numeric', editable: 'never', disableClick: true},
    {title: 'Количество мест', field: 'placesAmount', type: 'numeric', disableClick: true},
    {title: 'Вес', field: 'weight', type: 'numeric', disableClick: true},
    {title: 'Тип', field: 'type', disableClick: true},
    {title: 'Стоимость упаковки', field: 'packagePrice', type: 'numeric', disableClick: true},
    {title: 'Стоимость страховки', field: 'insurancePrice', type: 'numeric', disableClick: true},
    {title: 'Суммарная стоимость', field: 'totalPrice', type: 'numeric', disableClick: true},
  ]

  render() {
    return (
      <EntityPage
        columns={this.columns}
        url='/cargoes'
        tableName='Грузы'
        onRowClick={(event, rowData, togglePanel) => togglePanel()}
      />
    );
  }
}