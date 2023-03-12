import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Playing_field field;

    /*
    public static Node get_max_by_score(ArrayList<Node> nodes) {
        int imax = 0;
        double max = -1;

        for (int j=0; j<nodes.size(); j++)
            if (max < nodes.get(j).score) {
                max = nodes.get(j).score;
                imax = j;
            }

        return nodes.get(imax);
    }
    public static int[] Intelligence(char[][] matrix, char player) {
        int [] result_indexes = {-1,-1};
        Node.player_bot = player;
        Node.count = 0;
        Node tree = new Node(matrix, player, 0);

        Node maximum_score_node = get_max_by_score(tree.nexts); //мб лучше чтобы бот дольше сопротивлялся при проигрывании?
        Move result = maximum_score_node.current_move;

        result_indexes[0] = result.i_now;
        result_indexes[1] = result.j_now;


        return result_indexes;
    }
    public static boolean Bot_game(char[][] matrix, char player) {
        //char opponent = get_alternative_player(player);
        int [] indexes = Intelligence(matrix, player);
        System.out.println("Player bot "+player+": "+(indexes[0]+1)+","+(indexes[1]+1));

        matrix[indexes[0]][indexes[1]] = player;

        String result_game = check_result_game(matrix,player);
        boolean end_game = false;

        if (result_game.equals("Win")) {
            System.out.println("You lose");
            end_game = true;
        }
        if (result_game.equals("Draw")) {
            System.out.println("Draw");
            end_game = true;
        }

        return end_game;
    }

    public static void Man_vs_Bot() {
        char player1 = 'X', player2 = 'O';

        //char [][] matrix = new char[3][3];
        char [][] matrix = {{' ',' ',' '},
                {' ',' ',' '},
                {' ',' ',' '}};
        boolean end_game;

        while (true) {
            output_field(matrix);

            end_game = Player_game(matrix,player1);
            if (end_game) break;

            output_field(matrix);

            end_game = Bot_game(matrix,player2);
            if (end_game) break;
        }

        output_field(matrix);
    }

    public static void Bot_vs_Man() {
        char player1 = 'X', player2 = 'O';

        //char [][] matrix = new char[3][3];
        char [][] matrix = {{' ',' ',' '},
                {' ',' ',' '},
                {' ',' ',' '}};
        boolean end_game;

        while (true) {
            output_field(matrix);

            end_game = Bot_game(matrix,player1);
            if (end_game) break;

            output_field(matrix);

            end_game = Player_game(matrix,player2);
            if (end_game) break;
        }

        output_field(matrix);
    }
    public static void Test() {
        char [][] matrix = {{'X','O','X'},
                {'X',' ',' '},
                {' ','O',' '}};


        Intelligence(matrix, 'O');
    }

    public static void Play_with_Bot() {
        System.out.print("white or black : ");
        Scanner sc = new Scanner(System.in);

        if (sc.nextLine().charAt(0) == 'x')
            Man_vs_Bot();
        else
            Bot_vs_Man();
    }
*/

    public static boolean Player_game(char player) {
        boolean end_game = false;

        if(field.history_moves.size()>=14) {
            Situation situation_game = field.calculate_situation_game(player);
            switch (situation_game) {
                case LOSS -> {
                    System.out.println("Player " + Other_functions.opponent(player) + " win!");
                    end_game = true;
                }
                case DRAW -> {
                    System.out.println("Draw");
                    end_game = true;
                }
            }
        }

        if (!end_game) {
            Move move;
            while (true) {
                System.out.print("Player " + player + ": ");
                Scanner sc = new Scanner(System.in);
                move = new Move(sc.nextLine(), player);

                if (move.correct) // если ход корректен по вводу
                    if (field.go_move(move)) // если ход корректен по правилам хода
                        break; // выходим
            }
        }

        return end_game;
    }
    public static void Man_vs_Man() {
        char player1 = 'w', player2 = 'b';

        field = new Playing_field();
        boolean end_game;

        while (true) {
            field.show(player1);
            end_game = Player_game(player1);
            if (end_game) break;

            field.show(player2);
            end_game = Player_game(player2);
            if (end_game) break;
        }
        field.show(player1);
    }

    public static void Test_One_Man() {
        char player1 = 'w';

        field = new Playing_field();
        boolean end_game;

        while (true) {
            field.show(player1);
            end_game = Player_game(player1);
            if (end_game) break;
        }
        field.show(player1);
    }
    public static void main(String[] args) {
       Man_vs_Man();
    }
}
