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

import java.util.Optional;

/**
 * La classe Grid représente la grille du jeu du Puissance 4, dans laquelle les joueurs
 * peuvent placer des jetons.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Grid {

    /**
     * Le nombre de lignes dans la grille du jeu.
     */
    public static final int ROWS = 6;

    /**
     * Le nombre de colonnes dans la grille du jeu.
     */
    public static final int COLUMNS = 7;

    /**
     * Le nombre de jetons à aligner pour gagner.
     */
    public static final int TO_ALIGN = 4;

    /**
     * Les jetons actuellement dans la grille.
     * Une case est vide si elle contient la valeur {@link Token#EMPTY}.
     */
    private Token[][] tokens;

    /**
     * Crée une nouvelle instance de Grid, initialement vide.
     */
    public Grid() {
        this.tokens = new Token[ROWS][COLUMNS];
        clear();
    }

    /**
     * Vide la grille du jeu, en retirant tous les jetons qui s'y trouvent.
     */
    public void clear() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                tokens[i][j] = Token.EMPTY;
            }
        }
    }

    /**
     * Joue un jeton dans la colonne donnée.
     *
     * @param token Le jeton à jouer.
     * @param column La colonne dans laquelle jouer le jeton.
     *
     * @return La ligne dans laquelle le jeton a été joué, ou -1 si la colonne est pleine.
     */
    public int play(Token token, int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (tokens[i][column] == Token.EMPTY) {
                tokens[i][column] = token;
                return i;
            }
        }
        return -1;
    }

    /**
     * Récupère le jeton à la position donnée.
     *
     * @param row La ligne du jeton à récupérer.
     * @param column La colonne du jeton à récupérer.
     *
     * @return Le jeton à la position donnée.
     */
    public Token get(int row, int column) {
        return tokens[row][column];
    }

    /**
     * Détermine si la grille est pleine.
     *
     * @return Si la grille ne contient plus de cases vides.
     */
    public boolean isFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (tokens[i][j] == Token.EMPTY) {
                    // Il reste une case vide.
                    return false;
                }
            }
        }

        // La grille est pleine.
        return true;
    }

    // ajout personnel
    public boolean columnIsFull(int column) {
        return tokens[0][column] != Token.EMPTY;
    }

    /**
     * Détermine si un joueur a aligné 4 jetons.
     *
     * @return Le jeton du joueur ayant aligné 4 jetons, ou {@link Optional#empty()} si
     *         aucun joueur n'a aligné 4 jetons.
     */
    public Optional<Token> findFourInALine() {
        if (getMaxAligned(Token.RED) >= TO_ALIGN) {
            return Optional.of(Token.RED);

        } else if (getMaxAligned(Token.YELLOW) >= TO_ALIGN) {
            return Optional.of(Token.YELLOW);
        }

        return Optional.empty();
    }

    /**
     * Détermine le nombre maximal de jetons alignés pour un joueur donné.
     *
     * @param token Le jeton du joueur pour lequel déterminer le nombre maximal de jetons
     *        alignés.
     *
     * @return Le nombre maximal de jetons alignés pour le joueur donné.
     */
    public int getMaxAligned(Token token) {
        int max = 0;

        // On cherche le nombre maximum de jetons dans une ligne.
        int tmp = getMaxInARow(token);
        if (tmp > max) {
            max = tmp;
        }

        // On cherche le nombre maximum de jetons dans une colonne.
        tmp = getMaxInAColumn(token);
        if (tmp > max) {
            max = tmp;
        }

        // On cherche le nombre maximum de jetons dans une diagonale.
        tmp = getMaxInADiagonal(token);
        if (tmp > max) {
            max = tmp;
        }

        return max;
    }

    /**
     * Détermine le nombre maximal de jetons alignés dans une ligne.
     *
     * @param token Le jeton à compter.
     *
     * @return Le nombre maximal de jetons alignés.
     */
    private int getMaxInARow(Token token) {
        int max = 0;

        for (int i = 0; i < Grid.ROWS; i++) {
            int inRow = 0;

            for (int j = 0; j < Grid.COLUMNS; j++) {
                if (tokens[i][j] == token) {
                    inRow++;

                } else {
                    max = Math.max(max, inRow);
                    inRow = 0;
                }
            }

            max = Math.max(max, inRow);
        }

        return max;
    }

    /**
     * Détermine le nombre maximal de jetons alignés dans une colonne.
     *
     * @param token Le jeton à compter.
     *
     * @return Le nombre maximal de jetons alignés.
     */
    private int getMaxInAColumn(Token token) {
        int max = 0;

        for (int i = 0; i < Grid.COLUMNS; i++) {
            int inColumn = 0;

            for (int j = 0; j < Grid.ROWS; j++) {
                if (tokens[j][i] == token) {
                    inColumn++;

                } else {
                    max = Math.max(max, inColumn);
                    inColumn = 0;
                }
            }

            max = Math.max(max, inColumn);
        }

        return max;
    }

    /**
     * Détermine le nombre maximal de jetons alignés dans une diagonale.
     *
     * @param token Le jeton à compter.
     *
     * @return Le nombre maximal de jetons alignés.
     */

    private int getMaxInADiagonal(Token token) {
        return Math.max(getMaxInMainDiagonal(token), getMaxInCounterDiagonal(token));
    }

    /**
     * Détermine le nombre maximal de jetons alignés dans la diagonale principale.
     *
     * @param token Le jeton à compter.
     *
     * @return Le nombre maximal de jetons alignés.
     */
    private int getMaxInMainDiagonal(Token token) {
        int max = 0;

        for (int i = 0; i <= (Grid.ROWS - TO_ALIGN); i++) {
            for (int j = (Grid.COLUMNS - 1); (TO_ALIGN - 1) <= j; j--) {
                int inDiagonal = 0;

                // On regarde les jetons alignés sur la diagonale finissant en (i, j).
                for (int k = 0; ((i + k) < Grid.ROWS) && (0 <= (j - k)); k++) {
                    if (tokens[i + k][j - k] == token) {
                        inDiagonal++;

                    } else {
                        max = Math.max(max, inDiagonal);
                        inDiagonal = 0;
                    }
                }

                max = Math.max(max, inDiagonal);
            }
        }

        return max;
    }

    /**
     * Détermine le nombre maximal de jetons alignés dans la diagonale inversée.
     *
     * @param token Le jeton à compter.
     *
     * @return Le nombre maximal de jetons alignés.
     */
    private int getMaxInCounterDiagonal(Token token) {
        int max = 0;

        for (int i = 0; i <= (Grid.ROWS - TO_ALIGN); i++) {
            for (int j = 0; j <= (Grid.COLUMNS - TO_ALIGN); j++) {
                int inDiagonal = 0;

                // On regarde les jetons alignés sur la diagonale démarrant en (i, j).
                for (int k = 0; ((i + k) < Grid.ROWS) && ((j + k) < Grid.COLUMNS); k++) {
                    if (tokens[i + k][j + k] == token) {
                        inDiagonal++;

                    } else {
                        max = Math.max(max, inDiagonal);
                        inDiagonal = 0;
                    }
                }

                max = Math.max(max, inDiagonal);
            }
        }

        return max;
    }

}
