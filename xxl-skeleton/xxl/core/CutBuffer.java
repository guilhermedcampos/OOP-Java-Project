package xxl.core;

import java.io.Serializable;

/*
* Represents a cut buffer used for storing content to be pasted in a spreadsheet.
*/
public class CutBuffer implements Serializable {

    /** Array of contents stored in the cut buffer. */
    private Content[] _contents;

    /** Flag indicating whether the contents vary in columns. */
    private boolean _variesInColumns;

    /**
     * Constructs a CutBuffer object with default settings.
     * By default, contents do not vary in columns.
     */
    public CutBuffer() {
        _variesInColumns = false;
    }

    /**
     * Sets whether the contents in the cut buffer vary in columns.
     *
     * @param variesInColumns true if the contents vary in columns, false otherwise.
     */
    public void setVariesInColumns(boolean variesInColumns) {
        _variesInColumns = variesInColumns;
    }

    /**
     * Gets the array of contents stored in the cut buffer.
     *
     * @return the array of contents in the cut buffer.
     */
    public Content[] getContents() {
        return _contents;
    }

    /**
     * Sets the contents in the cut buffer.
     *
     * @param contents the array of contents to be stored in the cut buffer.
     */
    public void setContents(Content[] contents) {
        _contents = contents;
    }

    /**
     * Gets the content at the specified index in the cut buffer.
     *
     * @param index the index of the content to retrieve.
     * @return the content at the specified index in the cut buffer.
     */
    public Content getContent(int index) {
        return _contents[index];
    }

    /**
     * Checks if the contents in the cut buffer vary in columns.
     *
     * @return true if the contents vary in columns, false otherwise.
     */
    public boolean variesInColumns() {
        return _variesInColumns;
    }
}
