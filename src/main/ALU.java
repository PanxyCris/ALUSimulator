package main;

/**
 * 模拟ALU进行整数和浮点数的四则运算
 * @author [请将此处修改为“161250095_潘星宇”]
 *
 */
import java.util.ArrayList;
public class ALU {

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * @param number 十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
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
	 * 生成十进制浮点数的二进制表示。
	 * 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		int exp=(int)(Math.pow(2, eLength-1))-1;
		final double small=Math.pow(2, -exp+1);
		ArrayList<String> end=new ArrayList<>();
		
		double body=0.0;//表示整体
		int num=0;//表示整数部分
		double decimal=0.0;//表示小数部分

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
						int dLength=sLength-temp.length()+1;//记录小数部分的长度
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
						int dLength=sLength-temp.length()+1;//记录小数部分的长度
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
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int) floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * @param number 十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length 二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754 (String number, int length) {
		// TODO YOUR CODE HERE.
		if(length==32)
		return floatRepresentation(number, 8, length-9);
		else
			return floatRepresentation(number, 11, length-12);

	}
	
	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
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
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand 二进制表示的操作数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”， NaN表示为“NaN”
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE
		
		String s=null;
		int exp=0;//表示指数
		String expe=null;//表示指数字符串
		double temp=1.0;//表示小数
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
			  double mul=Math.pow(2, exp);//mul表示乘数
			  
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
			  double mul=Math.pow(2, exp);//mul表示乘数
			  
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
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * @param operand 二进制表示的操作数
	 * @return operand按位取反的结果
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
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 左移的位数
	 * @return operand左移n位的结果
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
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand逻辑右移n位的结果
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
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * @param operand 二进制表示的操作数
	 * @param n 右移的位数
	 * @return operand算术右移n位的结果
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
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * @param x 被加数的某一位，取0或1
	 * @param y 加数的某一位，取0或1
	 * @param c 低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder (char x, char y, char c) {
		// TODO YOUR CODE HERE.
		int m=0,n=0,p=0;//用于中转xyc
	
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
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * @param operand1 4位二进制表示的被加数
	 * @param operand2 4位二进制表示的加数
	 * @param c 低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
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
	 * 加一器，实现操作数加1的运算。
	 * 需要采用与门、或门、异或门等模拟，
	 * 不可以直接调用{@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * @param operand 二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
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
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param c 最低位进位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
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
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被加数
	 * @param operand2 二进制补码表示的加数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
	
		return adder(operand1,operand2,'0',length);
	}
	
	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被减数
	 * @param operand2 二进制补码表示的减数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.

		int temp=Integer.parseInt(integerTrueValue(operand2));
		
		operand2=integerRepresentation(Integer.toString(-temp),operand2.length());
		
		return adder(operand1,operand2,'0',length);
	}
	
	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int) adder}等方法。<br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被乘数
	 * @param operand2 二进制补码表示的乘数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
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
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * @param operand1 二进制补码表示的被除数
	 * @param operand2 二进制补码表示的除数
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，最后length位为余数
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
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int) integerAddition}、
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * @param operand1 二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2 二进制原码表示的加数，其中第1位为符号位
	 * @param length 存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，后length位是相加结果
	 */
	public String signedAddition (String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		boolean is1=false;//正数为真
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
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int) signedAddition}等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被加数
	 * @param operand2 二进制表示的加数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
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
		if(!(isPos1^isPos2)){//同号
			if(integerTrueValue(operand1.substring(1)).equals("0"))
			    result="0"+operand2;
			else if(integerTrueValue(operand2.substring(1)).equals("0"))
			    result="0"+operand1;
			else{//无0
			String expCompare=integerSubtraction(convert1,convert2,eLength);//判断指数大小
			if(integerTrueValue(expCompare).equals("0")){//指数相同
				str1.append("00");
				String exp=frameShift(oneAdder(convert1).substring(1));
				str1.append(exp);
				String addDec=signedAddition(addFourZero(operand1.substring(1+eLength)),addFourZero(operand2.substring(1+eLength)),sLength+4);
				if(addDec.substring(0,1).equals("1")){//小数溢出
					str1.append("1"+addDec.substring(6,addDec.length()-1));
				}
				else
					str1.append("0"+addDec.substring(6,addDec.length()-1));
				result=str1.toString();
			}
			else if(expCompare.substring(0,1).equals("0")){//1指数大
				
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
				if(addDec.substring(0,1).equals("1")){//小数溢出
					str1.append("1"+addDec.substring(7,addDec.length()-gLength));
				}
				else
					str1.append("0"+addDec.substring(7,addDec.length()-gLength));
				
				result=str1.toString();
			}
			else{//2指数大
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
					if(addDec.substring(0,1).equals("1")){//小数溢出
						str1.append("1"+addDec.substring(7,addDec.length()-gLength));
					}
					else
						str1.append("0"+addDec.substring(7,addDec.length()-gLength));
					
					result=str1.toString();
			   }
			}
		}
		else{//异号
			String expCompare=integerSubtraction(convert1,convert2,eLength);//判断指数大小
            if(integerTrueValue(expCompare).equals("0")){//指数相同
				String decCompare=integerSubtraction(addFourZero(operand1.substring(1+eLength)),addFourZero(operand2.substring(1+eLength)),4+sLength);
				if(integerTrueValue(decCompare).equals("0")){//尾数相同
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
			else if(expCompare.substring(1,2).equals("0")){//1指数大
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
			else{//2指数大
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
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int) floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 二进制表示的被减数
	 * @param operand2 二进制表示的减数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @param gLength 保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
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
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int) integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被乘数
	 * @param operand2 二进制表示的乘数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
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
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int) integerDivision}等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 二进制表示的被除数
	 * @param operand2 二进制表示的除数
	 * @param eLength 指数的长度，取值大于等于 4
	 * @param sLength 尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
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
