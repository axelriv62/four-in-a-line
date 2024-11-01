/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.fourinaline;

import java.io.IOException;
import java.util.Objects;

import fr.univartois.butinfo.ihm.fourinaline.model.FourInALineInterface;
import fr.univartois.butinfo.ihm.fourinaline.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * La classe FourInALineApplication est la classe principale du jeu du Puissance 4
 * fonctionnant avec JavaFX.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FourInALineApplication extends Application {

    /**
     * Cette méthode permet d'initialiser le jeu du Puissance 4 et son affichage dans la
     * fenêtre de l'application.
     *
     * @param stage La fenêtre (initialement vide) de l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Définir l'icône de l'application
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
        stage.getIcons().add(icon);

        // Il faut d'abord récupérer la description de la vue (au format FXML).
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./view/puissance4-view.fxml"));
        Parent viewContent = fxmlLoader.load();

        FourInALineInterface controller = fxmlLoader.getController();
        Game game = new Game();
        controller.setGame(game);

        // Ensuite, on la place dans la fenêtre.
        Scene scene = new Scene(viewContent);
        stage.setScene(scene);

        // On peut ensuite donner un titre à la fenêtre.
        stage.setTitle("Puissance 4");

        // Enfin, on affiche la fenêtre.
        stage.show();
    }

    /**
     * Cette méthode exécute l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

}
