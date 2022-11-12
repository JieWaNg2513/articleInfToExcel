import java.util.List;

public class Article {
    //序号 作者 标题 期刊名 年份 doi pmid pmcid abstract

    public int number;
    public StringBuilder authors = new StringBuilder();

    public StringBuilder authorsInf = new StringBuilder();
    public StringBuilder title = new StringBuilder();
    public StringBuilder journal = new StringBuilder(); //期刊名
    public StringBuilder publication = new StringBuilder();//出版时间
    public StringBuilder doi = new StringBuilder();
    public StringBuilder pmid = new StringBuilder();
    public StringBuilder pmcid = new StringBuilder();
    public StringBuilder articleAbs = new StringBuilder();

    public StringBuilder cois = new StringBuilder();

    public StringBuilder getCois() {
        return cois;
    }

    public void setCois(StringBuilder cois) {
        this.cois = cois;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StringBuilder getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = new StringBuilder(authors);
    }

    public StringBuilder getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = new StringBuilder(title);
    }

    public StringBuilder getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = new StringBuilder(journal);
    }

    public StringBuilder getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = new StringBuilder(publication);
    }

    public StringBuilder getDoi() {
        return doi;
    }

    public void setDoi(StringBuilder doi) {
        this.doi = new StringBuilder(doi);
    }

    public StringBuilder getPmid() {
        return pmid;
    }

    public void setPmid(StringBuilder pmid) {
        this.pmid = new StringBuilder(pmid);
    }

    public StringBuilder getPmcid() {
        return pmcid;
    }

    public void setPmcid(StringBuilder pmcid) {
        this.pmcid = new StringBuilder(pmcid);
    }

    public StringBuilder getArticleAbs() {
        return articleAbs;
    }

    public void setArticleAbs(StringBuilder articleAbs) {
        this.articleAbs = articleAbs;
    }

    public Article(){}
    public Article(int number) {
        this.number = number;
    }

    public void setJournalInf(String journalInf) {
        String[] infos = journalInf.split("\\. ");
        this.setNumber(Integer.parseInt(infos[0].trim()));
        this.setJournal(infos[1].trim());
        this.setPublication(infos[2].trim());
        this.setDoi(new StringBuilder(infos[3].trim()));
    }

    public StringBuilder getAuthorsInf() {
        return authorsInf;
    }

    public void setAuthorsInf(StringBuilder authorsInf) {
        this.authorsInf = authorsInf;
    }

    @Override
    public String toString() {
        return "Article{" +
                "number=" + number +
                ", authors='" + authors + '\'' +
                ", authorsInf=" + authorsInf +
                ", title='" + title + '\'' +
                ", journal='" + journal + '\'' +
                ", publication='" + publication + '\'' +
                ", doi='" + doi + '\'' +
                ", pmid='" + pmid + '\'' +
                ", pmcid='" + pmcid + '\'' +
                ", articleAbs=" + articleAbs +
                ", cois=" + cois +
                '}';
    }
}
