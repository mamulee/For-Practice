package util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserController implements Initializable {

	@FXML
	private AnchorPane anchorpane;
	@FXML
	private ImageView imageview;
	
	@FXML
	private void handleSingleBtn (ActionEvent event) {
		
		FileChooser fc = new FileChooser();
		fc.setTitle("Open File Dialog");
		Stage stage = (Stage) anchorpane.getScene().getWindow();
		
		
		File file = fc.showOpenDialog(stage); // will return the selected file object
		Image image = new Image(file.toURI().toString());
		imageview.setImage(image);
		
//		if(file != null) {
//			Desktop desktop = Desktop.getDesktop();
//			try {
//				desktop.open(file); // computer will open the choosen file
//				
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	@FXML
	private void handleMultipleBtn (ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Multiple Files Dialog");
		Stage stage = (Stage) anchorpane.getScene().getWindow();
		
		List<File> list = fc.showOpenMultipleDialog(stage); // will return the list of choosen files
	
		if(list != null) {
			for(File file : list) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
