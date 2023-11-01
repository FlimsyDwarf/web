package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

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
        public  Character[][] cells = new Character[BOARD_SIZE][BOARD_SIZE];
        public boolean crossesMove;
        public String phase;

        public int getMovesCount() {
            return movesCount;
        }

        public Character[][] getCells() {
            return cells;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public String getPhase() {
            return phase;
        }

        public int getSize() {
            return size;
        }

        public State(int size) {
            this.size = size;
            phase = "RUNNING";
            crossesMove = true;
            movesCount = 0;
        }
    }

    public static State state;

     public static void action(HttpServletRequest request, Map<String, Object> view) {
         newGame(request, view);
     }
    public static void newGame(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        state = new State(BOARD_SIZE);
        view.put("state", state);
        session.setAttribute("state", state);
    }
    public static void onMove(HttpServletRequest request, Map<String, Object> view) {
        int x = -1;
        int y = -1;
        HttpSession session = request.getSession();
        state = (State) session.getAttribute("state");
        if (state == null) {
            newGame(request, view);
            return;
        }
        for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            String key = param.getKey();
            if (key.startsWith("cell")) {
                y = key.charAt(key.length() - 1) - '0';
                x = key.charAt(key.length() - 2) - '0';
            }
        }
        if (x != -1) {
            makeMove(state, x, y);
        }
        view.put("state", state);
        session.setAttribute("state", state);
    }


    private static void makeMove(State state, int x, int y) {
        if (!Objects.equals(state.phase, "RUNNING")) {
            return;
        }
        if (x < 0 || x > state.size - 1 || y < 0 || y > state.size - 1
                || state.cells[x][y] != null) {
            return;
        }
        state.cells[x][y] = playerSign(state);
        state.movesCount++;
        state.phase = checkDraw(state) ? "DRAW" : state.phase;
        state.phase = checkWinner(state, x, y) ? "WON_" + playerSign(state) : state.phase;
        state.crossesMove = !state.crossesMove;
    }

    private static char playerSign(final State state) {
         return state.crossesMove ? 'X' : 'O';
    }

    private static boolean checkWinner(final State state, int x, int y) {
        return check(state, 0, y, 1, 0) || check(state, x, 0, 0, 1) ||
                check(state, 0, 0, 1, 1) || check(state, state.size - 1, 0, -1, -1);
    }

    private static boolean check(State state, int x, int y, int deltX, int deltY) {
        int sum = 0;
        final Character playerSign = state.crossesMove ? 'X' : 'O';
        for ( ; 0 <= x && x < state.size && 0 <= y && y < state.size; x += deltX, y += deltY) {
            sum += (state.cells[x][y] == playerSign ? 1 : 0);
        }
        return sum == state.size;
    }

    private static boolean checkDraw(final State state) {
         return state.movesCount == state.size * state.size;
    }
}
