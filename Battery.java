import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.math.*;
import org.json.JSONException;
import org.json.JSONObject;
public class Battery
{ 
	public static void main(String[] args)  throws IOException
	{
		JSONObject obj=new JSONObject();
		String Line;
		String foreground;
		String fgvalue = "";
		String drain;
		double drainvalue=0;
		int i=0;
		int count=0;
		int n=1000;
		double[] valueArray=new double[n];
		FileInputStream fis = new FileInputStream("Battery.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

			while((Line=br.readLine())!=null)
			{
					count++;
					StringTokenizer st=new StringTokenizer(Line,":");
					ArrayList<String> token=new ArrayList<>();	
					while(st.hasMoreTokens())
					{
						token.add(st.nextToken());
					}
					for(String x:token)
					{
						if(x.contains("Foreground activities"))
						{
							foreground=Line;
							StringTokenizer st2=new StringTokenizer(foreground,":");
							ArrayList<String> fgtoken=new ArrayList<>();	
							while(st2.hasMoreTokens())
							{
								fgtoken.add(st2.nextToken());
								//System.out.println(st2.nextToken());
							}
							fgvalue=token.get(1);
						}
					}
					for(String y:token)
					{
						if(y.contains("Uid u0a202"))
						{
							drain=Line;
							StringTokenizer st2=new StringTokenizer(drain,":(");
							ArrayList<String> draintoken=new ArrayList<>();	
							while(st2.hasMoreTokens())
							{
								draintoken.add(st2.nextToken());
								//System.out.println(st2.nextToken());
							}
							//System.out.println(draintoken.get(1));
							drainvalue=Double.parseDouble(draintoken.get(1));
						}
					}	

			}
		
			br.close();
			FileWriter fw = new FileWriter("Battery.json");
			PrintWriter pw = new PrintWriter(fw);
			try
			{
				obj.put("", fgvalue);
				obj.put("", drainvalue);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			pw.format("\nforeground_time:%s",fgvalue);
			pw.format("\nBattery drain:%s",drainvalue);
			double batterypercentage=drainvalue/1000;
			DecimalFormat d=new DecimalFormat("#.####");
			pw.format("\nBatterypercentage:%s", d.format(batterypercentage));
			
			pw.close();
			
			
		}
	
	}