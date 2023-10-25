package xxl.core;

public class CutBuffer {
    private Content[] _contents;
    private boolean _variesInColumns;

    public CutBuffer() {
        _variesInColumns = false;
    }

    // Constructor that specifies whether the cut buffer varies in columns or rows
    public void setVariesInColumns(boolean variesInColumns) {
        _variesInColumns = variesInColumns;
    }

    public Content[] getContents() {
        return _contents;
    }

    public void setContents(Content[] contents) {
        _contents = contents;
    }

    public Content getContent(int index) {
        return _contents[index];
    }

    public boolean variesInColumns() {
        return _variesInColumns;
    }
}
