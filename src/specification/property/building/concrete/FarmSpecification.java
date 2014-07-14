package specification.property.building.concrete;

import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.zone.ZoneType;
import economy.Product;

public class FarmSpecification extends BuildingSpecification {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.AGRICULTURAL);
		productForSale.setMaxQuantityForProduct(Product.UNPROCESSED_FOOD, 10);
		productForSale.setQuantityForProduct(Product.UNPROCESSED_FOOD, 0);
	}

	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.UNPROCESSED_FOOD, 1);
	}

	@Override
	public String getName() {
		return "Farm";
	}
}
