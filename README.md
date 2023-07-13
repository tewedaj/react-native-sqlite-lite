# react-native-sqlite-lite

easy straight forward sqlite module for react-native
## Only works on android
## Installation

```sh
npm install react-native-sqlite-lite
```

## Usage


---
## Installation


## Usage

Import the library functions in your JavaScript code:

```javascript
import {
  createDatabase,
  createTable,
  insertQuery,
  selectQuery
} from 'react-native-sqlite-lite';
```

### `createDatabase(databaseName: string, callback: (result: string, error: string) => void): void`

Creates a new SQLite database with the specified name.

- `databaseName`: The name of the database to create.
- `callback`: A function that will be called with the creation result and an error message if applicable.

Example usage:

```javascript
createDatabase('mydatabase', (result, error) => {
  if (error) {
    console.error(error);
  } else {
    console.log(result);
  }
});
```

### `createTable(databaseName: string, tableName: string, query: string, callback: (result: string) => void): void`

Creates a new table in the specified database using the provided query.

- `databaseName`: The name of the database where the table will be created.
- `tableName`: The name of the table to create.
- `query`: The SQL query for creating the table.
- `callback`: A function that will be called with the creation result.

Example usage:

```javascript
const query = 'CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)';
createTable('mydatabase', 'users', query, (result) => {
  console.log(result);
});
```

### `insertQuery(databaseName: string, query: string, callback: (result: string) => void): void`

Executes an SQL INSERT query on the specified database.

- `databaseName`: The name of the database where the query will be executed.
- `query`: The SQL INSERT query.
- `callback`: A function that will be called with the execution result.

Example usage:

```javascript
const query = 'INSERT INTO users (name) VALUES ("John Doe")';
insertQuery('mydatabase', query, (result) => {
  console.log(result);
});
```

### `selectQuery(databaseName: string, query: string, callback: (result: string) => void): void`

Executes an SQL SELECT query on the specified database.

- `databaseName`: The name of the database where the query will be executed.
- `query`: The SQL SELECT query.
- `callback`: A function that will be called with the execution result.

Example usage:

```javascript
const query = 'SELECT * FROM users';
selectQuery('mydatabase', query, (result) => {
  console.log(result);
});
```

---




## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---