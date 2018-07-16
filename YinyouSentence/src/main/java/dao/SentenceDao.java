package dao;

import bean.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName SentenceDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 15:55
 * Version 1.0
 */
public class SentenceDao {
    @Autowired
    private SessionFactory sessionFactory;

    // 根据句子Id返回句子信息
    public Sentence getSentenceById(long id){

        Session session = sessionFactory.openSession();
        String hql = "From Sentence where id = ?";
        Sentence sentence = null;
        Transaction tx = session.beginTransaction();
        sentence = (Sentence) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return sentence;
    }

    // 根据作者ID获得其所有句子
    public List<Sentence> getSentencesByAuthorId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where authorId = ?  ORDER BY loveNum desc ";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentences;
    }

    // 根据句子Id判断句子是否已经存在
    public boolean isSentenceExist(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from Sentence where id = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 根据作者Id获得作者信息
    public GiantInfo getGiantInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From GiantInfo where id = ?";
        GiantInfo giantInfo = null;
        Transaction tx = session.beginTransaction();
        giantInfo = (GiantInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return giantInfo;
    }

    // 根据用户ID获取用户信息
    public UserInfo getUserInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From UserInfo where id = ?";
        UserInfo userInfo = null;
        Transaction tx = session.beginTransaction();
        userInfo = (UserInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return userInfo;
    }

    // 根据句子Id获取喜欢该句子的用户的id
    public List<Long> getLoveSentenceUserIdBySentenceId(long id){
        Session session = sessionFactory.openSession();
        String hql = "select userId From SentenceLove where sentenceId = ?  ORDER BY id desc ";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return userIds;
    }

    // 通过出处id获取出处信息
    public OriginInfo getOriginInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From OriginInfo where id = ?";
        OriginInfo originInfo = null;
        Transaction tx = session.beginTransaction();
        originInfo = (OriginInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return originInfo;
    }

    // 通过标签名获取标签对象
    public Tag getTagByName(String tagName){
        Session session = sessionFactory.openSession();
        String hql = "From Tag where name = ?";
        Tag tag = null;
        Transaction tx = session.beginTransaction();
        List<Tag> tags =  session.createQuery(hql).setParameter(0,tagName).list();
        tx.commit();
        if(tags != null && tags.size() > 0){
            tag = tags.get(0);
        }
        return tag;
    }

    // 根据出处ID获得其所有句子
    public List<Sentence> getSentencesByOriginId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where originId = ?  ORDER BY loveNum desc ";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentences;
    }

    // 判断一个用户是否喜欢过一个句子，如果喜欢则返回这条记录的id，否则返回0
    public long checkUserLoveSentence(long userId, long sentenceId){
        Session session = sessionFactory.openSession();
        String hql = "From SentenceLove where userId = ? AND sentenceId = ?";
        SentenceLove sentenceLove = null;
        Transaction tx = session.beginTransaction();
        sentenceLove = (SentenceLove) session.createQuery(hql).setParameter(0,userId).setParameter(1,sentenceId).uniqueResult();
        tx.commit();

        if(sentenceLove == null){
            return 0;
        }
        return sentenceLove.getId();
    }

    // 用户喜欢一个句子
    public void userLoveSentence(SentenceLove sentenceLove){
        // 添加到句子喜欢表，并更新句子里面的喜欢的数值
        Session session = sessionFactory.openSession();
        String hql = "update Sentence set loveNum = loveNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.save(sentenceLove);
        session.createQuery(hql).setParameter(0,sentenceLove.getSentenceId()).executeUpdate();
        tx.commit();

    }

    // 用户不喜欢一个句子，传入的参数为 SentenceLove表的Id和句子的Id
    public void userNotLoveSentence(long loveId,long sentenceId){
        Session session = sessionFactory.openSession();
        String hql = "delete from SentenceLove where id = ?";
        String hql_2 = "update Sentence set loveNum = loveNum -1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,loveId).executeUpdate();
        session.createQuery(hql_2).setParameter(0,sentenceId).executeUpdate();
        tx.commit();
    }

    // 查看一个句子的喜欢个数
    public long getSentenceLoveNum(long sentenceId){
        Session session = sessionFactory.openSession();
        String hql = "select loveNum from Sentence where id = ?";
        long loveNum = 0;
        Transaction tx = session.beginTransaction();
        loveNum = (Long) session.createQuery(hql).setParameter(0,sentenceId).uniqueResult();
        tx.commit();
        return  loveNum;
    }

    // 随机返回一条句子,句子id不能为指定id
    public Sentence getRandomSentence(long notThisId){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where id<> ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,notThisId).list();
        tx.commit();
        // 取出全部合法句子后，随机取一条返回
        Random random= new Random();
        int index = random.nextInt(sentences.size());
        return sentences.get(index);
    }

    // 获取推荐句子，参数为条数
    public List<Sentence> getRecommendSentences(int num){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setMaxResults(num).list();
        tx.commit();
        return sentences;
    }

    // 获取热门句子，参数为条数
    public List<Sentence> getHotSentences(int num){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence order by loveNum desc";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setMaxResults(num).list();
        tx.commit();
        return sentences;
    }

    // 获取热门原创句子，参数为条数
    public List<Sentence> getHotOriginSentences(int num){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where isOriginal = ? order by loveNum desc";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,(byte) 1).setMaxResults(num).list();
        tx.commit();
        return sentences;
    }

    // 获取新发布的句子，参数为条数
    public List<Sentence> getNewPublishSentence(int num){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence order by publishTime desc";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setMaxResults(num).list();
        tx.commit();
        return sentences;
    }

    // 获得句子的评论条数
    public long getCommentNumBySentenceId(long sentenceId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) From SentenceComment where sentenceId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,sentenceId).uniqueResult();
        tx.commit();
        return num;
    }

    // 根据用户Id获取用户喜欢的所有句子
    public List getLoveSentencesByUserId(long userId){
        Session session = sessionFactory.openSession();
        String hql = "From SentenceLove where userId = ?";
        List<SentenceLove> sentenceLoves = new ArrayList<SentenceLove>();
        Transaction tx = session.beginTransaction();
        sentenceLoves =  session.createQuery(hql).setParameter(0,userId).list();
        tx.commit();
        return sentenceLoves;
    }

    // 根据用户Id获取其发布的句子
    public List<Sentence> getPublishSentencesByUserId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where publisherId = ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentences;
    }


    // 根据用户id获取其原创句子
    public List<Sentence> getOriginalSentencesByUserId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where publisherId = ? AND isOriginal = 1";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentences;
    }


    // 根据句子内容模糊查询句子
    public List<Sentence> getSentencesBySentenceContent(String s){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where content like ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,"%" + s + "%").list();
        tx.commit();
        return sentences;
    }

    // 根据作者名模糊查询该作者名对应的所有作者列表
    public List<GiantInfo> getGiantInfosByGiantName(String s){
        Session session = sessionFactory.openSession();
        String hql = "From GiantInfo where name like ?";
        List<GiantInfo> giantInfos = new ArrayList<GiantInfo>();
        Transaction tx = session.beginTransaction();
        giantInfos =  session.createQuery(hql).setParameter(0,"%" + s + "%").list();
        tx.commit();
        return giantInfos;
    }


    // 根据句子作者id查询句子
    public List<Sentence> getSentenceByAuthorId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where authorId = ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0, id).list();
        tx.commit();
        return sentences;
    }

    // 根据出处名模糊查询所有出处
    public List<OriginInfo> getOriginInfosByOriginName(String s){
        Session session = sessionFactory.openSession();
        String hql = "From OriginInfo where name like ?";
        List<OriginInfo> originInfos = new ArrayList<OriginInfo>();
        Transaction tx = session.beginTransaction();
        originInfos =  session.createQuery(hql).setParameter(0,"%" + s + "%").list();
        tx.commit();
        return originInfos;
    }

    // 根据出处id查询其下所有句子
    public List<Sentence> getSentenceByOriginId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where originId = ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0, id).list();
        tx.commit();
        return sentences;
    }

    // 根据标签名模糊查询标签列表
    public List<Tag> getTagsByTagName(String s){
        Session session = sessionFactory.openSession();
        String hql = "From Tag where name like ?";
        List<Tag> tags = new ArrayList<Tag>();
        Transaction tx = session.beginTransaction();
        tags =  session.createQuery(hql).setParameter(0,"%" + s + "%").list();
        tx.commit();
        return tags;
    }

    // 根据标签Id获取其对应的所有 标签引用 记录
    public List<TagQuote> getTagQuotesByTagId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From TagQuote where tagId = ?";
        List<TagQuote> tagQuotes = new ArrayList<TagQuote>();
        Transaction tx = session.beginTransaction();
        tagQuotes =  session.createQuery(hql).setParameter(0, id).list();
        tx.commit();
        return tagQuotes;
    }


    public Boolean checkIfUserFollowUser(long myId,long userId){
        return checkIfUserFollowUser(myId,0,userId);
    }


    // 判断两个user之间是否互相关注
    public Boolean checkIfUserFollowUser(long myId,int beginIndex, long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) From UserFollow where followerId = ? And userId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0, myId).setFirstResult(beginIndex).setParameter(1,userId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }


    // 根据分类id获取这个分类下指定条数 句子id
    public List<Long> getSentenceIdsByCategoryId(long categoryId,int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT sentenceId from SentenceCategory where categoryId = ?";
        List<Long> sentenceIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        sentenceIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, categoryId).setMaxResults(num).list();
        tx.commit();
        return sentenceIds;
    }


    // 根据标签id获取该标签下制定条数句子Id
    public List<Long> getSentenceIdsByTagId(long tagId,int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT sentenceId from TagQuote where tagId = ?";
        List<Long> sentenceIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        sentenceIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, tagId).setMaxResults(num).list();
        tx.commit();
        return sentenceIds;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
