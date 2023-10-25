package xxl.core;

public class CutBuffer { 
    private Content[] _contents;

    public Content[] getContents() {
        return _contents;
    }

    public void setContent(Content[] contents) {
        _contents = contents;
    }

    public Content getContent(int index) {
        return _contents[index];

    }

}