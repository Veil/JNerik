package net.aphotix.jnerik.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nathan on 24/03/2016.
 */
public class ModuleConstructor {

    private final Map<Class<?>, Object> supportedInjections;

    public ModuleConstructor(Object... injections) {
        supportedInjections = new HashMap<>();

        for (Object injection : injections) {
            supportedInjections.put(injection.getClass(), injection);
        }
    }

    public Module construct(Class<Module> module) throws InstantiationException {
        Constructor<?>[] constructors = module.getDeclaredConstructors();

        if (constructors.length != 1) {
            throw new InstantiationException(String.format("Cannot load %s due to an incorrect number of constructors. " +
                    "Number expected: 1, Number found: %s", module.getSimpleName(), constructors.length));
        }

        final Constructor<?> moduleConstructor = constructors[0];

        final List<Object> args = new ArrayList<>();

        for (Class<?> type : moduleConstructor.getParameterTypes()) {
            final Object injection = supportedInjections.get(type);

            if (injection == null) {
                throw new InstantiationException(String.format("Cannot load %s due to an unknown constructor parameter type %s.",
                        module.getSimpleName(), type.getSimpleName()));
            }

            args.add(injection);
        }

        try {
            return (Module) moduleConstructor.newInstance(args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            final InstantiationException wrapped = new InstantiationException();
            wrapped.initCause(e);
            throw wrapped;
        }
    }

}
