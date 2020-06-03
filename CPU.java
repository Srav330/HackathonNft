import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;


public class CPU {

public static void main(String[] args) throws IOException {
JSONObject object = new JSONObject();
double[] cpu = new double[10000];
double max=0;
FileInputStream f = new FileInputStream("cpu.txt");
InputStreamReader inp = new InputStreamReader(f);
BufferedReader br = new BufferedReader(inp);
String Line="";
double num=0;
double avg=0;
int count=0;
int i=0;

while((Line = br.readLine())!=null){
StringTokenizer st = new StringTokenizer(Line," ");
ArrayList<String> array = new ArrayList<>();
while(st.hasMoreTokens()){
array.add(st.nextToken());
}
num = Double.parseDouble(array.get(8));
if(max<=num){
max = num;
}
cpu[i] = num;
i++;
count++;
}
double sum=0;
for(int j=0;j<count;j++){
sum +=cpu[j];
}
avg = sum/count;
br.close();
FileWriter fw = new FileWriter("cpu.json");
PrintWriter pw = new PrintWriter(fw);
for(int k=0;k<count;k++){
try {
object.put(k+"s:", cpu[k]);
} catch (JSONException e) {
System.out.println(e);
}
}
pw.format("%s", object);
pw.format("\nMAX:%s", max);
pw.format("\nAVG:%s", avg);
pw.close();


}

}