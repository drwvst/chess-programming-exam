package chess;

import java.util.Collection;
import java.util.List;

import static chess.ChessPiece.QBRValidMoves;

public class rookMovesCalculator implements ChessPiece.pieceMovesCalculator{
    private static final ChessPosition[] possibleRookMoves = {
            new ChessPosition(1,0), // Up
            new ChessPosition(-1,0), // Down
            new ChessPosition(0,-1), // Left
            new ChessPosition(0,1), // Right
    };


    @Override
    public Collection<ChessMove> validMoves(ChessBoard board, ChessPosition myPosition) {
        return QBRValidMoves(board,myPosition, possibleRookMoves);
    }
}
