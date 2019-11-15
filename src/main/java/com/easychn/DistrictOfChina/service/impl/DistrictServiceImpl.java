package com.easychn.DistrictOfChina.service.impl;

import org.springframework.stereotype.Service;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


import java.io.IOException;
import java.nio.charset.Charset;


import com.easychn.DistrictOfChina.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

    public void getDistrictDataSource () throws IOException{
        String dataPath = this.getClass().getResource("/data").getPath();
        String srcCSV =  dataPath + "/district-of-China-mainland.csv";
        String targetFile = dataPath + "/test.csv";
        String provincesFile = dataPath + "/provinces.csv";
        String citiesFile = dataPath + "/cities.csv";
        String areasFile = dataPath + "/areas.csv";
        CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("UTF-8"));
        CsvWriter write =new CsvWriter(targetFile,',',Charset.forName("UTF-8"));
        CsvWriter provincesWrite =new CsvWriter(provincesFile,',',Charset.forName("UTF-8"));
        CsvWriter citiesWrite =new CsvWriter(citiesFile,',',Charset.forName("UTF-8"));
        CsvWriter areasWrite =new CsvWriter(areasFile,',',Charset.forName("UTF-8"));
        //各字段以引号标记
        write.setForceQualifier(true);
        //路过表头
        //r.readHeaders();
        //逐条读取记录，直至读完
        String[] header = {};
        while (reader.readRecord()) {
            //把头保存起来
            if (reader.getCurrentRecord()==0){
                header = reader.getValues();
            }
            //获取当前记录位置
            System.out.print(reader.getCurrentRecord() + ".");
            //读取一条记录
            System.out.println(reader.getRawRecord());
            String[] tmp = {reader.getValues()[0],reader.getValues()[1]};
            //修改记录，并只写入第一个字段和第二字段
            if (!header[1].equals(tmp[1]) && ("".equals(tmp[1])||tmp==null)){
                System.err.println("空");
                tmp[1]="空";
                write.writeRecord(tmp);
            }else{
                int code = Integer.parseInt(reader.getValues()[0]);
                int province = code % 10000;
                System.err.println("province: "+ province);
                if(province == 0) {
                    System.err.println("写入省第" + reader.getCurrentRecord() + "条记录：" + reader.getValues()[0]  + "," + reader.getValues()[1]);
                    provincesWrite.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1],"1","0"});
                }
                int city = code % 100;
                System.err.println("city: "+ city);
                if(province > 0 && city == 0) {
                    System.err.println("写入市第" + reader.getCurrentRecord() + "条记录：" + reader.getValues()[0]  + "," + reader.getValues()[1]);
                    citiesWrite.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1],"2",String.valueOf(code - province)});
                }
                

                if(province > 0 && city > 0) {
                    System.err.println("写入区第" + reader.getCurrentRecord() + "条记录：" + reader.getValues()[0]  + "," + reader.getValues()[1]);
                    areasWrite.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1],"3",String.valueOf(code - city)});
                }

                write.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1]});
            }
        }

        reader.close();
        write.close();
        provincesWrite.close();
        citiesWrite.close();
        areasWrite.close();
    }
}
