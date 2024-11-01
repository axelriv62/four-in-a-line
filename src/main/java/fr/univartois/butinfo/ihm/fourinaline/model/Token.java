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

package fr.univartois.butinfo.ihm.fourinaline.model;

/**
 * L'énumération Token représente les jetons utilisés dans le jeu
 * du Puissance 4.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public enum Token {

    /**
     * Le jeton "vide", représentant l'absence de jeton dans un emplacement
     * de la grille.
     */
    EMPTY,

    /**
     * Le jeton jaune.
     */
    YELLOW,

    /**
     * Le jeton rouge.
     */
    RED;

    /**
     * Donne le jeton qui suit celui-ci dans le déroulement d'une partie
     * du jeu du Puissance 4.
     *
     * @return Le jeton suivant celui-ci.
     */
    public Token next() {
        return switch (this) {
            case EMPTY -> EMPTY;
            case YELLOW -> RED;
            case RED -> YELLOW;
        };
    }

}
