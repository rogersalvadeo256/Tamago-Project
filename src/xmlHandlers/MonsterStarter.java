package xmlHandlers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import tamago.Monster;

public class MonsterStarter {

	Monster ms = new Monster();

	public void starter(Monster mst) {

		ms.setAge(mst.getAge());
		ms.setBath(mst.getBath());
		ms.setDiscipline(mst.getDiscipline());
		ms.setFood(mst.getFood());
		ms.setHp(mst.getHp());
		ms.setName(mst.getName());
		ms.setPoop(mst.getPoop());
		ms.setSpecies(mst.getSpecies());
		ms.setType(mst.getType());
		ms.setWater(mst.getWater());
		ms.setWeight(mst.getWeight());

		try {

			File file = new File("files/Monster.xml");
			JAXBContext jc = JAXBContext.newInstance(Monster.class);
			Marshaller jm = jc.createMarshaller();

			// output pretty printed
			jm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jm.marshal(ms, file);
			jm.marshal(ms, System.out);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
