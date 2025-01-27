package chess;

import java.util.Collection;
import java.util.List;


import static chess.ChessPiece.QBRValidMoves;

public class bishopMovesCalculator implements ChessPiece.pieceMovesCalculator {
    private static final ChessPosition[] possibleBishopMoves = {
            new ChessPosition(1,1), // Diagonal Right
            new ChessPosition(1,-1), // Diagonal Left
            new ChessPosition(-1,1), // Diagonal Down Right
            new ChessPosition(-1,-1), // Diagonal Down Left
    };

    @Override
    public Collection<ChessMove> validMoves(ChessBoard board, ChessPosition myPosition) {
        return QBRValidMoves(board,myPosition, possibleBishopMoves);
    }
}
