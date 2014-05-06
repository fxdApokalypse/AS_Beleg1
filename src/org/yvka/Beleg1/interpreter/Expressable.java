package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.ui.Context;


public interface Expressable<T> {
	public T interpret(final Context context);
	
}
