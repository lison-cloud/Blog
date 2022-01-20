package by.bsuir.blog.mapper.util;

import by.bsuir.blog.mapper.Table;

public class QueryUtils {
    
    public static String cols(Table table){
        StringBuilder cols = new StringBuilder();
        table.columns().stream().forEach(
            c -> cols.append(c).append(",")
        );
        cols.deleteCharAt(cols.length()-1);
        return cols.toString();
    }

    public static String vals(Table table) {
        StringBuilder vals = new StringBuilder();
        for (int i = 0; i < table.columns().size(); i++) {
            vals.append("?,");
        }
        vals.deleteCharAt(vals.length()-1);
        return vals.toString();
    }

}
