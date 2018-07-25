package com.anovak92.countinmind.model;

import com.anovak92.countinmind.model.operations.AddOperation;
import com.anovak92.countinmind.model.operations.AllOperation;
import com.anovak92.countinmind.model.operations.DivOperation;
import com.anovak92.countinmind.model.operations.MultOperation;
import com.anovak92.countinmind.model.operations.SubOperation;

import java.util.ArrayList;
import java.util.List;

public class OperationFactory {
    public static Operation create(String type){
        switch (type){
            case Operation.ADD:
                return new AddOperation();
            case Operation.SUB:
                return new SubOperation();
            case Operation.MUL:
                return new MultOperation();
            case Operation.DIV:
                return new DivOperation();
            case Operation.ALL:
                return new AllOperation();
            default:
                throw new RuntimeException("No such operation");
        }
    }
    public static List<Operation> crateAllAvailable(){
        List<Operation> availableOperations = new ArrayList<>();
        availableOperations.add(new AddOperation());
        availableOperations.add(new SubOperation());
        availableOperations.add(new MultOperation());
        availableOperations.add(new DivOperation());
        availableOperations.add(new AllOperation());
        return availableOperations;
    }
}
