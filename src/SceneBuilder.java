import desirability.specification.DesirabilitySpecification;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import objects.City;
import objects.Cursor;
import objects.CursorType;
import objects.EventController;
import objects.Grid;
import objects.GridIterator;
import objects.Tile;
import specification.SpecificationEntity;
import specification.property.PoliceStationSpecification;
import specification.property.Property;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;


public class SceneBuilder {
	// Grid Pane Constructor and Grid Constraints UI Spacing
	// Returns GridPane to main scene Uses City for ini info
	// Style info set by gameGridStyle.css in resources.graphics
	// Event Handler for UI interaction
		Parent generateGridPane(City city, Cursor cursor) {
			
			GridPane gridPane = new GridPane();
			gridPane.setHgap(1);gridPane.setVgap(1);
			
			//Top Gap Spacers 
			//Top Left GridPane used for MouseOver info
			GridPane pnLeftGap = new GridPane();
			pnLeftGap.setMinSize(240, 135);
			pnLeftGap.setMaxSize(240, 135);
			pnLeftGap.setId("topLeftBar");
			gridPane.add(pnLeftGap, 0, 0);
			
			GridPane pnTopGap = new GridPane();
			pnTopGap.setMinSize(1440, 135);
			pnTopGap.setMaxSize(1440, 135);
			pnTopGap.setId("topBar");
			
			gridPane.add(pnTopGap, 1, 0);
			final Text txtType = new Text();
			txtType.setText("Tile Type: ");
			pnTopGap.add(txtType, 0, 0);
			
			
			final Pane pnTopRightGap = new Pane();
			pnTopRightGap.setMinSize(240, 135);
			pnTopRightGap.setMaxSize(240, 135);
			pnTopRightGap.setId("emptyTopRight");
			gridPane.add(pnTopRightGap, 2, 0);
			
			//Main Game GridPane set from City Event Controls for changing tile type
			Grid<DesirabilitySpecification> dGrid = city.getEconomy().getDesirabilityGrid();
			GridPane gpGameGrid = new GridPane();
			GridIterator<PropertySpecification> gridIterator = city.getGrid().iterator();
			while (gridIterator.hasNext()) {
				final SpecificationEntity<PropertySpecification> property = gridIterator.next();
				property.addSpecification(new TileSpecification(new Tile(gridIterator.getX(), gridIterator.getY())));
				property.addSpecification(EventController.makeMouseMovedTileEvent(cursor, property));
				property.addSpecification(EventController.makeMouseExitedTileEvent(property));
				property.addSpecification(EventController.makeMousePressedTileEvent(cursor, property, dGrid, city.getGrid()));
				property.addSpecification(EventController.makeMouseMovedTileTextEvent(txtType, property, dGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY())));
				property.addSpecification(EventController.makeMousePressedTileTextEvent(txtType, property, dGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY())));
				Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
				//Add Pane to GameGrid
				gpGameGrid.add(pane, gridIterator.getX(), gridIterator.getY());	 
			}
			gridPane.add(gpGameGrid, 1, 1);
			
			 
			//Control GridPane 
			GridPane gpControlGrid = new GridPane();
			gpControlGrid.setId("controlGrid");
			gpControlGrid.setMinSize(235, 540);
			gridPane.add(gpControlGrid, 2, 1);
			
			//Empty Zone Button Setup and Event Handle
			Button emptyBtn = new Button();
			emptyBtn.setId("emptyButton");
			emptyBtn.setMinSize(235,30);
			emptyBtn.setMaxSize(235,30);
			emptyBtn.setText("Empty Hand");
			//Event Controller Call Zone Empty
			EventController.setButtonEvents(emptyBtn, pnTopRightGap, cursor, CursorType.ZONE_EMPTY, null);
			//Add Button To Control Grid
			gpControlGrid.add(emptyBtn, 0,0);
			
			//Zone Residential Button Setup and Event handle
			Button resBtn = new Button();
			resBtn.setId("resButton");
			resBtn.setMinSize(235,30);
			resBtn.setMaxSize(235,30);
			resBtn.setText("Zone Residential");
			//Event Controller Call Zone Residential
			EventController.setButtonEvents(resBtn, pnTopRightGap, cursor, CursorType.ZONE_RESIDENTIAL, null);
			//Add Button to Control Grid
			gpControlGrid.add(resBtn, 0,1);
			
			//Zone Commercial Button Setup and Event handle
			Button commBtn = new Button();
			commBtn.setId("commButton");
			commBtn.setMinSize(235,30);
			commBtn.setMaxSize(235,30);
			commBtn.setText("Zone Commercial");
			EventController.setButtonEvents(commBtn, pnTopRightGap, cursor, CursorType.ZONE_COMMERCIAL, null);
			//Add Button to Control Grid
			gpControlGrid.add(commBtn, 0, 2);
			
			//Zone Industrial Button Setup and Event handle
			Button indsBtn = new Button();
			indsBtn.setId("indsButton");
			indsBtn.setMinSize(235,30);
			indsBtn.setMaxSize(235,30);
			indsBtn.setText("Zone Industrial");
			EventController.setButtonEvents(indsBtn, pnTopRightGap, cursor, CursorType.ZONE_INDUSTRIAL, null);
			//Add Button to Control Grid
			gpControlGrid.add(indsBtn, 0, 3);
			
			//Zone Industrial Button Setup and Event handle
			Button policeStationBtn = new Button();
			policeStationBtn.setId("policeButton");
			policeStationBtn.setMinSize(235,30);
			policeStationBtn.setMaxSize(235,30);
			policeStationBtn.setText("Police Station");
			EventController.setButtonEvents(policeStationBtn, pnTopRightGap, cursor, CursorType.POLICE_STATION, PoliceStationSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(policeStationBtn, 0, 4);
			
			//Bulldoze Button Setup and Event handle
			Button bulldozeBtn = new Button();
			bulldozeBtn.setId("bulldozeButton");
			bulldozeBtn.setMinSize(235,30);
			bulldozeBtn.setMaxSize(235,30);
			bulldozeBtn.setText("Bulldoze Zone");
			//Event Controller Call Zone Bulldoze
			EventController.setButtonEvents(bulldozeBtn, pnTopRightGap, cursor, CursorType.ZONE_BULLDOZE, null);
			//Add Button To Control Grid
			gpControlGrid.add(bulldozeBtn, 0, 5);
			return gridPane;
		}
}
