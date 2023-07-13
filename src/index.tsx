import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-sqlite-lite' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const SqliteLite = NativeModules.SqliteLite
  ? NativeModules.SqliteLite
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );




export function createDatabase(databaseName: string, callback: (result: string,ERR: string) => void): void {

  SqliteLite.createDatabase(databaseName, callback);
}

export function createTable(databaseName: string, tableName: string, query: string, callback: (result: string) => void): void {
  
    SqliteLite.createTable(databaseName,tableName, query, callback);
  }

export function insertQuery(databaseName: string, query: string, callback: (result: string) => void): void {
  
    SqliteLite.insertQuery(databaseName, query, callback);
  }

export function selectQuery(databaseName: string, query: string, callback: (result: string) => void): void {
    
      SqliteLite.selectQuery(databaseName, query, callback);
    }





