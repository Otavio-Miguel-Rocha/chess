public class Main {

    public static void main(String[] args) {

        tabuleiro();
    }
    private static void tabuleiro(){
        for (int i = 0 ; i < 8 ; i ++){
            System.out.println("---------------------------------------");
            for (int j = 0; j < 8; j++) {
                System.out.print(" | | ");
            }
            System.out.println("");
            if( i == 7){
                System.out.println("---------------------------------------");
            }
        }
    }
}
