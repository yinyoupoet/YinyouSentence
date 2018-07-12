package auxiliary;

import bean.SentenceCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollectionAuxiliary
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 19:17
 * Version 1.0
 */
public class CollectionAuxiliary {
    // 所有的句子集
    private SentenceCollection sentenceCollection;
    // 当前句子集是否收藏了这个句子
    private Boolean collect;


    public SentenceCollection getSentenceCollection() {
        return sentenceCollection;
    }

    public void setSentenceCollection(SentenceCollection sentenceCollection) {
        this.sentenceCollection = sentenceCollection;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }
}
