package design.pattern.structural;

import common.helper.entity.Entity;

import java.util.Arrays;
import java.util.stream.IntStream;


/**
 * {@link CompositeEntity} allows a set of related objects to be represented and managed by a unified object
 * */
public interface CompositeEntity {}

abstract class DependentEntity<T> implements Entity {
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}

abstract class CoarseGrainedEntity<T> {
    protected DependentEntity<T>[] dependentEntities;

    public void setData(T[] data) {
        IntStream.of(0, data.length - 1).forEach(i -> dependentEntities[i].setData(data[i]));
    }
}

class MessageDependentEntity extends DependentEntity<String> {}

class SignalDependentEntity extends DependentEntity<String> {}

class ConsoleCoarseGrainedEntity extends CoarseGrainedEntity<String> {
    public ConsoleCoarseGrainedEntity() {
        super.dependentEntities = new DependentEntity[]{ new MessageDependentEntity(), new SignalDependentEntity() };
    }

    public String[] getData() {
        return new String[]{ dependentEntities[0].getData(), dependentEntities[1].getData() };
    }

    @Override
    public void setData(String... data) {
        super.setData(data);
    }
}

class ConsoleCompositeEntity implements CompositeEntity {
    private final ConsoleCoarseGrainedEntity consoleCoarseGrainedEntity = new ConsoleCoarseGrainedEntity();

    public void setData(String message, String signal) {
        consoleCoarseGrainedEntity.setData(message, signal);
    }

    public String[] getData() {
        return consoleCoarseGrainedEntity.getData();
    }

    public static void main(String[] args) {
        ConsoleCompositeEntity compositeEntity = new ConsoleCompositeEntity();

        compositeEntity.setData("message", "signal");

        System.out.println(Arrays.toString(compositeEntity.getData()));
    }
}