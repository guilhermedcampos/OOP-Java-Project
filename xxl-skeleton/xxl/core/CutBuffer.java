package xxl.core;

import java.io.Serial;
import java.io.Serializable;

/*
* Represents a cut buffer used for storing content to be pasted in a spreadsheet.
*/
public class CutBuffer implements Serializable {
    private Content[] _contents;
    private boolean _variesInColumns;

    public CutBuffer() {
        _variesInColumns = false;
    }

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
