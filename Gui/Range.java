public class Range {
    private Cell _start;
    private Cell _end;

    public Range(Cell start, Cell end) {
        this._start = start;
        this._end = end;
    }

    public Cell getStart() {
        return _start;
    }

    public Cell getEnd() {
        return _end;
    }
}