package chess;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;
    private static final EnumMap<PieceType, pieceMovesCalculator> MOVES_CALCULATORS = new EnumMap<>(PieceType.class);

    static {
        MOVES_CALCULATORS.put(PieceType.KING, new kingMovesCalculator());
        MOVES_CALCULATORS.put(PieceType.QUEEN, new queenMovesCalculator());
        MOVES_CALCULATORS.put(PieceType.BISHOP, new bishopMovesCalculator());
        MOVES_CALCULATORS.put(PieceType.KNIGHT, new knightMovesCalculator());
        MOVES_CALCULATORS.put(PieceType.ROOK, new rookMovesCalculator());
        MOVES_CALCULATORS.put(PieceType.PAWN, new pawnMovesCalculator());
    }

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */

    public interface pieceMovesCalculator {
        Collection<ChessMove> validMoves(ChessBoard board, ChessPosition myPosition);
    }

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        pieceMovesCalculator calculator = MOVES_CALCULATORS.get(type);
        if (calculator != null) {
            return calculator.validMoves(board, myPosition);
        }
        return new ArrayList<>();
    }

    static Collection<ChessMove> KingKnightValidMoves(ChessBoard board, ChessPosition myPos, ChessPosition[] possibleKMoves){
        List<ChessMove> validMoves = new ArrayList<>();
        ChessPiece myPiece = board.getPiece(myPos);

        for(ChessPosition moveOffset : possibleKMoves){
            int testRow = myPos.getRow() + moveOffset.getRow();
            int testCol = myPos.getColumn() + moveOffset.getColumn();

            //in bounds test
            if(testRow >= 1 && testRow <= 8 && testCol >= 1 && testCol <= 8){
                ChessPosition newPosition = new ChessPosition(testRow, testCol);
                ChessPiece targetPiece = board.getPiece(newPosition);

                if(targetPiece == null || targetPiece.getTeamColor() != myPiece.getTeamColor()){
                    ChessMove newMove = new ChessMove(myPos, newPosition, null);
                    validMoves.add(newMove);
                }
            }
        }
        return validMoves;
    }

    static Collection<ChessMove> QBRValidMoves(ChessBoard board, ChessPosition myPos, ChessPosition[] possibleQBRMoves){
        List<ChessMove> validMoves = new ArrayList<>();
        ChessPiece myPiece = board.getPiece(myPos);

        for(ChessPosition moveOffset : possibleQBRMoves){
            int testRow = myPos.getRow() + moveOffset.getRow();
            int testCol = myPos.getColumn() + moveOffset.getColumn();

            //in bounds test
            while(testRow >= 1 && testRow <= 8 && testCol >= 1 && testCol <= 8){
                ChessPosition newPosition = new ChessPosition(testRow, testCol);
                ChessPiece targetPiece = board.getPiece(newPosition);

                if(targetPiece == null || targetPiece.getTeamColor() != myPiece.getTeamColor()){
                    ChessMove newMove = new ChessMove(myPos, newPosition, null);
                    validMoves.add(newMove);
                    if(targetPiece != null && targetPiece.getTeamColor() != myPiece.getTeamColor()){
                        break;
                    }
                } else {
                    break;
                }
                testRow += moveOffset.getRow();
                testCol += moveOffset.getColumn();
            }
        }
        return validMoves;
    }







    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
