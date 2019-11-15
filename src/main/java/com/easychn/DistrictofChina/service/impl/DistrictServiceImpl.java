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
        CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("UTF-8"));
        CsvWriter write =new CsvWriter(targetFile,',',Charset.forName("UTF-8"));
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
                System.err.println("写入第" + reader.getCurrentRecord() + "条记录：" + reader.getValues()[0]  + "," + reader.getValues()[1]);
                write.writeRecord(new String[]{reader.getValues()[0],reader.getValues()[1]});
            }
        }

        reader.close();
        write.close();
    }
}
