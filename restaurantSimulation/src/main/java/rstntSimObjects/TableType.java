package rstntSimObjects;

public enum TableType {
    SMALL(2),
    MEDIUM(4),
    LARGE(6);

    private int maxSeating;

    TableType(int maxSeating) {
        this.maxSeating = maxSeating;
    }

    private boolean canSeatParty(int partySize){
        return partySize == this.maxSeating || partySize == this.maxSeating-1;
    }
    public static TableType getTableTypeForParty(int partySize) {
        for (TableType tableType: TableType.values()){
            if (tableType.canSeatParty(partySize)){
                return tableType;
            }
        }
        throw new IllegalArgumentException("There is no table that can seat a party of that size.");
    }
}
