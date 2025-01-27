package chess;


import java.util.Collection;
import java.util.List;

import static chess.ChessPiece.KingKnightValidMoves;

public class knightMovesCalculator implements ChessPiece.pieceMovesCalculator{

    private static final ChessPosition[] possibleKnightMoves = {
            new ChessPosition(2,1), //Upper top Right
            new ChessPosition(1,2), //Lower top right
            new ChessPosition(-1,2), //Upper bottom right
            new ChessPosition(-2,1), //Lower bottom right
            new ChessPosition(-2,-1), //lower bottom Left
            new ChessPosition(-1,-2), //lower bottom right
            new ChessPosition(1,-2), //Lower top Left
            new ChessPosition(2,-1) //Lower top left
    };

    @Override
    public Collection<ChessMove> validMoves(ChessBoard board, ChessPosition myPosition) {
        return KingKnightValidMoves(board,myPosition, possibleKnightMoves);
    }
}
