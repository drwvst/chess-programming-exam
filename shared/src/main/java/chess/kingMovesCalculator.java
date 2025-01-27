package chess;

import java.util.Collection;
import java.util.List;

import static chess.ChessPiece.KingKnightValidMoves;

public class kingMovesCalculator implements ChessPiece.pieceMovesCalculator {

    private static final ChessPosition[] possibleKingMoves = {
            new ChessPosition(1,0), //Forward
            new ChessPosition(-1,0), //Backward
            new ChessPosition(0,1), //Right
            new ChessPosition(0,-1), //Left
            new ChessPosition(1,1), //Front Right
            new ChessPosition(1,-1), //Front Left
            new ChessPosition(-1,1), //Back Right
            new ChessPosition(-1,-1) //Back Left
    };

    @Override
    public Collection<ChessMove> validMoves(ChessBoard board, ChessPosition myPosition) {
        return KingKnightValidMoves(board,myPosition, possibleKingMoves);
    }
}
