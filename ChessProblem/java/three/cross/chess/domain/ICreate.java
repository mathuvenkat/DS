package three.cross.chess.domain;

import three.cross.chess.exception.InitializeException;


@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Interface with init method to be implemented" 
		
			
)
public interface ICreate {
	
	void init() throws InitializeException;

}
