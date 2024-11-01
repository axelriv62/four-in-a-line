package fr.univartois.butinfo.ihm.fourinaline.model;

public class Game {
    private Grid grid;
    private Token token;

    public Game(){
        grid = new Grid();
        token = Token.YELLOW;
        initGrid();
    }

    private void initGrid(){
        grid.clear();
    }

    public Grid getGrid(){
        return grid;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
