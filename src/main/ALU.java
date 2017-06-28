package main;

/**
 * ģ��ALU���������͸���������������
 * @author [�뽫�˴��޸�Ϊ��161250095_�����]
 *
 */
import java.util.ArrayList;
public class ALU {

	/**
	 * ����ʮ���������Ķ����Ʋ����ʾ��<br/>
	 * ����integerRepresentation("9", 8)
	 * @param number ʮ������������Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʋ����ʾ�ĳ���
	 * @return number�Ķ����Ʋ����ʾ������Ϊlength
	 */
	public String integerRepresentation (String number, int length) {
		// TODO YOUR CODE HERE.
		ArrayList<String> end=new ArrayList<>();
		  if(number.charAt(0)!=0){
		 int num=Integer.parseInt(number);
		 if(num<Math.pow(2, length)){
		 for(int i=length;i>0;i--){
		       if((num&(1<<(i-1)))!=0)
		       end.add(length-i,"1");
		       else
		   end.add(length-i,"0");
		     }
		   }
		  }
		  else {
			  end.add(0,"1");
			  StringBuffer str=new StringBuffer();
			  for(int i=1;i<length;i++){
				  str.append(number.charAt(i));
			  }
			  String change=str.toString();
			int num=Integer.parseInt(change);
			
			if(num<Math.pow(2, length-1)){
			  for(int i=length-1;i>0;i--){
			       if((num&(1<<(i-1)))!=0)
			    	   end.add(length-i,"0");
			       else
			    	   end.add(length-i,"1");
			    }
			  for(int i=0;i<length-1;i++){
			  if(end.get(length-i).equals("0")){
				  end.set(length-i, "1");
				  break;
			  }  
			  else{
				  end.set(length-i, "0");
			  }
			  }
		  }
			
	}       
		  StringBuffer str=new StringBuffer();
		  for(int i=0;i<end.size();i++){
			  str.append(end.get(i));
		  }
		  String s=str.toString();
		return s;
	}
	
