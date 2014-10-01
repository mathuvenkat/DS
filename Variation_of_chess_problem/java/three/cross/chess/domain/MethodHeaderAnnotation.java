package three.cross.chess.domain;

import java.lang.annotation.Documented;

@Documented
public @interface MethodHeaderAnnotation {
	
	String input();
	String comments();

}
