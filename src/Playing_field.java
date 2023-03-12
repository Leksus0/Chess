import java.util.ArrayList;
import java.util.Arrays;

public class Playing_field {
    char [][] field;
    ArrayList<Move> history_moves;
    ArrayList<Character> eating_white_figures;
    ArrayList<Character> eating_black_figures;

    static String white_figures = "♚♛♜♝♞♟";
    static String black_figures = "♔♕♖♗♘♙";

    public Playing_field() {    //        0,2                   0,6
        field = new char[][]{{'♖', '♘', '♗', '♕', '♔', '♗', '♘', '♖'},
                             {'♙', '♙', '♙', '♙', '♙', '♙', '♙', '♙'},
                             {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                             {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                             {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                             {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                             {'♟', '♟', '♟', '♟', '♟', '♟', '♟', '♟'},
                             {'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜'}};
                            //           7,2                    7,6

        history_moves = new ArrayList<>();
        eating_white_figures = new ArrayList<>();
        eating_black_figures = new ArrayList<>();

        // String s = "♔♕♖♗♘♙♚♛♜♝♞♟";
    }
    public void Test_field(){
        field = new char[][]{{'♖', '♘', '♗', '♕', '♔', '♗', '♘', '♖'},
                {'♙', '♙', '♙', '♙', '♙', '♙', '♙', '♙'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'♟', '♟', '♟', '♟', '♟', '♟', '♟', '♟'},
                {'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜'}};
    }
    public void show(char player) {
        if (player == 'w') {
            for (int i = 0; i < 8; i++) {
                System.out.print((8 - i) + " ");

                for (int j = 0; j < 8; j++)
                    if (field[i][j] != ' ')
                        System.out.print(field[i][j]);
                    else
                        System.out.print('@');
                System.out.println("");
            }
            System.out.println("  a b cd ef gh");
        } // вывод для белых
        else {
            for (int i = 7; i >= 0; i--) {
                System.out.print((8 - i) + " ");

                for (int j = 7; j >= 0; j--)
                    if (field[i][j] != ' ')
                        System.out.print(field[i][j]);
                    else
                        System.out.print('@');
                System.out.println();
            }
            System.out.println("  h g fe dc ba");
        } // вывод для черных
        System.out.println();
    }

    boolean figure_belong_player(char figure, char player) {
        char color_figure = (figure < 9818) ? 'b' : 'w';

        return (color_figure == player && figure != ' ');
    }

    Coordinate get_coord_figure(char figure) {
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (field[i][j] == figure)
                    return new Coordinate(i,j);

        return new Coordinate();
    }
    boolean is_Check_to_player(char player) {

        // <editor-fold defaultstate="collapsed" desc="Ищем короля">
        char figure_king = ' ';
        if (player == 'w') figure_king = '♚';
        else figure_king = '♔';

        Coordinate coord_king = get_coord_figure(figure_king);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="идем по полю и ищем">
        // <editor-fold defaultstate="collapsed" desc="идем налево по х">
        for (int j=coord_king.j-1; j>=0; j--)
            if (field[coord_king.i][j] != ' ')
                if (figure_belong_player(field[coord_king.i][j],player)) break;
                else return true;

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем направо по х">
        for (int j=coord_king.j+1; j<8; j++)
            if (field[coord_king.i][j] != ' ')
                if (figure_belong_player(field[coord_king.i][j],player)) break;
                else return true;

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем вверх по y">
        for (int i=coord_king.i-1; i>=0; i--)
            if (field[i][coord_king.j] != ' ')
                if (figure_belong_player(field[i][coord_king.j], player)) break;
                else return true;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем вниз по y">
        for (int i=coord_king.i+1; i<8; i++)
            if (field[i][coord_king.j] != ' ')
                if (figure_belong_player(field[i][coord_king.j], player)) break;
                else return true;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="идем по 'главной' диагонали вверх">
        for (int i=coord_king.i-1, j=coord_king.j-1; (i>=0 && j>=0); i--, j--)
            if (field[i][j] != ' ')
                if (figure_belong_player(field[i][j], player)) break;
                else return true;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем по 'главной' диагонали вниз">
        for (int i=coord_king.i+1, j=coord_king.j+1; (i<8 && j<8); i++, j++)
            if (field[i][j] != ' ')
                if (figure_belong_player(field[i][j], player)) break;
                else return true;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем по 'побочной' диагонали вверх">
        for (int i=coord_king.i-1, j=coord_king.j+1; (i>=0 && j<8); i--, j++)
            if (field[i][j] != ' ')
                if (figure_belong_player(field[i][j], player)) break;
                else return true;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="идем по 'побочной' диагонали вниз">
        for (int i=coord_king.i+1, j=coord_king.j-1; (i<8 && j>=0); i++, j--)
            if (field[i][j] != ' ')
                if (figure_belong_player(field[i][j], player)) break;
                else return true;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="проверяем удары коней">
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (Coordinate.distance(new Coordinate(i,j), coord_king) == Math.sqrt(5) &&
                    ((player == 'w' && field[i][j] == '♘') || (player == 'b' && field[i][j] == '♞')))
                        return true;
        // </editor-fold>
        // </editor-fold>

        //если не нашли то
        return false;
    } //проверка на шах

    boolean at_least_one_acceptable_move(ArrayList<Move> player_figures) {
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                for (Move figure : player_figures) {
                    figure.coord_next.i = i;
                    figure.coord_next.j = j;

                    if (is_Acceptable_move(figure))
                        return true;
                }

        return false;
    }
    public Situation calculate_situation_game(char player) {
        //loss - сейчас шах и куда не пойти будет шах
        //draw - сейчас нет шаха, но если куда-то пойти, будет шах
        //Nothing

        //надо найти хоть один допустимый ход из пласта фигур игрока
        //тогда это не мат и не ничья
        ArrayList<Move> player_figures = new ArrayList<>();
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (figure_belong_player(field[i][j], player))
                    player_figures.add(new Move(i, j,0,0, field[i][j], player));

        //в случае если нет допустимых ходов, то это либо мат либо ничья
        if (!at_least_one_acceptable_move(player_figures))
            return is_Check_to_player(player) ? Situation.LOSS : Situation.DRAW;

        return Situation.NOTHING;
    } //выдача ситуации на поле

    public static char[][] clone(char[][] matrix) {
        return Arrays.stream(matrix).map(char[]::clone).toArray(char[][]::new);
    }
    boolean was_there_such_a_move(int i, int j, char figure) {
        for (Move move: history_moves)
            if (move.coord_now.i == i && move.coord_now.j == j && move.figure == figure)
                return true;

        return false;
    }
    //поиск по истории: был ли ход с данной позиции, этой фигурой
    boolean Can_move_like_this(Move move) {
        char figure = (move.player == 'b') ? move.figure : (char)(move.figure - 6);

        if (figure == '♔') {
            double length_move = Coordinate.distance(move.coord_now, move.coord_next);

            //обычный ход
            if (length_move <= Math.sqrt(2)) return true;

            //ход курильщика (рокировка)
            if (length_move == 2) {
                boolean king_walked = true, rook_walked = true;

                if (move.coord_next.i == 0 && move.coord_next.j == 2) {
                    king_walked = was_there_such_a_move(0,4,'♔');
                    rook_walked = was_there_such_a_move(0,0,'♖');
                }
                if (move.coord_next.i == 0 && move.coord_next.j == 6) {
                    king_walked = was_there_such_a_move(0,4,'♔');
                    rook_walked = was_there_such_a_move(0,7,'♖');
                }
                if (move.coord_next.i == 7 && move.coord_next.j == 2) {
                    king_walked = was_there_such_a_move(7,4,'♚');
                    rook_walked = was_there_such_a_move(7,0,'♜');
                }
                if (move.coord_next.i == 7 && move.coord_next.j == 6) {
                    king_walked = was_there_such_a_move(7,4,'♚');
                    rook_walked = was_there_such_a_move(7,7,'♜');
                }

                boolean figures_didnt_walk = !king_walked && !rook_walked;
                boolean is_not_Check = is_Check_to_player(move.player);

                //при рокировке: не должно быть шаха и король с ладьёй не должны были ходить
                return is_not_Check && figures_didnt_walk;
            }

            return false;
        }
        if (figure == '♙') {
            //просто ходит
            //первый ход на два
            //бьёт
            double length_move = Coordinate.distance(move.coord_now, move.coord_next);
            double direction_move = move.coord_next.i-move.coord_now.i; //направление хода относительно зад. доски
            if (!((move.player == 'w' && direction_move < 0)
                || (move.player == 'b' && direction_move > 0))) return false;

            if (length_move == 1) {
                //обычный ход
                //ходим на пустую клетку
                return field[move.coord_now.i][move.coord_now.j] == ' ';
            }
            if (length_move == 2) {
                //ход на два
                //на двух клетках впереди нет фигур
                // ток с нач. позиции
                return (move.player == 'w' && move.coord_now.i == 6 &&
                        field[move.coord_now.i-1][move.coord_now.j] == ' ' &&
                        field[move.coord_now.i-2][move.coord_now.j] == ' ')
                        ||
                        (move.player == 'b' && move.coord_now.i == 1 &&
                        field[move.coord_now.i+1][move.coord_now.j] == ' ' &&
                        field[move.coord_now.i+2][move.coord_now.j] == ' ');
            }
            if (length_move == Math.sqrt(2)) {
                //удар или взятие на проходе
                char enemy_figure = field[move.coord_next.i][move.coord_next.j];
                if (!figure_belong_player(enemy_figure, move.player))
                    if (enemy_figure != ' ') return true;
                    else {
                        //взятие на проходе:
                        //относительно coord_next есть ли ниже пешка противника? если да, то запоминаем её координату
                        //в случае coord_now белых игрок на координате 3?
                        //в случае coord_now черных игрок на координате 4?
                        //прошлый ход = ход противника был пешкой на два хода на запомненную координату?

                        char opponent_pawn = Move.get_figure(Other_functions.opponent(move.player), 'P');
                        Move last_move = history_moves.get(history_moves.size()-1);

                        if (move.player == 'w')
                            if (field[move.coord_next.i+1][move.coord_next.j] == opponent_pawn) {
                                Coordinate coord_opponent_pawn = move.coord_next;
                                coord_opponent_pawn.i += 1;

                                return move.coord_now.i == 3 &&
                                       last_move.figure == opponent_pawn &&

                                       last_move.coord_now.j == last_move.coord_next.j &&
                                       last_move.coord_now.i+2 == last_move.coord_next.i &&
                                       last_move.coord_next.equal(coord_opponent_pawn);
                            }
                        else
                            if (field[move.coord_next.i-1][move.coord_next.j] == opponent_pawn) {
                                Coordinate coord_opponent_pawn = move.coord_next;
                                coord_opponent_pawn.i -= 1;

                                return move.coord_now.i == 4 &&
                                        last_move.figure == opponent_pawn &&

                                        last_move.coord_now.j == last_move.coord_next.j &&
                                        last_move.coord_now.i-2 == last_move.coord_next.i &&
                                        last_move.coord_next.equal(coord_opponent_pawn);
                            }
                    }
            }

        }
        if (figure == '♖') {
            //на одной линии ладья сейчас и потом
            //между координатами сейчас и потом нет фигур
            if (move.coord_now.i == move.coord_next.i) {
                int symbol = (move.coord_next.j-move.coord_now.j > 0) ? 1 : -1;
                for (int j=move.coord_now.j+symbol; j!=move.coord_next.j; j+=symbol)
                    if (field[move.coord_now.i][j] != ' ')
                        return false;
                return true;
            }
            if (move.coord_now.j == move.coord_next.j) {
                int symbol = (move.coord_next.i-move.coord_now.i > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol; i!=move.coord_next.i; i+=symbol)
                    if (field[i][move.coord_now.j] != ' ')
                        return false;
                return true;
            }
        }
        if (figure == '♗') {
            //на одной диагонали слон сейчас и потом
            //между координатами сейчас и потом нет фигур

            //на главной диагонали
            if ((move.coord_next.i - move.coord_now.i == move.coord_next.j - move.coord_now.j)) {
                int symbol = (move.coord_next.j-move.coord_now.j > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol, j=move.coord_now.j+symbol;
                     i!=move.coord_next.i; // второе условие и так одновременно настигнется по j
                     i+=symbol, j+=symbol)
                    if (field[j][j] != ' ')
                        return false;
                return true;
            }
            //на побочной
            if ((move.coord_next.i - move.coord_now.i == -(move.coord_next.j - move.coord_now.j))) {
                int symbol = (move.coord_next.i-move.coord_now.i > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol, j=move.coord_now.j-symbol;
                     i!=move.coord_next.i; // второе условие и так одновременно настигнется по j
                     i+=symbol, j-=symbol)
                    if (field[j][j] != ' ')
                        return false;
                return true;
            }
        }
        if (figure == '♕') {
            //ходит как ладья или слон
            //ладья
            if (move.coord_now.i == move.coord_next.i) {
                int symbol = (move.coord_next.j-move.coord_now.j > 0) ? 1 : -1;
                for (int j=move.coord_now.j+symbol; j!=move.coord_next.j; j+=symbol)
                    if (field[move.coord_now.i][j] != ' ')
                        return false;
                return true;
            }
            if (move.coord_now.j == move.coord_next.j) {
                int symbol = (move.coord_next.i-move.coord_now.i > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol; i!=move.coord_next.i; i+=symbol)
                    if (field[i][move.coord_now.j] != ' ')
                        return false;
                return true;
            }

            //слон
            //на главной диагонали
            if ((move.coord_next.i - move.coord_now.i == move.coord_next.j - move.coord_now.j)) {
                int symbol = (move.coord_next.j-move.coord_now.j > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol, j=move.coord_now.j+symbol;
                     i!=move.coord_next.i; // второе условие и так одновременно настигнется по j
                     i+=symbol, j+=symbol)
                    if (field[j][j] != ' ')
                        return false;
                return true;
            }
            //на побочной
            if ((move.coord_next.i - move.coord_now.i == -(move.coord_next.j - move.coord_now.j))) {
                int symbol = (move.coord_next.i-move.coord_now.i > 0) ? 1 : -1;
                for (int i=move.coord_now.i+symbol, j=move.coord_now.j-symbol;
                     i!=move.coord_next.i; // второе условие и так одновременно настигнется по j
                     i+=symbol, j-=symbol)
                    if (field[j][j] != ' ')
                        return false;
                return true;
            }
        }
        if (figure == '♘') {
            return Coordinate.distance(move.coord_now, move.coord_next) == Math.sqrt(5);
        }

        return false;
    }
    //Можно ли так двигаться фигуре (только в плане передвижения)
    boolean is_Acceptable_move(Move move) {
        //Алгоритм:
        //-2 - не ходит ли фигура сама в себя?
        //-1 - в рамках границ игрового поля?
        //0 - такая фигура действительно стоит на этом месте?
        //1 - можно ли такой фигуре так ходить?
            //1.1 - если фигура пешка:
                //1.1.1 - она бьёт
                //1.1.2 - она ходит
                //1.1.3 - она делает взятие на проходе
            //1.2 - если фигура король:
                //1.2.1 - он бьёт или ходит (без разницы)
                //1.2.2 - он делает рокировку
            //1...
            //1.6 - если фигура ладья...
        //2 - делаешь ход НЕ на свои фигуры?
        //3 - если сделать ход, то игроку не будет шаха

        boolean [] q = new boolean[8];

        q[0] = field[move.coord_now.i][move.coord_now.j] == move.figure;
        q[1] = Can_move_like_this(move);
        q[2] = (field[move.coord_next.i][move.coord_next.j] == ' ')
                || !figure_belong_player(field[move.coord_next.i][move.coord_next.j], move.player);

        char[][] field_copy = clone(field);
        field[move.coord_now.i][move.coord_now.j] = ' ';
        field[move.coord_next.i][move.coord_next.j] = move.figure;

        q[3] = !is_Check_to_player(move.player);

        field = clone(field_copy);

        return q[0] && q[1] && q[2] && q[3];
    }
    public boolean go_move(Move move) {
        boolean is_Acceptable = is_Acceptable_move(move);
        if (is_Acceptable) {
            field[move.coord_now.i][move.coord_now.j] = ' ';
            field[move.coord_next.i][move.coord_next.j] = move.figure;

            history_moves.add(move);
        }
        else
            System.out.println("Incorrect move.\n");

        return is_Acceptable;
    }
}