	/**
	 * ����ʮ���Ƹ������Ķ����Ʊ�ʾ��
	 * ��Ҫ���� 0������񻯡����������+Inf���͡�-Inf������ NaN�����أ������� IEEE 754��
	 * �������Ϊ��0���롣<br/>
	 * ����floatRepresentation("11.375", 8, 11)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return number�Ķ����Ʊ�ʾ������Ϊ 1+eLength+sLength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		int exp=(int)(Math.pow(2, eLength-1))-1;
		final double small=Math.pow(2, -exp+1);
		ArrayList<String> end=new ArrayList<>();
		
		double body=0.0;//��ʾ����
		int num=0;//��ʾ��������
		double decimal=0.0;//��ʾС������

		if(number.equals("NaN")){
			end.add("1");
			for(int i=0;i<eLength;i++)
				end.add("1");
		}
		else{
			if(!number.substring(0,1).equals("-")){
				end.add("0");
				if(number.equals("+Inf")){
					for(int i=0;i<eLength;i++)
						end.add("1");
					for(int i=0;i<sLength;i++)
						end.add("0");
				}else if(number.equals("0"))
					for(int i=0;i<eLength+sLength;i++)
						end.add("0");
				else{
					body=Double.parseDouble(number);
					if(body<small){
						for(int i=0;i<eLength;i++)
							  end.add("0");
						  for(int i=0;i<sLength;i++){
							  if(body<Math.pow(2, -exp+1-i)&&body>=Math.pow(2, -exp-i)){
								  end.add("1");
								  body-=Math.pow(2, -exp-i);
							  }
							  else
								  end.add("0");
						   }
						 }
				
						else{
						 int t=0;
					     while(!number.substring(t,t+1).equals(".")){
					    	 t+=1;
					     }
					    String m=number.substring(0,t);
						   num=Integer.parseInt(m);
					    decimal=body-num;
					
						String temp=Integer.toBinaryString(num);
						int result=1;
						for(int i=0;i<eLength-1;i++)
							result<<=1;
						result-=1;
						if(temp.length()>1||temp.equals("1")){
							result+=temp.length()-1;
							 String center=Integer.toBinaryString(result);
					    if(center.length()<eLength)
					    	for(int i=0;i<eLength-center.length();i++)
					    		end.add("0");
					    for(int i=0;i<center.length();i++)
					    	end.add(center.substring(i,i+1));
						
						for(int i=1;i<temp.length();i++)
							end.add(temp.substring(i,i+1));
						int dLength=sLength-temp.length()+1;//��¼С�����ֵĳ���
						for(int i=0;i<dLength;i++){
							decimal*=2;
							if(decimal>=1){
								end.add("1");
								decimal-=1;
							}
							else
								end.add("0");
						}
						}
						else{
							StringBuffer str=new StringBuffer();
							
							str.append("0");
							for(int i=0;i<sLength;i++){
								decimal*=2;
								if(decimal>=1){
									str.append("1");
									decimal-=1;
								}
								else
									str.append("0");
							}
							
							temp=str.toString();
							while(!temp.substring(0,1).equals("1")){
								temp=leftShift(temp,1);
							    result-=1;
							}
							 String center=Integer.toBinaryString(result);
							    if(center.length()<eLength)
							    	for(int i=0;i<eLength-center.length();i++)
							    		end.add("0");
							    for(int i=0;i<center.length();i++)
							    	end.add(center.substring(i,i+1));
								
								for(int i=1;i<temp.length();i++)
									end.add(temp.substring(i,i+1));
						}
						
					   
					}
			    }
			}
			else{
				end.add("1");
				StringBuffer str=new StringBuffer();
				  for(int i=1;i<number.length();i++){
					  str.append(number.charAt(i));
				  }
				  String change=str.toString();
				  if(change.equals("Inf")){
						for(int i=0;i<eLength;i++)
							end.add("1");
						for(int i=0;i<sLength;i++)
							end.add("0");
					}else if(change.equals("0"))
						for(int i=0;i<eLength+sLength;i++)
							end.add("0");
					else{
						body=Double.parseDouble(change);
						
					    if(body<small){
					  for(int i=0;i<eLength;i++)
						  end.add("0");
					  for(int i=0;i<sLength;i++){
						  if(body<Math.pow(2, -exp+1-i)&&body>=Math.pow(2, -exp-i)){
							  end.add("1");
							  body-=Math.pow(2, -exp-i);
						  }
						  else
							  end.add("0");
					  }
					 
					}				
					    
					else{
						int t=0;
					     while(!change.substring(t,t+1).equals(".")){
					    	 t+=1;
					     }
					    String m=change.substring(0,t);
						   num=Integer.parseInt(m);
					    decimal=body-num;
					
						String temp=Integer.toBinaryString(num);
						int result=1;
						for(int i=0;i<eLength-1;i++)
							result<<=1;
						result-=1;
						if(temp.length()>1||temp.equals("1")){
							result+=temp.length()-1;
							 String center=Integer.toBinaryString(result);
					    if(center.length()<eLength)
					    	for(int i=0;i<eLength-center.length();i++)
					    		end.add("0");
					    for(int i=0;i<center.length();i++)
					    	end.add(center.substring(i,i+1));
						
						for(int i=1;i<temp.length();i++)
							end.add(temp.substring(i,i+1));
						int dLength=sLength-temp.length()+1;//��¼С�����ֵĳ���
						for(int i=0;i<dLength;i++){
							decimal*=2;
							if(decimal>=1){
								end.add("1");
								decimal-=1;
							}
							else
								end.add("0");
						}
						}
						else{
							StringBuffer str1=new StringBuffer();
							
							str1.append("0");
							for(int i=0;i<sLength;i++){
								decimal*=2;
								if(decimal>=1){
									str1.append("1");
									decimal-=1;
								}
								else
									str1.append("0");
							}
							
							temp=str1.toString();
							while(!temp.substring(0,1).equals("1")){
								temp=leftShift(temp,1);
							    result-=1;
							}
							 String center=Integer.toBinaryString(result);
							    if(center.length()<eLength)
							    	for(int i=0;i<eLength-center.length();i++)
							    		end.add("0");
							    for(int i=0;i<center.length();i++)
							    	end.add(center.substring(i,i+1));
								
								for(int i=1;i<temp.length();i++)
									end.add(temp.substring(i,i+1));
						}
					}
				}
			}
		}
		StringBuffer str=new StringBuffer();
		  for(int i=0;i<end.size();i++){
			  str.append(end.get(i));
		  }
		  String s=str.toString();
		return s;
	}
	
	/**
	 * ����ʮ���Ƹ�������IEEE 754��ʾ��Ҫ�����{@link #floatRepresentation(String, int, int) floatRepresentation}ʵ�֡�<br/>
	 * ����ieee754("11.375", 32)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʊ�ʾ�ĳ��ȣ�Ϊ32��64
	 * @return number��IEEE 754��ʾ������Ϊlength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String ieee754 (String number, int length) {
		// TODO YOUR CODE HERE.
		if(length==32)
		return floatRepresentation(number, 8, length-9);
		else
			return floatRepresentation(number, 11, length-12);

	}
	
	/**
	 * ��������Ʋ����ʾ����������ֵ��<br/>
	 * ����integerTrueValue("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 */
	public String integerTrueValue (String operand) {
		// TODO YOUR CODE HERE.
		ArrayList<String> end=new ArrayList<>();
		int number=0;
		String s=null;
		if(operand.substring(0,1).equals("0")){
			for(int i=operand.length()-1;i>0;i--){
				number+=(Integer.parseInt(operand.substring(i,i+1)))*Math.pow(2, operand.length()-i-1);
			    s=Integer.toString(number);
			}
			
		}
		else{
			for(int i=0;i<operand.length();i++)
			    end.add(operand.substring(i,i+1));
			
			for(int i=end.size()-1;i>=0;i--){
			if(end.get(i).equals("0"))
				end.set(i,"1");
			else{
				end.set(i,"0");
				break;
			   }
			}
			for(int i=end.size()-1;i>=0;i--){
				if(end.get(i).equals("0"))
					end.set(i,"1");
				else
					end.set(i,"0");
			}
			String temp=null;
			for(int i=end.size()-1;i>=0;i--)
				number+=(Integer.parseInt(end.get(i)))*Math.pow(2,end.size()-i-1);
			    
			    temp=Integer.toString(number);
			
				StringBuffer str=new StringBuffer();
				str.append("-");
				for(int i=0;i<temp.length();i++)
					str.append(temp.charAt(i));
				
				s=str.toString();
			}
		
		return s;
	}
	
