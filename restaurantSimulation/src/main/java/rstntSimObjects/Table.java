package rstntSimObjects;

public class Table {
    private TableType tableType;
    private Party seatedParty;

    public Table(TableType tableType) {
        this.tableType = tableType;
    }
    public void seatParty(Party party){
        this.seatedParty = party;
    }
    public void unseatParty(){
        this.seatedParty=null;
    }
    public boolean isOccupied(){
        return this.seatedParty!=null;
    }

    public TableType getTableType() {
        return tableType;
    }

    public Party getSeatedParty() {
        return seatedParty;
    }
}
