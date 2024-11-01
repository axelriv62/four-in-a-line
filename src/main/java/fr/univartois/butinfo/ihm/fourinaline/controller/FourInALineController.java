package fr.univartois.butinfo.ihm.fourinaline.controller;

import fr.univartois.butinfo.ihm.fourinaline.model.FourInALineInterface;
import fr.univartois.butinfo.ihm.fourinaline.model.Game;
import fr.univartois.butinfo.ihm.fourinaline.model.Token;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;
import java.util.Optional;
import java.util.Random;
import java.net.URL;

public class FourInALineController implements FourInALineInterface {

    private static final String ONE_PLAYER_MODE = "1 joueur";
    private static final String TWO_PLAYERS_MODE = "2 joueurs";
    private final Random random = new Random();

    @FXML
    private DialogPane dialogue;
    @FXML
    private GridPane mainGrid;
    @FXML
    private Button modeButton;

    private Button[] moveButtons;
    private ImageView[][] cells;

    private String mode;
    private Game game;

    @Override
    public void setGame(Game game){
        this.game = game;
    }

    public FourInALineController() {
        mode = ONE_PLAYER_MODE;
    }

    @Override
    @FXML
    public void initialize() {
        moveButtons = new Button[mainGrid.getColumnCount()];
        cells = new ImageView[mainGrid.getRowCount() - 1][mainGrid.getColumnCount()];

        for (Node child : mainGrid.getChildren()) {
            Integer row = GridPane.getRowIndex(child);
            if (row == null) {
                row = 0;
            }

            Integer column = GridPane.getColumnIndex(child);
            if (column == null) {
                column = 0;
            }

            if (child instanceof Button button) {
                moveButtons[column] = button;
                button.setDisable(false);

            } else if (child instanceof ImageView view) {
                cells[row - 1][column] = view;
            }
        }
        modeButton.setDisable(false);
        modeButton.setText(mode);
        game = new Game();
        showGrid();
        dialogue.setContentText("Nouvelle partie en mode " + mode);
    }

    @Override
    public void setDisable(boolean bool){
        for (Button moveButton : moveButtons) {
            moveButton.setDisable(bool);
        }
    }

    @Override
    @FXML
    public void columnChoice(ActionEvent event){
        Button button = (Button)event.getSource();
        Integer column = GridPane.getColumnIndex(button);

        if (column == null){
            column = 0;
        }

        game.getGrid().play(game.getToken(), column);
        showGrid();
        checkWinner();
        checkColumnFull(column);

        if(mode.equals(ONE_PLAYER_MODE) && game.getToken() == Token.RED){
            column = game.getGrid().play(game.getToken(), random.nextInt(7));
            showGrid();
            checkWinner();
            checkColumnFull(column);
        }


    }

    @Override
    public void checkColumnFull(Integer column){
        if (game.getGrid().columnIsFull(column)){
            moveButtons[column].setDisable(true);
        }
    }

    @Override
    public void checkWinner(){
        if (game.getGrid().isFull()){
            dialogue.setContentText("Match nul !");
            setDisable(true);
            modeButton.setDisable(true);
        }
        Optional<Token> result = game.getGrid().findFourInALine();
        if (result.isPresent()) {
            dialogue.setContentText("Le joueur " + result.get() + " est vainqueur !");
            setDisable(true);
            modeButton.setDisable(true);
        }
        else{
            game.setToken(game.getToken().next());
        }
    }

    @Override
    @FXML
    public void setMode(ActionEvent event){
        if (mode.equals(ONE_PLAYER_MODE)){
            mode = TWO_PLAYERS_MODE;
        }
        else{
            mode = ONE_PLAYER_MODE;
        }
        modeButton.setText(mode);
        dialogue.setContentText("Configuration en mode " + mode);
    }

    private Image loadImage(String name) {
        URL urlImage = getClass().getResource("../view/images/" + name + ".gif");
        assert urlImage != null;
        return new Image(urlImage.toExternalForm(), 50, 50, true, false);
    }

    private void showGrid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setImage(loadImage(game.getGrid().get(i,j).toString()));
            }
        }
    }
}