	/**
	 * ���������ԭ���ʾ�ĸ���������ֵ��<br/>
	 * ����floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ����������ֱ��ʾΪ��+Inf���͡�-Inf���� NaN��ʾΪ��NaN��
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE
		
		String s=null;
		int exp=0;//��ʾָ��
		String expe=null;//��ʾָ���ַ���
		double temp=1.0;//��ʾС��
		if(operand.length()==1+eLength){
			for(int i=0;i<operand.length();i++)
				if(!operand.substring(i,i+1).equals("1"))
					break;
				else if(i==eLength)
					s="NaN";
		}
		else if(operand.substring(0, 1).equals("0")){
	
			int m=1;
			while(operand.substring(m,m+1).equals("1")){
				m++;
			}
			if(m>=1+eLength)
				s="+Inf";
			
			else if((integerTrueValue(operand.substring(1,1+eLength)).equals("0"))&&(!integerTrueValue(operand.substring(1+eLength)).equals("0"))){
				double result=0;
				int expo=(int)(Math.pow(2, eLength-1))-1;
				for(int i=1+eLength;i<operand.length();i++)
					if(operand.substring(i,i+1).equals("1")){
						result+=Math.pow(2,-expo+1-i+eLength);
					}
				s=Double.toString(result);
			}
			else{
			
			StringBuffer str=new StringBuffer();
			  str.append("0");
			  for(int i=1;i<eLength+1;i++)
				  str.append(operand.charAt(i));
			  
			  String end1=str.toString();
			  expe=integerTrueValue(end1);
			  exp=Integer.parseInt(expe);
			  exp-=Math.pow(2,eLength-1)-1;
			  double mul=Math.pow(2, exp);//mul��ʾ����
			  
			  for(int i=0;i<sLength;i++)
				  temp+=Integer.parseInt(operand.substring(1+eLength+i,2+eLength+i))*Math.pow(2,-i-1);
			  
			  temp*=mul;
			  s=Double.toString(temp);
			  
			  int n=1;
			while(operand.substring(n,n+1).equals("0")){
				n++;
				if(n==1+eLength+sLength){
				s="0";
				break;
				}
			}
			}
		}
		else{
			 if((integerTrueValue(operand.substring(1,1+eLength)).equals("0"))&&(!integerTrueValue(operand.substring(1+eLength)).equals("0"))){
				double result=0;
				int expo=(int)(Math.pow(2, eLength-1))-1;
				for(int i=1+eLength;i<operand.length();i++)
					if(operand.substring(i,i+1).equals("1")){
						result+=Math.pow(2,-expo+1-i+eLength);
					}
				s="-"+Double.toString(result);
			}
			 else{
			StringBuffer str=new StringBuffer();
			  str.append("0");
			  for(int i=1;i<eLength+1;i++)
				  str.append(operand.charAt(i));
			  
			  String end1=str.toString();
			  expe=integerTrueValue(end1);
			  exp=Integer.parseInt(expe);
			  exp-=Math.pow(2,eLength-1)-1;
			  double mul=Math.pow(2, exp);//mul��ʾ����
			  
			  for(int i=0;i<sLength;i++)
				  temp+=Integer.parseInt(operand.substring(1+eLength+i,2+eLength+i))*Math.pow(2,-i-1);
			  
			  temp*=mul;
			  String sPart=Double.toString(temp);
			  
			  StringBuffer newStr=new StringBuffer();
			  newStr.append("-");
			  newStr.append(sPart);
			  
			  s=newStr.toString();
			  
			
			int m=1;
		while(operand.substring(m,m+1).equals("1")){
			m++;
		}
		if(m>=1+eLength)
			s="NaN";
		else{
		int n=1;
		while(operand.substring(n,n+1).equals("0")){
			n++;
			if(n==1+eLength+sLength){
			s="-0";
			break;
			}
		}
		}
		}
		}
		if(s.equals("0"))
			return "0.0";
		
		return s;
	}
	
	/**
	 * ��λȡ��������<br/>
	 * ����negation("00001001")
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @return operand��λȡ���Ľ��
	 */
	public String negation (String operand) {
		// TODO YOUR CODE HERE.
		ArrayList<String> end=new ArrayList<>();
		for(int i=0;i<operand.length();i++){
			if(operand.substring(i, i+1).equals("0"))
				end.add("1");
			else
				end.add("0");
		}
		StringBuffer str=new StringBuffer();
		  for(int i=0;i<end.size();i++){
			  str.append(end.get(i));
		  }
		  String s=str.toString();
		return s;
	}
	
	/**
	 * ���Ʋ�����<br/>
	 * ����leftShift("00001001", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand����nλ�Ľ��
	 */
	public String leftShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		StringBuffer str=new StringBuffer();
		
		if(n<operand.length()){
		str.append(operand.substring(n,operand.length()));
		for(int i=0;i<n;i++)
			str.append("0");
		}
		else
			for(int i=0;i<operand.length();i++)
				str.append("0");
		
