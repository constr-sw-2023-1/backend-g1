package constsw.grupoum.courses.adapter.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import constsw.grupoum.courses.adapter.exception.InvalidAtributeException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReflectionUtils {

    public Class<?> getComplexFieldClass(Class<?> clazz, String field) throws InvalidAtributeException {
        Queue<String> fieldsQueue = new LinkedList<String>(Arrays.asList(field.split("\\.")));
        return recursive(clazz, fieldsQueue);
    }

    private Class<?> recursive(Class<?> clazz, Queue<String> fieldsQueue) throws InvalidAtributeException {
        if (fieldsQueue.isEmpty())
            return clazz;

        return recursive(getFieldClass(clazz, fieldsQueue.poll()), fieldsQueue);
    }

    public Class<?> getFieldClass(Class<?> clazz, String field) throws InvalidAtributeException {
        try {
            return clazz.getDeclaredField(field).getType();
        } catch (NoSuchFieldException | SecurityException e) {
            throw new InvalidAtributeException(
                    String.format("%s has no atribute %s", clazz.getSimpleName(), field), e);
        }
    }

}
