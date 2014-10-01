package three.cross.chess.domain;

import java.lang.annotation.Documented;


@Documented
public @interface ClassHeaderAnnotation {

	String author();
	String date();
	String comments();
}
