package xmlHandlers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import tamago.Tamago;

public class StarterTamago {
	
	
	Tamago tmg = new Tamago();

	public void starter(Tamago ta) {
		
		
		tmg.setHappines(ta.getHappines());
		
		
		try {
			
			File file = new File("files/Tamago.xml");
			JAXBContext jc = JAXBContext.newInstance(Tamago.class);
			Marshaller jm = jc.createMarshaller();

			// output pretty printed
			jm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jm.marshal(ta, file);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
