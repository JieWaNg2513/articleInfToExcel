import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class txtToexcelUtils {

    public static final String TXT = "txt";
    public static final String XLX = "xlx";
    public static final String XLXS = "xlsx";

    /**
     * 读取指定路径的txt文件，将文件内容转化为字符串
     * @param filePath
     * @return
     * @throws Exception
     */
    public String readTxt(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("filePath is not available!");
        }
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 将读取到到字符串转化为List
     * @param content
     * @return
     * @throws IOException
     */
    public List<Article> getArticle(String content) throws IOException {
        List<Article> lists = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        StringBuffer strbuf = new StringBuffer();
        int index = -3;//统计记录了几个空行。
        Article article = new Article();
        while ((line = br.readLine()) != null) {

            if (line.trim().equals("")) { //统计空行的数量
                if (index == 6) {
                    lists.add(article);
                    article = new Article();
                    index = -1;
                }else{
                    index++;
                }

            }else {
                switch (index) {
                    case 0:
                        article.setJournalInf(line);
                        break;
                    case 1:
                        article.setTitle(line);
                        break;
                    case 2:
                        article.setAuthors(line);
                        break;
                    case 3:
                        article.setAuthorsInf(article.getAuthorsInf().append(line));
//                        System.out.println("111");
//                        System.out.println(article.getAuthorsInf());
                        break;
                    case 4:
                        article.articleAbs.append(line);
                        break;
                    case 5:
                        if (line.startsWith("DOI")) {
                            article.setDoi(article.getDoi().append(line));
                        } else if (line.startsWith("PMCID")) {
                            article.setPmcid(article.getPmcid().append(line));
                        } else if (line.startsWith("PMID")) {
                            article.setPmid(article.getPmid().append(line));
                        }
                        break;
                    case 6:
                        article.cois.append(line);
                        break;
//                    case 7:
//                        lists.add(article);
//                        article = new Article();
//                        index = -2;
//                        break;
                }
            }

        }
//        System.out.println(strbuf.toString());
        lists.add(article);
        return lists;
    }

    /**
     * 生成excel文件
     */
    public void makeExcel(List<Article> list) {

        //1、创建工作簿
        Workbook workbook = new XSSFWorkbook();
        //1.1、设置表格的格式----居中
        CellStyle cs = workbook.createCellStyle();

        //2.1、创建工作表
        Sheet sheet = workbook.createSheet("文献内容");
        //2.2、合并单元格
        //sheet.addMergedRegion(new CellRangeAddress(4, 8, 5, 9));
        //3.1、创建行----表头行
        Row row = sheet.createRow(0);
        //4、创建格
        Cell cell = row.createCell(0);
        cell.setCellValue("期刊信息");
        cell.setCellStyle(cs);
        cell = row.createCell(1);
        cell.setCellValue("期刊标题");
        cell.setCellStyle(cs);
        cell = row.createCell(2);
        cell.setCellValue("作者");
        cell.setCellStyle(cs);
        cell = row.createCell(3);
        cell.setCellValue("作者信息");
        cell.setCellStyle(cs);
        cell = row.createCell(4);
        cell.setCellValue("文献摘要");
        cell.setCellStyle(cs);
        cell = row.createCell(5);
        cell.setCellValue("DOI");
        cell.setCellStyle(cs);
        cell = row.createCell(6);
        cell.setCellValue("PMCID");
        cell.setCellStyle(cs);
        cell = row.createCell(7);
        cell.setCellValue("PMID");
        cell.setCellStyle(cs);
        cell = row.createCell(8);
        cell.setCellValue("COIS");
        cell.setCellStyle(cs);

        for (int i = 0;i < list.size();i++) {
            Article article = list.get(i);
            //3.2、创建行----内容行
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(article.getJournal().toString());
            row.createCell(1).setCellValue(article.getTitle().toString());
            row.createCell(2).setCellValue(article.getAuthors().toString());
            row.createCell(3).setCellValue(article.getAuthorsInf().toString());
            row.createCell(4).setCellValue(article.getArticleAbs().toString());
            row.createCell(5).setCellValue(article.getDoi().toString());
            row.createCell(6).setCellValue(article.getPmcid().toString());
            row.createCell(7).setCellValue(article.getPmid().toString());
            row.createCell(8).setCellValue(article.getCois().toString());
        }

        //6、将文件储存到指定位置
        FileOutputStream fout=null;
        try {
            fout = new FileOutputStream("/Users/wangjie/Desktop/articleTest.xlsx");
            workbook.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
