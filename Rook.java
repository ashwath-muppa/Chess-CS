import java.util.ArrayList;

// // Rook class extends Piece class
public class Rook extends Piece{

    // Constructor for Rook class
    public Rook(char s, int side, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap)
    {
        // Set initial position based on side and color
        x = (side==0)?(0):(448);
        y = (s=='W')?(448):0;
        // Set file path for image based on color
        file = (s=='W')?("pieces/rook.png"):("pieces/rook1.png");
        // Set board, color, list of other pieces, type, and captured pieces panel
        board = b;
        color = s;
        otherPieces = o;
        type = "Rook";
        cappanel = cap;
    }
    // Constructor for Rook class with specified position
    public Rook(int x1, int y1,char s, int[][] b, ArrayList<ChessPiece> o,CapturedPieces cap)
    {
        // Set position, image file, board, color, list of other pieces, type, and captured pieces panel
        x = x1;
        y = y1;
        file = (s=='W')?("pieces/rook.png"):("pieces/rook1.png");
        board = b;
        color = s;
        otherPieces = o;
        type = "Rook";
        cappanel = cap;
    }
    // Method to calculate legal moves for the rook
    public ArrayList<int[]> legalMoves(){
      // Initialize lists to store legal moves
      ArrayList<int[]> finlegal = new ArrayList<int[]>();
      ArrayList<int[]> legal = new ArrayList<int[]>();

      // Calculate current position of the rook on the board
      int tmX = (int)(Math.floor(getX()/64));
      int tmY = (int)(Math.floor(getY()/64));

      // Check legal moves horizontally (right)
      for(int i=tmX+1;i<8;i++){
        if(board[tmY][i]==1){
            int[] curLegal = {i*64, tmY*64};
            finlegal.add(curLegal);
            break;
        }
        int[] curLegal = {i*64, tmY*64};
        legal.add(curLegal);
      }

      // Check legal moves horizontally (left)
      for(int i=tmX-1;i>=0;i--){
        if(board[tmY][i]==1){
            int[] curLegal = {i*64, tmY*64};
            finlegal.add(curLegal);
            break;
        }
        int[] curLegal = {i*64, tmY*64};
        legal.add(curLegal);

      }

      // Check legal moves vertically (up)
      for(int i=tmY-1;i>=0;i--){
        if(board[i][tmX]==1){
            int[] curLegal = {tmX*64, i*64};
            finlegal.add(curLegal);
            break;
        }
        int[] curLegal = {tmX*64, i*64};
        legal.add(curLegal);
      }

      // Check legal moves vertically (down)
      for(int i=tmY+1;i<8;i++){
        if(board[i][tmX]==1){
            int[] curLegal = {tmX*64, i*64};
            finlegal.add(curLegal);
            break;
        }
        int[] curLegal = {tmX*64, i*64};
        legal.add(curLegal);
      }

      // Check if legal moves capture opponent's pieces
      for(int[] leg : finlegal){
        for(ChessPiece k : otherPieces){
            if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()!=getColor()){
                legal.add(leg);
            }
        }

      }
      
      // Return the list of legal moves for the rook
      return legal;
   }

   // Method to identify protected pieces by the rook
   public ArrayList<int[]> protectedPieces(){
    // Initialize lists to store protected pieces
    ArrayList<int[]> finlegal = new ArrayList<int[]>();
    ArrayList<int[]> legal = new ArrayList<int[]>();

    // Calculate current position of the rook on the board
    int tmX = (int)(Math.floor(getX()/64));
    int tmY = (int)(Math.floor(getY()/64));

    // Check protected pieces horizontally (right)
    for(int i=tmX+1;i<8;i++){
      if(board[tmY][i]==1){
          int[] curLegal = {i*64, tmY*64};
          finlegal.add(curLegal);
          break;
      }
    }

    // Check protected pieces horizontally (left)
    for(int i=tmX-1;i>=0;i--){
      if(board[tmY][i]==1){
          int[] curLegal = {i*64, tmY*64};
          finlegal.add(curLegal);
          break;
      }

    }

    // Check protected pieces vertically (up)
    for(int i=tmY-1;i>=0;i--){
      if(board[i][tmX]==1){
          int[] curLegal = {tmX*64, i*64};
          finlegal.add(curLegal);
          break;
      }
    }

    // Check protected pieces vertically (down)
    for(int i=tmY+1;i<8;i++){
      if(board[i][tmX]==1){
          int[] curLegal = {tmX*64, i*64};
          finlegal.add(curLegal);
          break;
      }
    }

    // Check if protected pieces belong to the same color
    for(int[] leg : finlegal){
      for(ChessPiece k : otherPieces){
        if(k.getY()==leg[1] && k.getX()==leg[0] && k.getColor()==getColor()){
            legal.add(leg);
        }
      }
    }

    // Return the list of protected pieces by the rook
    return finlegal;
  }
}
