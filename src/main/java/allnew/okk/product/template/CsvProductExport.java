package allnew.okk.product.template;

// Week 6, pattern template Marciniuk
public class CsvProductExport extends ProductExportTemplate {
    @Override
    protected String formatData(String rawData) {
        String[] pairs = rawData.split(";");
        StringBuilder headers = new StringBuilder();
        StringBuilder values  = new StringBuilder();
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            headers.append(kv[0]).append(",");
            values.append(kv.length > 1 ? kv[1] : "").append(",");
        }
        return headers.toString().replaceAll(",$", "")
                + "\n"
                + values.toString().replaceAll(",$", "");
    }

    @Override
    protected void exportOutput(String content) {
        System.out.println("CsvProductExport: saving to product.csv:\n" + content);
    }
}
// End Week 6, pattern template Marciniuk