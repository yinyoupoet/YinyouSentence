package pageEntity;

import auxiliary.CollectionAuxiliary;
import bean.SentenceCollection;
import dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollectionEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 22:41
 * Version 1.0
 */
public class CollectionEntity {
    @Autowired
    private CollectionDao collectionDao;

    private List<CollectionAuxiliary> collectionAuxiliaries = new ArrayList<CollectionAuxiliary>();

    /**
    * @author hasee
    * @Description 初始化方法
    * @Date 22:43 2018-07-10
    * @Param []
    * @return void
    **/
    public void init(long userId,long sentenceId){
        if(userId == 0){
            collectionAuxiliaries = null;
            return;
        }
        if(collectionAuxiliaries != null){
            collectionAuxiliaries.clear();
        }else{
            collectionAuxiliaries = new ArrayList<CollectionAuxiliary>();
        }
        List<SentenceCollection> sentenceCollections = collectionDao.getSentenceCollectionsByUserId(userId);
        for(SentenceCollection sc : sentenceCollections){
            CollectionAuxiliary collectionAuxiliary = new CollectionAuxiliary();
            collectionAuxiliary.setSentenceCollection(sc);
            collectionAuxiliary.setCollect(collectionDao.isSentenceCollectedInCollection(sentenceId,sc.getId()));
            collectionAuxiliaries.add(collectionAuxiliary);
        }
    }


    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    public List<CollectionAuxiliary> getCollectionAuxiliaries() {
        return collectionAuxiliaries;
    }

    public void setCollectionAuxiliaries(List<CollectionAuxiliary> collectionAuxiliaries) {
        this.collectionAuxiliaries = collectionAuxiliaries;
    }
}
