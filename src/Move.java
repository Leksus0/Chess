public class Move {

    public Coordinate coord_now;
    public Coordinate coord_next;
    public char player;
    public char figure;

    public boolean correct;

    boolean within_field() {

        boolean a = (0 <= coord_now.i) && (coord_now.i <= 7);
        boolean b = (0 <= coord_now.j) && (coord_now.j <= 7);
        boolean c = (0 <= coord_next.i) && (coord_next.i <= 7);
        boolean d = (0 <= coord_next.j) && (coord_next.j <= 7);

        return a && b && c && d;
    } //в границах игрового поля ход
    public static char get_figure(char player, char symbol) {
        char figure = ' '; // ' ' + 6 = '&'
        switch (symbol) {
            case 'R': figure = '♔'; break; // 9812
            case 'Q': figure = '♕'; break;
            case 'L': figure = '♖'; break;
            case 'S': figure = '♗'; break;
            case 'K': figure = '♘'; break;
            case 'P': figure = '♙'; break;
        }

        if (player == 'w') figure += 6; // потому что черные начинаются с 9812, а белые с 9818

        return figure;
    }
    boolean no_same_thing() {
        return !((coord_now.i == coord_next.i) && (coord_now.j == coord_next.j));
    }
    // ходим не в то же самое место

    public Move(String moveString, char player) {
        // P - пешка, L - ладья, K - конь, S - слон, Q - королева, R - король
        // формат: Pc7 c6
        if (moveString.length() == 6) {
            this.player = player;

            figure = get_figure(player, moveString.charAt(0));

            coord_now = new Coordinate();
            coord_now.i = moveString.charAt(2) - '1';
            coord_now.j = moveString.charAt(1) - 'a';

            coord_next = new Coordinate();
            coord_next.i = moveString.charAt(5) - '1';
            coord_next.j = moveString.charAt(4) - 'a';

            coord_now.i = 7 - coord_now.i; // потому что черные вверху игрового поля
            coord_next.i = 7 - coord_next.i;

            correct = within_field() && no_same_thing() && figure != '&'; // корректность фигуры
        }
        else
            correct = false;

        if (!correct) System.out.println("\nIncorrect move.\n");
    }
    public Move(int i_now, int j_now, int i_next, int j_next, char figure, char player) {
        coord_now.i = i_now;
        coord_now.j = j_now;
        coord_next.i = i_next;
        coord_next.j = j_next;

        this.figure = figure;
        this.player = player;
        correct = true;
    }
}
