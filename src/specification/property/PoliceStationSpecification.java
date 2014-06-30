package specification.property;

import specification.desirability.PoliceSpecification;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, ZoneType.URBAN_SERVICE);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
