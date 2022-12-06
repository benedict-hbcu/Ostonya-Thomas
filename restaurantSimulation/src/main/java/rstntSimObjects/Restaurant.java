package rstntSimObjects;

import java.util.*;
import java.util.stream.Collectors;

public class Restaurant {
    private List<Table> tables;
    private Map<TableType, Queue<Party>> line;
    private model.menu.Menu menu;

    public Restaurant(List<Table> tables, model.menu.Menu menu) {
        this.line=new HashMap<>();
        this.tables = tables;
        this.menu = menu;
    }

    public void addPartyToQueue(Party party){
        TableType tableType = TableType.getTableTypeForParty(party.getMembers().size());
        if(!this.line.containsKey(tableType)){
            this.line.put(tableType, new ArrayDeque<>());
        }
        this.line.get(tableType).add(party);
    }

    public void incrementTimeWaitedInQueue(){
        for(Queue <Party> queue : line.values()) {
            for(Party party : queue){
                party.markStillInQueue();
            }
        }
    }

    public List <Table> getUnoccupiedTables(){
        return this.tables.stream().filter(x->!x.isOccupied()).collect(Collectors.toList());
    }

    public List <Table> getOccupiedTables(){
        return this.tables.stream().filter(x->x.isOccupied()).collect(Collectors.toList());
    }

    public Optional<Party> getNextParty(TableType tableType){
        return Optional.ofNullable(this.line.getOrDefault(tableType, new ArrayDeque<>()).poll());
    }

    public List<Table> getTables() {
        return tables;
    }



    public model.menu.Menu getMenu() {
        return menu;
    }
}
