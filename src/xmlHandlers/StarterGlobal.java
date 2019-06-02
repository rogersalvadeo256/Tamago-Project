package xmlHandlers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import randonStuff.GlobalStuff;

public class StarterGlobal {

	
	GlobalStuff glb = new GlobalStuff();
	
	
//	public StarterGlobal() {
//		// TODO Auto-generated constructor stub
//	
//	}
//	
	public void starter(GlobalStuff gl) {
	

		
		gl.setFirstTime(gl.isFirstTime());
		
		gl.setMethod(gl.getMethod());
		
		try {
			
			File file = new File("files/Global.xml");
			JAXBContext jc = JAXBContext.newInstance(GlobalStuff.class);
			Marshaller jm = jc.createMarshaller();

			// output pretty printed
			jm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jm.marshal(gl, file);
			//jm.marshal(gl, System.out);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
	
}
