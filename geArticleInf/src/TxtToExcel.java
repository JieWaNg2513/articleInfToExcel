import java.util.List;

public class TxtToExcel {
    public static void main(String[] args) throws Exception {
        String result;
        txtToexcelUtils utils = new txtToexcelUtils();
        String filePath = "/Users/wangjie/Desktop/abstract-CAR-TORCAR-set.txt";
        result = utils.readTxt(filePath);
        System.out.println(result);
        System.out.println("**********************");
        utils.getArticle(result);
        List<Article> lists = utils.getArticle(result);
        System.out.println(lists.size());
        for (Article article : lists) {
            System.out.println(article.toString());
        }

        utils.makeExcel(lists);
    }
}
