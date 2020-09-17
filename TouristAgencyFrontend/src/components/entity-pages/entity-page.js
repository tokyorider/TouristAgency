import React from 'react';
import { AXIOS } from '../../utils/axios-config';
import MaterialTable from 'material-table';
import GoBackButton from '../buttons/go-back-button';

export default class EntityPage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      modalMessage: '',
      modalTitle: '',
      showModal: false,
      tableRef: React.createRef(),
      formState: null,
    };

    this.firstFilter = false;
  }

  onModalClose = () => {
    this.setState({
      showModal: false
    });
  };

  onFilterSubmit = (state) => {
    this.setState({
      formState: state,
    });

    this.firstFilter = state !== null;
    this.state.tableRef.current.onQueryChange()
  };

  showModal = (text, title) => {
    this.setState({
        modalTitle: title,
        modalMessage: text,
        showModal: true
    });
  };

  showSuccess = (text) => this.showModal(text, 'Success!');

  showError = (text) => this.showModal(text, 'Error!');

  getErrorFromReason = (reason) => reason.response?.data?.error;

  addEntity = newData =>
    new Promise(resolve => {
      AXIOS.post(`${this.props.url}`, newData)
        .then(() => {
          this.showSuccess('Entity was added!');
          this.state.tableRef.current.onQueryChange();
        })
        .catch((reason) =>
          this.showError(`Error adding entity: ${this.getErrorFromReason(reason)}`)
        )
      resolve()
    });

  updateEntity = (newData, oldData) =>
    new Promise((resolve, reject) => {
      newData.id = oldData.id;
      console.log(newData.id);
      AXIOS.put(`${this.props.url}`, newData)
        .then(() => {
          this.showSuccess(`Entity was updated`);
          this.state.tableRef.current.onQueryChange();
        })
        .catch((reason) =>
          this.showError(`Error updating entity: ${this.getErrorFromReason(reason)}`)
        );

      resolve()
    });

  deleteEntity = oldData =>
    new Promise((resolve, reject) => {
      AXIOS.delete(`${this.props.url}/${oldData.id}`)
        .then(() => {
          this.showSuccess(`Entity was deleted`);
          this.state.tableRef.current.onQueryChange();
        })
        .catch((reason) =>
          this.showError(`Error deleting entity: ${this.getErrorFromReason(reason)}`)
        );

      resolve()
    });

  resolveResults = (resolve, response) => {
    resolve({
      data: response.data.content,
      page: response.data.pageNumber,
      totalCount: response.data.totalElements,
    })
  };

  getAllEntities = (query) => {
    console.log("page " + query.page);
    console.log("pageSize " + query.pageSize);

    return AXIOS.get(
      this.props.url,
      {
        params: this.getQueryParams(query)
      });
    ;
  };

  getFilteredEntities = (query) => {
    const params = Object.assign(this.getQueryParams(query), this.state.formState);
    if (this.firstFilter) {
      params.page = 0;
      this.firstFilter = false;
    }
    return AXIOS.get(`${this.props.url}/filter`, { params });
  };

  getQueryParams = (query) => ({
    page: query.page,
    pageSize: query.pageSize,
    orderBy: query.orderBy && query.orderBy.field,
    order: query.orderDirection && (query.orderDirection === 'asc' ? 'ASCENDING' : 'DESCENDING')
  });

  render() {
    return (
      <>
        <MaterialTable
          tableRef={this.state.tableRef}
          title={this.props.tableName}
          columns={this.props.columns}
          data={query =>
            new Promise((resolve, reject) => {
              const requestPromise = this.state.formState
                                ? this.getFilteredEntities(query)
                                : this.getAllEntities(query);

              requestPromise
                .then(
                  response => this.resolveResults(resolve, response)
                )
                .catch(
                  (reason) => {
                    this.showError(`Error refreshing entities: ${this.getErrorFromReason(reason)}`);
                    resolve({
                      data: [],
                      page: 0,
                      totalCount: 0
                    })
                  })
            })
          }
          options={{
            actionsColumnIndex: -1,
            filtering: this.props.filterForm,
            search: false,
            pageSize: 8,
            pageSizeOptions: []
          }}
          editable={{
            onRowAdd: this.addEntity,
            onRowUpdate: this.updateEntity,
            onRowDelete: this.deleteEntity,
          }}
          components={this.props.filterForm && {
            FilterRow: (props) => React.cloneElement(this.props.filterForm, {
                onSubmit: this.onFilterSubmit, outerState: this.state.formState
            })
          }}
          detailPanel={this.props.detailPanel}
          onRowClick={this.props.onRowClick}
        />

        <GoBackButton />
      </>
    );
  }
}