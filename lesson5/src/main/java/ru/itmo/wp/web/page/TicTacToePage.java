package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import java.util.Map;

public class TicTacToePage {

    private static final int BOARD_SIZE = 3;
     private enum Phase {
        RUNNING,
        WON_X,
        WON_O,
        DRAW
    }

    public static class State {
        private int movesCount;
        public final int size;
        public  char[][] cells = new char[BOARD_SIZE][BOARD_SIZE];
        public boolean crossesMove = true;
        public String phase;
        public State(int size) {
            this.size = size;
            phase = "RUNNING";
        }
    }

    public State state = new State(BOARD_SIZE);
    public void onMove(final HttpServletRequest request, Map<String, Object> view) {
        int x = -1;
        int y = -1;
        for (Map.Entry param : request.getParameterMap().entrySet()) {
            String key = (String) param.getKey();
            if (key.startsWith("cell")) {
                y = key.charAt(key.length() - 1) - '0';
                x = key.charAt(key.length() - 2) - '0';
            }
        }
        if (x != -1) {
            makeMove(this.state, x, y);
        }
        view.put("state", this.state);
    }


    private static void makeMove(State state, int x, int y) {
        if (x < 0 || x > state.size - 1 || y < 0 || y > state.size - 1
                || state.cells[x][y] != '\u0000') {
            return;
        }
        state.cells[x][y] = playerSign(state);
        state.crossesMove = !state.crossesMove;
        state.movesCount++;
        state.phase = checkWinner(state, x, y) ? "WON_" + playerSign(state) : state.phase;
        state.phase = checkDraw(state) ? "DRAW" : state.phase;
    }

    private static char playerSign(final State state) {
         return state.crossesMove ? 'X' : 'O';
    }

    private static boolean checkWinner(final State state, int x, int y) {
        final char playerSign = state.crossesMove ? 'X' : 'O';
        int cntRow = 0;
        int cntCol = 0;
        int cntMainDiag = 0;
        int cntSideDiag = 0;

        for (int delt = 1; delt < state.size; delt++) {
            cntRow += (state.cells[x + delt][y] == playerSign ? 1 : 0) +
                    (state.cells[x - delt][y] == playerSign ? 1 : 0);

            cntCol += (state.cells[x][y + delt] == playerSign ? 1 : 0) +
                    (state.cells[x][y - delt] == playerSign ? 1 : 0);

            cntMainDiag += (state.cells[x + delt][y + delt] == playerSign ? 1 : 0) +
                    (state.cells[x - delt][y - delt] == playerSign ? 1 : 0);

            cntSideDiag += (state.cells[x + delt][y - delt] == playerSign ? 1 : 0) +
                    (state.cells[x - delt][y + delt] == playerSign ? 1 : 0);
        }

        return cntRow == state.size || cntCol == state.size ||
                cntMainDiag == state.size || cntSideDiag == state.size;
    }

    private static boolean checkDraw(final State state) {
         return state.movesCount == state.size * state.size;
    }
}
