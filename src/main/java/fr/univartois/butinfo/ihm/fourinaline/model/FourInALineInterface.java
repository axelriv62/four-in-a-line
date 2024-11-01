package fr.univartois.butinfo.ihm.fourinaline.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Random;

public interface FourInALineInterface {

    void setGame(Game game);

    @FXML
    void initialize();

    void setDisable(boolean bool);

    @FXML
    void columnChoice(ActionEvent event);

    void checkColumnFull(Integer column);

    void checkWinner();

    @FXML
    void setMode(ActionEvent event);
}
