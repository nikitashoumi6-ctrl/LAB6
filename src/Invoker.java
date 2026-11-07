import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {
    public static void main(String[] args) throws Exception {
        MyClass obj = new MyClass();

        for (Method method : MyClass.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Repeat.class)) {
                //вызываем только protected и private методы
                if (Modifier.isProtected(method.getModifiers()) ||
                        Modifier.isPrivate(method.getModifiers())) {

                    Repeat r = method.getAnnotation(Repeat.class);
                    method.setAccessible(true); //разрешаем вызов приватных методов

                    //формируем аргументы по типам параметров
                    Class<?>[] paramTypes = method.getParameterTypes();
                    Object[] methodArgs = new Object[paramTypes.length];
                    for (int i = 0; i < paramTypes.length; i++) {
                        if (paramTypes[i] == String.class) {
                            methodArgs[i] = "test";
                        } else if (paramTypes[i] == int.class || paramTypes[i] == Integer.class) {
                            methodArgs[i] = 42;
                        } else if (paramTypes[i] == double.class || paramTypes[i] == Double.class) {
                            methodArgs[i] = 3.14;
                        }
                    }

                    //вызываем метод нужное кол-тво раз
                    for (int i = 0; i < r.times(); i++) {
                        method.invoke(obj, methodArgs);
                    }
                }
            }
        }
    }
}