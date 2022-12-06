package Generators;

import rstntSimObjects.utilities.Constants;
import rstntSimObjects.Table;
import rstntSimObjects.TableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableGenerator {
    private TableGenerator(){
    }
    public static List<Table> generateTables(){
        List<Table> tables = new ArrayList<>();
        for(Map.Entry<TableType,Integer> entry : Constants.TABLE_TYPE_DISTRIBUTION.entrySet()) {
            tables.addAll(generateNumTablesOfType(entry.getKey(), entry.getValue()));
        }
        return tables;
    }
    public static List<Table> generateNumTablesOfType(TableType tableType, int numTables) {
        List<Table> tables = new ArrayList<>();
        for (int i=0; i<numTables;i++){
            tables.add(new Table(tableType));
        }
        return tables;
    }
}
