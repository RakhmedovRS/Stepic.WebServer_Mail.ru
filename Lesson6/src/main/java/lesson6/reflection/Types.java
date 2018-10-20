package lesson6.reflection;

/**
 * @author rassoll
 * @created 05.10.2018
 */
public enum Types
{
	BYTE,
	BOOLEAN,
	SHORT,
	CHAR,
	INT,
	FLOAT,
	LONG,
	DOUBLE,
	STRING;

	public static Types getType(Class<?> clazz)
	{
		String className = clazz.getSimpleName().toUpperCase();
		return Types.valueOf(className);
	}
}
