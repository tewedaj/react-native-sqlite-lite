// subject: MVP Done
// type: feature
//
// test funcion for all the functions

import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import {  createDatabase,createTable,insertQuery,selectQuery} from 'react-native-sqlite-lite';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    
    
      createTable("test", "test", "CREATE TABLE test(id NUMBER , test number)", (result) => {
        console.log("Table Created: " , result);
        insertQuery("test", "INSERT INTO test(id,test) Values (3,4)",  (result) => {
          console.log("Inserted To Table: " , result);
          selectQuery("test", "SELECT * FROM test", (result) => {
            console.log("Selected From Table: " , result);
          }
          );
        }
        );
      }
      );

    

  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );

}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
