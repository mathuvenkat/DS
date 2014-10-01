package three.cross.chess.domain;

import three.cross.chess.exception.DestroyException;

@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Interface with clean method to be implemented" 
		
			
)
public interface IDestroy {
	
	public void clean() throws DestroyException;

}
