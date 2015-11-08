public class City{
	String [] cities = {"Guatemala", "Antigua", "Chimaltenango", "Xela", "Flores", "Mixco"};

	public int getIndex(String city){
		for(int i=0; i<cities.length;i++){
			if(city.equals(cities[i]))
				return i;
		}
		return 0;
	}

	public String getLabel(int index){
		try{
			return cities[index];	
		}
		catch(Exception e){
			System.out.println("No existe una ciudad asignada a ese indice.");
			return "Error";
		}
		
	}

	public boolean contains(String city){
		for(int i=0; i<cities.length;i++){
			if(city.equals(cities[i]))
				return true;
		}
		return false;
	}

}