		return str.toString();
	}
	
	/**
	 * �߼����Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand�߼�����nλ�Ľ��
	 */
	public String logRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		StringBuffer str=new StringBuffer();
		if(n<operand.length()){
		for(int i=0;i<n;i++)
			str.append("0");
		
		str.append(operand.substring(0,operand.length()-n));
		}
		else
			for(int i=0;i<operand.length();i++)
				str.append("0");
		return str.toString();
	}
	
	/**
	 * �������Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand��������nλ�Ľ��
	 */
	public String ariRightShift (String operand, int n) {
		// TODO YOUR CODE HERE.
		StringBuffer str=new StringBuffer();
		
		if(operand.length()>n){
		if(operand.substring(0,1).equals("0"))
		    for(int i=0;i<n;i++)
			    str.append("0");
		else
			for(int i=0;i<n;i++)
			    str.append("1");
		
		str.append(operand.substring(0,operand.length()-n));
		}
		else{
			if(operand.substring(0,1).equals("0"))
			    for(int i=0;i<operand.length();i++)
				    str.append("0");
			else
				for(int i=0;i<operand.length();i++)
				    str.append("1");
		}
		return str.toString();
	}
	
	/**
	 * ȫ����������λ�Լ���λ���мӷ����㡣<br/>
	 * ����fullAdder('1', '1', '0')
	 * @param x ��������ĳһλ��ȡ0��1
	 * @param y ������ĳһλ��ȡ0��1
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
	 */
	public String fullAdder (char x, char y, char c) {
		// TODO YOUR CODE HERE.
		int m=0,n=0,p=0;//������תxyc
	
		if(x=='1')
			m=1;
		else
			m=0;
		if(y=='1')
			n=1;
		else
			n=0;
		if(c=='1')
			p=1;
		else
			p=0;
		
		int s=m^n^p;
		int t=m*n+p*(m^n);
		StringBuffer str=new StringBuffer();
		if(t==0)
			str.append("0");
		else
			str.append("1");
		if(s==0)
			str.append("0");
		else
			str.append("1");
	 
		return str.toString();
	}
	
	/**
	 * 4λ���н�λ�ӷ�����Ҫ�����{@link #fullAdder(char, char, char) fullAdder}��ʵ��<br/>
	 * ����claAdder("1001", "0001", '1')
	 * @param operand1 4λ�����Ʊ�ʾ�ı�����
	 * @param operand2 4λ�����Ʊ�ʾ�ļ���
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ����Ϊ5���ַ�����ʾ�ļ����������е�1λ�����λ��λ����4λ����ӽ�������н�λ��������ѭ�����
	 */
	public String claAdder (String operand1, String operand2, char c) {
		// TODO YOUR CODE HERE.
		 
          String p3 = fullAdder(operand1.charAt(3), operand2.charAt(3), c);
          String p2 = fullAdder(operand1.charAt(2), operand2.charAt(2), p3.charAt(0));
          String p1 = fullAdder(operand1.charAt(1), operand2.charAt(1), p2.charAt(0));
          String p0 = fullAdder(operand1.charAt(0), operand2.charAt(0), p1.charAt(0));
          StringBuffer str=new StringBuffer();
        str.append(p0);
        str.append(p1.substring(1));
        str.append(p2.substring(1));
        str.append(p3.substring(1));
          return str.toString();
	}
	
	/**
	 * ��һ����ʵ�ֲ�������1�����㡣
	 * ��Ҫ�������š����š�����ŵ�ģ�⣬
	 * ������ֱ�ӵ���{@link #fullAdder(char, char, char) fullAdder}��
	 * {@link #claAdder(String, String, char) claAdder}��
	 * {@link #adder(String, String, char, int) adder}��
	 * {@link #integerAddition(String, String, int) integerAddition}������<br/>
	 * ����oneAdder("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder (String operand) {
		// TODO YOUR CODE HERE.
		StringBuffer str=new StringBuffer();
		StringBuffer newStr=new StringBuffer();
		int temp=0;
		
	
		for(int i=operand.length()-1;i>=0;i--){
			str.append(Integer.toString(Integer.parseInt(operand.substring(i, i+1))^1));
			if((Integer.parseInt(operand.substring(i, i+1))&1)==0){
				temp=i;
				break;
			}
		}
		if(temp!=0){
		for(int i=temp-1;i>=0;i--){
			str.append(operand.substring(i,i+
					1));
		}
		
			str.append("0");
		}
		else if(operand.substring(0,1).equals("1"))
			str.append("0");
		else
			str.append("1");
		
		
		for(int i=operand.length();i>=0;i--){
			newStr.append(str.substring(i,i+1));
		}
		
	
		return newStr.toString();
	}
	public String oneSub(String operand){
		StringBuffer str=new StringBuffer();
		StringBuffer newStr=new StringBuffer();
		int temp=0;
		
		
		for(int i=operand.length()-1;i>=0;i--){
			str.append(Integer.toString(Integer.parseInt(operand.substring(i, i+1))^1));
			if((Integer.parseInt(operand.substring(i, i+1))|0)==1){
				temp=i;
				break;
			}
		}
		if(temp!=0){
		for(int i=temp-1;i>=0;i--){
			str.append(operand.substring(i,i+
					1));
		}
		
			str.append("0");
		}
		else if(operand.substring(0,1).equals("0"))
			str.append("1");
		else
			str.append("0");
		
		
		for(int i=operand.length();i>=0;i--){
			newStr.append(str.substring(i,i+1));
		}
		
		return newStr.toString();
	}
	
	/**
	 * �ӷ�����Ҫ�����{@link #claAdder(String, String, char)}����ʵ�֡�<br/>
	 * ����adder("0100", "0011", ��0��, 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param c ���λ��λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String adder (String operand1, String operand2, char c, int length) {
		// TODO YOUR CODE HERE.
		   String result = "";
           int count = length/4;
           StringBuffer str1=new StringBuffer();
           StringBuffer str2=new StringBuffer();
           if(operand1.length()<length){
				for(int i=0;i<(length-operand1.length());i++)
					str1.append(operand1.substring(0, 1));
				str1.append(operand1);
				operand1=str1.toString();
		}
		
		if(operand2.length()<length){
			for(int i=0;i<(length-operand2.length());i++)
				str2.append(operand2.substring(0, 1));
			str2.append(operand2);
			operand2=str2.toString();
	}
	
           for(int i=0;i<count;i++){
                   result=claAdder(operand1.substring(length-4-4*i,length-4*i),operand2.substring(length-4-4*i,length-4*i),c).substring(1)+result;
                   c=claAdder(operand1.substring(length-4-4*i,length-4*i),operand2.substring(length-4*i-4,length-4*i),c).charAt(0);
           }
           if(operand1.charAt(0)==operand2.charAt(0)&&operand1.charAt(0)!=result.charAt(0))
                   result='1'+result;
           else
                   result='0'+result;
           
           int overflow=(int)(Math.pow(2, length-1));
           if(Integer.parseInt(integerTrueValue(operand1))+Integer.parseInt(integerTrueValue(operand2))>=overflow)
        	   return "1"+result.substring(1);
           
           return result;
	}
	
	/**
	 * �����ӷ���Ҫ�����{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerAddition("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String integerAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
	
		return adder(operand1,operand2,'0',length);
	}
	
	/**
	 * �����������ɵ���{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerSubtraction("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ��������
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.

		int temp=Integer.parseInt(integerTrueValue(operand2));
		
		operand2=integerRepresentation(Integer.toString(-temp),operand2.length());
		
		return adder(operand1,operand2,'0',length);
	}
	
	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #adder(String, String, char, int) adder}�ȷ�����<br/>
	 * ����integerMultiplication("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ����˽�������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����˽��
	 */
	public String integerMultiplication (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		
		StringBuffer str1=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		StringBuffer str3=new StringBuffer();
		StringBuffer str4=new StringBuffer();
		
		if(operand1.length()<length){
				for(int i=0;i<(length-operand1.length());i++)
					str1.append(operand1.substring(0, 1));
				str1.append(operand1);
				operand1=str1.toString();
		}
		
		if(operand2.length()<length){
			for(int i=0;i<(length-operand2.length());i++)
				str2.append(operand2.substring(0, 1));
			str2.append(operand2);
			operand2=str2.toString();
	}
		
		String q="0";//q=Q-1
		String a=null;
		int count=length;
		
		for(int i=0;i<length;i++)
			str3.append("0");
		
		str3.append(operand2);
		str3.append(q);
		String s=str3.toString();
		
		
		 while(count!=0){
				if(!s.substring(s.length()-1,s.length()).equals(s.substring(s.length()-2,s.length()-1))){
					if(s.substring(s.length()-1,s.length()).equals("0")){
						int temp=Integer.parseInt(integerTrueValue(operand1));
						String operand=integerRepresentation(Integer.toString(-temp),length);
						a=adder(s.substring(0,length),operand,'0', length);
						a=a.substring(1,a.length());
                         
					}
					else{
						a=adder(s.substring(0,length),operand1,'0', length);
						a=a.substring(1,a.length());
					}
					    str3.setLength(0);
						str3.append(a);
						str3.append(s.substring(length,s.length()));
						s=str3.toString();	
					}
				    s=ariRightShift(s,1);
					count--;
				}
		
		boolean overflow=false;
		for(int i=0;i<s.length()-length-1;i++)
			if(!s.substring(i,i+1).equals(s.substring(i+1,i+2))){
				overflow=true;
				break;
			}
		
		if(overflow)
			str4.append("1");
		else
			str4.append("0");
		
		str4.append(s.substring(s.length()-length-1,s.length()-1));
		
		return str4.toString();
	}
	
	/**
	 * �����Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int) adder}�ȷ���ʵ�֡�<br/>
	 * ����integerDivision("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣����lengthλΪ����
	 */
	public String integerDivision (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		
		for(int i=0;i<operand2.length();i++)
			if(!operand2.substring(i,i+1).equals("0"))
				break;
			else if(i==operand2.length()-1)
				return "NaN";
	
		Boolean isPos1=true;
		Boolean isPos2=true;
		if(operand1.substring(0,1).equals("1"))
			isPos1=false;
		if(operand2.substring(0,1).equals("1"))
			isPos2=false;
	
		StringBuffer str1=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		
		if(operand1.length()<length){
			for(int i=0;i<(length-operand1.length());i++)
				str1.append(operand1.substring(0, 1));
			str1.append(operand1);
			operand1=str1.toString();
	}
	
	if(operand2.length()<length){
		for(int i=0;i<(length-operand2.length());i++)
			str2.append(operand2.substring(0, 1));
		str2.append(operand2);
		operand2=str2.toString();
}
	StringBuffer str3=new StringBuffer();
	for(int i=0;i<length;i++)
		str3.append(operand1.substring(0,1));
	
	str3.append(operand1);
	String s=str3.toString();
	
	int count=length;
	String negate=null;
	if(operand2.substring(0,1).equals("1")){
		negate=operand2;
		
	}
	else{
		negate=minus(operand2);
	}
	
	String cal=null;
	StringBuffer str4=new StringBuffer();
	while(count!=0){
		s=leftShift(s,1);
		if(operand1.substring(0,1).equals("0")){
		cal=adder(s.substring(0,length),negate,'0',length);
		cal=cal.substring(1);
		if(cal.substring(0,1).equals("0")){
			str4.setLength(0);
			str4.append(cal);
			str4.append(s.substring(length,s.length()-1));
			str4.append("1");
			s=str4.toString();
		        }
		}
		else{
		
			cal=adder(s.substring(0,length),minus(negate),'0',length);
			cal=cal.substring(1);
			if(cal.substring(0,1).equals("1")){
				str4.setLength(0);
				str4.append(cal);
				str4.append(s.substring(length,s.length()-1));
				str4.append("1");
				s=str4.toString();
			        }
			if(integerTrueValue(cal).equals("0")){
				str4.setLength(0);
				str4.append(cal);
				str4.append(s.substring(length,s.length()-1));
				str4.append("1");
				s=str4.toString();
			}
		}
		count--;
	}
	
	String overflow=adder(operand1,minus(operand2),'0',length);
	overflow=overflow.substring(1,overflow.length());
	
	  StringBuffer str5=new StringBuffer();
	  if(overflow.substring(0,1).equals("1")&&Integer.toString(Integer.parseInt(operand1.substring(0,1))^Integer.parseInt(operand2.substring(0,1))).equals("0"))
	  str5.append("1");
	  else
		  str5.append("0");
	  if(!(isPos1^isPos2))
	  str5.append(s.substring(length));
	  else
		  str5.append(minus(s.substring(length)));  
	  str5.append(s.substring(0,length));
	  
		
		return str5.toString();
	}
	
	/**
	 * �����������ӷ������Ե���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * ������ֱ�ӽ�������ת��Ϊ�����ʹ��{@link #integerAddition(String, String, int) integerAddition}��
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}��ʵ�֡�<br/>
	 * ����signedAddition("1100", "1011", 8)
	 * @param operand1 ������ԭ���ʾ�ı����������е�1λΪ����λ
	 * @param operand2 ������ԭ���ʾ�ļ��������е�1λΪ����λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ����������ţ�����ĳ���������ĳ���С��lengthʱ����Ҫ���䳤����չ��length
	 * @return ����Ϊlength+2���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������2λΪ����λ����lengthλ����ӽ��
	 */
	public String signedAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		boolean is1=false;//����Ϊ��
		boolean is2=false;
		if(operand1.substring(0,1).equals("0"))
			is1=true;
		if(operand2.substring(0,1).equals("0"))
			is2=true;
		
		StringBuffer str1=new StringBuffer();
		if(operand1.length()-2<length)
		   for(int i=0;i<length-operand1.length()+2;i++)
			   str1.append("0");
		       
		       str1.append(operand1.substring(1));
		       operand1=str1.toString();
		
		StringBuffer str2=new StringBuffer();
		if(operand2.length()-2<length)
		   for(int i=0;i<length-operand2.length()+2;i++)
			   str2.append("0");
		       
		       str2.append(operand2.substring(1));
		       operand2=str2.toString();
		
		 String result=null;
		       
		StringBuffer str3=new StringBuffer();
		if(is1&&is2){
			String temp=adder(operand1,operand2,'0',length+1);
			str3.append(temp.substring(0,1));
			str3.append("0");
			str3.append(temp.substring(1));
			result=str3.toString();
			
		}
		else if(!(is2||is1)){
			String temp=adder(operand1,operand2,'0',length+1);
			str3.append(temp.substring(0,1));
			str3.append("1");
			str3.append(temp.substring(1));
			result=str3.toString();
		}
		else{
			String temp=integerSubtraction(operand1,operand2,length+1);
			boolean is=is1;
			
			if(temp.substring(1,2).equals("1")&&temp.substring(0,1).equals("0")){
				temp=integerSubtraction(operand2,operand1,length);
				is=is2;
			}
			
			str3.append("0");
			
				if(is)
					str3.append("0");
				else
					str3.append("1");
			
			str3.append(temp.substring(1));
			result=str3.toString();
		}
		
		return result;
	}
	
	/**
	 * �������ӷ����ɵ���{@link #signedAddition(String, String, int) signedAddition}�ȷ���ʵ�֡�<br/>
	 * ����floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����ӽ�������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatAddition (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		boolean isPos1=true;
		boolean isPos2=true;
		if(operand1.substring(0,1).equals("1"))
		  isPos1=false;
		if(operand2.substring(0,1).equals("1"))
		  isPos2=false;
		
		StringBuffer str1=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		StringBuffer str3=new StringBuffer();
		String result=null;
		String convert1=counterFrameShift(operand1.substring(1,1+eLength));
		String convert2=counterFrameShift(operand2.substring(1,1+eLength));
		if(!(isPos1^isPos2)){//ͬ��
			if(integerTrueValue(operand1.substring(1)).equals("0"))
			    result="0"+operand2;
			else if(integerTrueValue(operand2.substring(1)).equals("0"))
			    result="0"+operand1;
			else{//��0
			String expCompare=integerSubtraction(convert1,convert2,eLength);//�ж�ָ����С
			if(integerTrueValue(expCompare).equals("0")){//ָ����ͬ
				str1.append("00");
				String exp=frameShift(oneAdder(convert1).substring(1));
				str1.append(exp);
				String addDec=signedAddition(addFourZero(operand1.substring(1+eLength)),addFourZero(operand2.substring(1+eLength)),sLength+4);
				if(addDec.substring(0,1).equals("1")){//С�����
					str1.append("1"+addDec.substring(6,addDec.length()-1));
				}
				else
					str1.append("0"+addDec.substring(6,addDec.length()-1));
				result=str1.toString();
			}
			else if(expCompare.substring(0,1).equals("0")){//1ָ����
				
			  int move=Integer.parseInt(integerTrueValue(expCompare));
			  str2.append(operand2.substring(1+eLength));
			  for(int i=0;i<gLength;i++)
				  str2.append("0");
			  String decimal=logRightShift(str2.toString(), move);
			  str3.append(operand1.substring(1+eLength));
			  for(int i=0;i<gLength;i++)
				  str3.append("0");
			  str1.append("00");
			  String exp=frameShift(oneAdder(convert1).substring(1));
			  if(integerTrueValue(operand2).equals("0"))
				  str1.append(operand1.substring(1,1+eLength));
			  else
				str1.append(exp);
				String addDec=signedAddition(addFourZero(str3.toString()),addFourZero(decimal),sLength+gLength+4);
				if(addDec.substring(0,1).equals("1")){//С�����
					str1.append("1"+addDec.substring(7,addDec.length()-gLength));
				}
				else
					str1.append("0"+addDec.substring(7,addDec.length()-gLength));
				
				result=str1.toString();
			}
			else{//2ָ����
				expCompare=integerSubtraction(convert2,convert1,eLength);
				int move=Integer.parseInt(integerTrueValue(expCompare));
				  str2.append(operand1.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str2.append("0");
				  String decimal=logRightShift(str2.toString(), move);
				  str3.append(operand2.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str3.append("0");
				  str1.append("00");
				  String exp=frameShift(oneAdder(convert2).substring(1));
				  if(integerTrueValue(operand1).equals("0"))
					  str1.append(operand2.substring(1,1+eLength));
				  else
					str1.append(exp);
					String addDec=signedAddition(addFourZero(str3.toString()),addFourZero(decimal),sLength+gLength+4);
					if(addDec.substring(0,1).equals("1")){//С�����
						str1.append("1"+addDec.substring(7,addDec.length()-gLength));
					}
					else
						str1.append("0"+addDec.substring(7,addDec.length()-gLength));
					
					result=str1.toString();
			   }
			}
		}
		else{//���
			String expCompare=integerSubtraction(convert1,convert2,eLength);//�ж�ָ����С
            if(integerTrueValue(expCompare).equals("0")){//ָ����ͬ
				String decCompare=integerSubtraction(addFourZero(operand1.substring(1+eLength)),addFourZero(operand2.substring(1+eLength)),4+sLength);
				if(integerTrueValue(decCompare).equals("0")){//β����ͬ
					for(int i=0;i<2+eLength+sLength;i++)
						str1.append("0");
				}
				else if(decCompare.substring(0,1).equals("0")){
						str1.append("00");
						String change=decCompare.substring(5);
						String subd=convert1;
						while(change.substring(0,1).equals("0")){
							change=leftShift(change,1);
							subd=oneSub(subd).substring(1);
						}
						change=leftShift(change,1);
						subd=oneSub(subd).substring(1);
						str1.append(frameShift(subd));
					str1.append(change);
				}
				else{
					decCompare=integerSubtraction(addFourZero(operand2.substring(1+eLength)),addFourZero(operand1.substring(1+eLength)),sLength+4);
						str1.append("00");
						String change=decCompare.substring(5);
						String subd=convert2;
						while(change.substring(0,1).equals("0")){
							change=leftShift(change,1);
							subd=oneSub(subd).substring(1);
						}
						change=leftShift(change,1);
						subd=oneSub(subd).substring(1);
						str1.append(frameShift(subd));
					str1.append(change);
				}
				result=str1.toString();
			}
			else if(expCompare.substring(1,2).equals("0")){//1ָ����
				if(integerTrueValue(operand2.substring(1)).equals("0"))
					result="0"+operand1;
				else{
				 int move=Integer.parseInt(integerTrueValue(expCompare));
				  str2.append(operand2.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str2.append("0");
				  String decimal="1"+str2.toString();
				  decimal=logRightShift(decimal, move);
				  str3.append(operand1.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str3.append("0");
				  
				  String large="1"+str3.toString();
				  str1.append("0");
				 
				  String subDec=integerSubtraction(addFourZero(large),addFourZero(decimal),5+sLength+gLength);
				  String changeExp=oneSub(convert1);
				   str1.append(operand1.substring(0,1));
				   str1.append(frameShift(changeExp.substring(1)));
			      str1.append(subDec.substring(6,subDec.length()-gLength+1));
			      result=str1.toString();
				}
			}
			else{//2ָ����
				if(integerTrueValue(operand1.substring(1)).equals("0"))
					result="0"+negation(operand2.substring(0,1))+operand2.substring(1);
				expCompare=integerSubtraction(convert2,convert1,eLength);
				int move=Integer.parseInt(integerTrueValue(expCompare));
				
				  str2.append(operand1.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str2.append("0");
				  String decimal="1"+str2.toString();
					decimal=logRightShift(decimal, move);
				  str3.append(operand2.substring(1+eLength));
				  for(int i=0;i<gLength;i++)
					  str3.append("0");
				  String large="1"+str3.toString();
				  str1.append("0");
				  
				  String subDec=integerSubtraction(addFourZero(large),addFourZero(decimal),5+sLength+gLength);
				  String changeExp=oneSub(convert2);
				  str1.append(operand2.substring(0,1));
				  str1.append(frameShift(changeExp.substring(1)));
			      str1.append(subDec.substring(6,subDec.length()-gLength+1));
			      result=str1.toString();
			}
		}
		return result;
	}
	
	/**
	 * �������������ɵ���{@link #floatAddition(String, String, int, int, int) floatAddition}����ʵ�֡�<br/>
	 * ����floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ�������������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatSubtraction (String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		StringBuffer str=new StringBuffer();
		if(operand2.substring(0,1).equals("0"))
			str.append("1");
		else
			str.append("0");
		
		str.append(operand2.substring(1));
		
		return floatAddition(operand1,str.toString(),eLength,sLength,gLength);
		
	}
	
	/**
	 * �������˷����ɵ���{@link #integerMultiplication(String, String, int) integerMultiplication}�ȷ���ʵ�֡�<br/>
	 * ����floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatMultiplication (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		if(integerTrueValue(operand1.substring(1)).equals("0"))
			return "00"+operand1.substring(1);
		if(integerTrueValue(operand2.substring(1)).equals("0"))
			return "00"+operand2.substring(1);
		
		StringBuffer str=new StringBuffer();
		str.append("0");
		if(operand1.substring(0,1).equals(operand2.substring(0,1)))
            str.append("0");
		else 
			str.append("1");
		
		String exp=integerAddition(counterFrameShift(operand1.substring(1,1+eLength)),counterFrameShift(operand2.substring(1,1+eLength)),eLength).substring(1);
		str.append(frameShift(exp));
		String shift1="01"+operand1.substring(1+eLength);
		String shift2="01"+operand2.substring(1+eLength);
		String result=integerMultiplication(shift1,shift2,2*(sLength+2));
		str.append(result.substring(5,result.length()-sLength));
			 return str.toString();
	}
	
	/**
	 * �������������ɵ���{@link #integerDivision(String, String, int) integerDivision}�ȷ���ʵ�֡�<br/>
	 * ����floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatDivision (String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
	
		
		String result=null;
		
		StringBuffer str=new StringBuffer();
		
		if(integerTrueValue(operand1.substring(1)).equals("0"))
				for(int i=0;i<2+eLength+sLength;i++)
					str.append("0");	
		else{
		str.append("0");
		if(operand1.substring(0,1).equals(operand2.substring(0, 1)))
			str.append("0");
		else
			str.append("1");
		
		String exp=integerSubtraction(counterFrameShift(operand1.substring(1,1+eLength)),counterFrameShift(operand2.substring(1,1+eLength)),eLength).substring(1);
		if(integerTrueValue(operand2.substring(1,1+eLength)).equals("0"))
				exp=integerAddition(counterFrameShift(operand1.substring(1,1+eLength)),counterFrameShift(operand2.substring(1,1+eLength)),eLength).substring(1);

		str.append(frameShift(exp));
		String shift1="01"+operand1.substring(1+eLength);
		String shift2="01"+operand2.substring(1+eLength);
		String decimal=integerDivision(shift1,shift2,2+sLength);
		str.append(decimal.substring(6)+"000");
		}
		result=str.toString();
		
		return result;
	}
	
	public String minus(String operand){
		
		String s=null;
		StringBuffer str1=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		if(operand.substring(0,1).equals("0")){
			s=negation(operand);
			int temp=0;
			for(int i=s.length();i>1;i--){
				if(s.substring(i-1,i).equals("1"))
					str1.append("0");
				else{
					str1.append("1");
					temp=i;
					break;
				}
			}
			for(int i=temp-1;i>1;i--)
				str1.append(s.substring(i-1,i));
			str1.append("1");
			s=str1.toString();
			for(int i=s.length();i>0;i--)
				str2.append(s.substring(i-1, i));
			s=str2.toString();
		}
		else{
			int temp=0;
			for(int i=operand.length();i>1;i--){
				if(operand.substring(i-1,i).equals("0"))
					str1.append("1");
				else{
					str1.append("0");
					temp=i;
					break;
				}
			}
			for(int i=temp-1;i>1;i--)
				str1.append(operand.substring(i-1,i));
			str1.append("1");
			s=str1.toString();
			for(int i=s.length();i>0;i--)
				str2.append(s.substring(i-1, i));
			s=str2.toString();
			s=negation(s);
	}
		return s;
	}
	public String addZero(String operand){
		StringBuffer str=new StringBuffer();
		str.append("0");
		str.append(operand);
		return str.toString();
	}
	public String addFourZero(String operand){
		StringBuffer str=new StringBuffer();
		for(int i=0;i<4;i++)
		str.append("0");
		str.append(operand);
		return str.toString();
	}
	public String frameShift(String operand){
		int num=(int)Math.pow(2, operand.length()-1)-1;
		int temp=Integer.parseInt(integerTrueValue(operand))+num;
		String result=integerRepresentation(Integer.toString(temp),operand.length());
		return result;
	}
	public String counterFrameShift(String operand){
		int num=(int)Math.pow(2, operand.length()-1)-1;
		int temp=Integer.parseInt(integerTrueValue(operand))-num;
		String result=integerRepresentation(Integer.toString(temp),operand.length());
		return result;
	}
}
