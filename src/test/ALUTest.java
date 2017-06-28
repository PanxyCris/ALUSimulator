package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.ALU;

public class ALUTest {

	//integerRepresentation
	@Test
	public void test1() {
		ALU alu=new ALU();
		assertEquals("00001001",alu.integerRepresentation("9",8));
	}
	@Test
	public void test2() {
		ALU alu=new ALU();
		assertEquals("11111011",alu.integerRepresentation("-5",8));
	}
	@Test
	public void test3() {
		ALU alu=new ALU();
		assertEquals("1111111111111110",alu.integerRepresentation("65534",16));
	}
	@Test
	public void test4() {
		ALU alu=new ALU();
		assertEquals("1011111111111100",alu.integerRepresentation("-16388",16));
	}
	
	@Test
	public void test5() {
		ALU alu=new ALU();
		assertEquals("00000000",alu.integerRepresentation("0",8));
	}
	@Test
	public void test6() {
		ALU alu=new ALU();
		assertEquals("0000000000000000000000000001001",alu.integerRepresentation("9",31));
	}
	@Test
	public void test7() {
		ALU alu=new ALU();
		assertEquals("11111111111111111111111111110001",alu.integerRepresentation("-15",32));
	}
	@Test
	public void test8() {
		ALU alu=new ALU();
		assertEquals("1001",alu.integerRepresentation("9",4));
	}
	@Test
	public void test9() {
		ALU alu=new ALU();
		assertEquals("1001",alu.integerRepresentation("-7",4));
	}
	@Test
	public void test10() {
		ALU alu=new ALU();
		assertEquals("00100111",alu.integerRepresentation("39",8));
	}
	
//floatRepresentation
	@Test
	public void test11(){
		ALU alu=new ALU();
		assertEquals("01000001001101100000",alu.floatRepresentation("11.375", 8, 11));
	}
	@Test
	public void test12(){
		ALU alu=new ALU();
		assertEquals("110011101000",alu.floatRepresentation("-7.25", 4, 7));
	}
	@Test
	public void test13(){
		ALU alu=new ALU();
		assertEquals("0000000000",alu.floatRepresentation("0", 5, 4));
	}
	@Test
	public void test14(){
		ALU alu=new ALU();
		assertEquals("1000000000000",alu.floatRepresentation("-0",6, 6));
	}
	@Test
	public void test15(){
		ALU alu=new ALU();
		assertEquals("0111111111111111000000",alu.floatRepresentation("+Inf", 15, 6));
	}
	@Test
	public void test16(){
		ALU alu=new ALU();
		assertEquals("11111111111000000000",alu.floatRepresentation("-Inf", 10, 9));
	}
	@Test
	public void test17(){
		ALU alu=new ALU();
		assertEquals("11111111111",alu.floatRepresentation("NaN", 10, 100));
	}
	@Test
	public void test18(){
		ALU alu=new ALU();
		assertEquals("000000000",alu.floatRepresentation("5.8774717541114375E-39", 5, 3));
	}
	@Test
	public void test19(){
		ALU alu=new ALU();
		assertEquals("1000000000",alu.floatRepresentation("-2.9387358770557188E-39", 4, 5));
	}
	@Test
	public void test20(){
		ALU alu=new ALU();
		assertEquals("1100000010100110011",alu.floatRepresentation("-5.2", 8, 10));
	}
	@Test
	public void test21(){
		ALU alu=new ALU();
		assertEquals("01000001001101100000000000000000",alu.ieee754("11.375", 32));
	}
	@Test
	public void test22(){
		ALU alu=new ALU();
		assertEquals("11000000111010000000000000000000",alu.ieee754("-7.25", 32));
	}
	@Test
	public void test23(){
		ALU alu=new ALU();
		assertEquals("00000000000000000000000000000000",alu.ieee754("0", 32));
	}
	@Test
	public void test24(){
		ALU alu=new ALU();
		assertEquals("10000000000000000000000000000000",alu.ieee754("-0", 32));
	}
	@Test
	public void test25(){
		ALU alu=new ALU();
		assertEquals("11000000101001100110011001100110",alu.ieee754("-5.2", 32));
	}
	@Test
	public void test26(){
		ALU alu=new ALU();
		assertEquals("0100000010000011100011010000000000000000000000000000000000000000",alu.ieee754("625.625", 64));
	}@Test
	public void test27(){
		ALU alu=new ALU();
		assertEquals("1100000010010100100010100001010001111010111000010100011110101110",alu.ieee754("-1314.52", 64));
	}
	@Test
	public void test28(){
		ALU alu=new ALU();
		assertEquals("0100000011101000010101110000101000000000000000000000000000000000",alu.ieee754("49848.3125", 64));
	}
	@Test
	public void test29(){
		ALU alu=new ALU();
		assertEquals("1100000010110111000010000001000000000000000000000000000000000000",alu.ieee754("-5896.0625",64));
	}
	@Test
	public void test30(){
		ALU alu=new ALU();
		assertEquals("0100000100100001111111010010010010000000000000000000000000000000",alu.ieee754("589458.25",64));
	}
	@Test
	public void test31(){
		ALU alu=new ALU();
		assertEquals("9",alu.integerTrueValue("00001001"));
	}
	@Test
	public void test32(){
		ALU alu=new ALU();
		assertEquals("-3",alu.integerTrueValue("1101"));
	}
	@Test
	public void test33(){
		ALU alu=new ALU();
		assertEquals("-22",alu.integerTrueValue("1101010"));
	}
	@Test
	public void test34(){
		ALU alu=new ALU();
		assertEquals("5",alu.integerTrueValue("00000101"));
	}
	@Test
	public void test35(){
		ALU alu=new ALU();
		assertEquals("0",alu.integerTrueValue("00000000"));
	}
	@Test
	public void test36(){
		ALU alu=new ALU();
		assertEquals("-1",alu.integerTrueValue("1111111"));
	}
	@Test
	public void test37(){
		ALU alu=new ALU();
		assertEquals("169",alu.integerTrueValue("00010101001"));
	}
	@Test
	public void test38(){
		ALU alu=new ALU();
		assertEquals("-233",alu.integerTrueValue("100010111"));
	}
	@Test
	public void test39(){
		ALU alu=new ALU();
		assertEquals("-32",alu.integerTrueValue("100000"));
	}
	@Test
	public void test40(){
		ALU alu=new ALU();
		assertEquals("61",alu.integerTrueValue("00111101"));
	}
	@Test
	public void test41(){
		ALU alu=new ALU();
		assertEquals("11.375",alu.floatTrueValue("01000001001101100000",8 ,11 ));
	}
	@Test
	public void test42(){
		ALU alu=new ALU();
		assertEquals("-7.25",alu.floatTrueValue("110011101000",4,7 ));
	}
	@Test
	public void test43(){
		ALU alu=new ALU();
		assertEquals("0.0",alu.floatTrueValue("0000000000",5 ,4 ));
	}
	@Test
	public void test44(){
		ALU alu=new ALU();
		assertEquals("-0",alu.floatTrueValue("1000000000000",6,6 ));
	}
	@Test
	public void test45(){
		ALU alu=new ALU();
		assertEquals("+Inf",alu.floatTrueValue("0111111111111111000000",15,6 ));
	}
	@Test
	public void test46(){
		ALU alu=new ALU();
		assertEquals("NaN",alu.floatTrueValue("11111111111000000000",10 ,9 ));
	}
	@Test
	public void test47(){
		ALU alu=new ALU();
		assertEquals("NaN",alu.floatTrueValue("11111111111",10 ,100 ));
	}
	@Test
	public void test48(){
		ALU alu=new ALU();
		assertEquals("34.25",alu.floatTrueValue("0100001000001001",8,7 ));
	}
	@Test
	public void test49(){
		ALU alu=new ALU();
		assertEquals("-5.5",alu.floatTrueValue("11001011000",4,6 ));
	}
	@Test
	public void test50(){
		ALU alu=new ALU();
		assertEquals("9.1875",alu.floatTrueValue("01001000100110",5,8));
	}
    @Test
    public void test51(){
    	ALU alu=new ALU();
    	assertEquals("11110110",alu.negation("00001001"));
    }
    @Test
    public void test52(){
    	ALU alu=new ALU();
    	assertEquals("0110",alu.negation("1001"));
    }
    @Test
    public void test53(){
    	ALU alu=new ALU();
    	assertEquals("11111",alu.negation("00000"));
    }
    @Test
    public void test54(){
    	ALU alu=new ALU();
    	assertEquals("010100",alu.negation("101011"));
    }
    
    @Test
    public void test55(){
    	ALU alu=new ALU();
    	assertEquals("000000",alu.negation("111111"));
    }
    @Test
    public void test56(){
    	ALU alu=new ALU();
    	assertEquals("01100110",alu.negation("10011001"));
    }
    @Test
    public void test57(){
    	ALU alu=new ALU();
    	assertEquals("00101110",alu.negation("11010001"));
    }
    @Test
    public void test58(){
    	ALU alu=new ALU();
    	assertEquals("11110001",alu.negation("00001110"));
    }
    @Test
    public void test59(){
    	ALU alu=new ALU();
    	assertEquals("10",alu.negation("01"));
    }
    @Test
    public void test60(){
    	ALU alu=new ALU();
    	assertEquals("1",alu.negation("0"));
    }
    @Test
    public void test61(){
    	ALU alu=new ALU();
    	assertEquals("00100100",alu.leftShift("00001001", 2));
    }
    @Test
    public void test62(){
    	ALU alu=new ALU();
    	assertEquals("10100000",alu.leftShift("00010101",5));
    }
    @Test
    public void test63(){
    	ALU alu=new ALU();
    	assertEquals("11001000000",alu.leftShift("00011111001",6));
    }
    @Test
    public void test64(){
    	ALU alu=new ALU();
    	assertEquals("0001000",alu.leftShift("1110001",3));
    }
    @Test
    public void test65(){
    	ALU alu=new ALU();
    	assertEquals("10001100",alu.leftShift("00100011",2));
    }
    @Test
    public void test66(){
    	ALU alu=new ALU();
    	assertEquals("0",alu.leftShift("1",1));
    }
    @Test
    public void test67(){
    	ALU alu=new ALU();
    	assertEquals("011110000",alu.leftShift("001001111",4));
    }
    @Test
    public void test68(){
    	ALU alu=new ALU();
    	assertEquals("0110000",alu.leftShift("1100110",3));
    }
    @Test
    public void test69(){
    	ALU alu=new ALU();
    	assertEquals("0",alu.leftShift("0",1));
    }
    @Test
    public void test70(){
    	ALU alu=new ALU();
    	assertEquals("00",alu.leftShift("10",2));
    }
    @Test
    public void test71(){
    	ALU alu=new ALU();
    	assertEquals("00111101",alu.logRightShift("11110110", 2));
    }
    @Test
    public void test72(){
    	ALU alu=new ALU();
    	assertEquals("0000011",alu.logRightShift("1100010", 5));
    }
    @Test
    public void test73(){
    	ALU alu=new ALU();
    	assertEquals("0001000",alu.logRightShift("1000101", 3));
    }
    @Test
    public void test74(){
    	ALU alu=new ALU();
    	assertEquals("000000111",alu.logRightShift("111101001", 6));
    }
    @Test
    public void test75(){
    	ALU alu=new ALU();
    	assertEquals("0000100",alu.logRightShift("0010001",2));
    }
    @Test
    public void test76(){
    	ALU alu=new ALU();
    	assertEquals("0",alu.logRightShift("1", 1));
    }
    @Test
    public void test77(){
    	ALU alu=new ALU();
    	assertEquals("00000010",alu.logRightShift("00010001", 3));
    }
    @Test
    public void test78(){
    	ALU alu=new ALU();
    	assertEquals("00111000",alu.logRightShift("11100011", 2));
    }
    @Test
    public void test79(){
    	ALU alu=new ALU();
    	assertEquals("0000110",alu.logRightShift("1100101", 4));
    }
    @Test
    public void test80(){
    	ALU alu=new ALU();
    	assertEquals("00",alu.logRightShift("10", 2));
    }
    @Test
    public void test81(){
    	ALU alu=new ALU();
    	assertEquals("11111101",alu.ariRightShift("11110110", 2));
    }
    @Test
    public void test82(){
    	ALU alu=new ALU();
    	assertEquals("1111111",alu.ariRightShift("1100010", 5));
    }
    @Test
    public void test83(){
    	ALU alu=new ALU();
    	assertEquals("1111000",alu.ariRightShift("1000101", 3));
    }
    @Test
    public void test84(){
    	ALU alu=new ALU();
    	assertEquals("111111111",alu.ariRightShift("111101001", 6));
    }
    @Test
    public void test85(){
    	ALU alu=new ALU();
    	assertEquals("0000100",alu.ariRightShift("0010001",2));
    }
    @Test
    public void test86(){
    	ALU alu=new ALU();
    	assertEquals("1",alu.ariRightShift("1", 1));
    }
    @Test
    public void test87(){
    	ALU alu=new ALU();
    	assertEquals("00000010",alu.ariRightShift("00010001", 3));
    }
    @Test
    public void test88(){
    	ALU alu=new ALU();
    	assertEquals("11111000",alu.ariRightShift("11100011", 2));
    }
    @Test
    public void test89(){
    	ALU alu=new ALU();
    	assertEquals("1111110",alu.ariRightShift("1100101", 4));
    }
    @Test
    public void test90(){
    	ALU alu=new ALU();
    	assertEquals("11",alu.ariRightShift("10", 2));
    }
    @Test
    public void test91(){
    	ALU alu=new ALU();
    	assertEquals("10",alu.fullAdder('1', '1', '0'));
    }
    @Test
    public void test92(){
    	ALU alu=new ALU();
    	assertEquals("00",alu.fullAdder('0', '0', '0'));
    }
    @Test
    public void test93(){
    	ALU alu=new ALU();
    	assertEquals("01",alu.fullAdder('0', '0', '1'));
    }
    @Test
    public void test94(){
    	ALU alu=new ALU();
    	assertEquals("01",alu.fullAdder('0', '1', '0'));
    }
    @Test
    public void test95(){
    	ALU alu=new ALU();
    	assertEquals("10",alu.fullAdder('0', '1', '1'));
    }
    @Test
    public void test96(){
    	ALU alu=new ALU();
    	assertEquals("01",alu.fullAdder('1', '0', '0'));
    }
    @Test
    public void test97(){
    	ALU alu=new ALU();
    	assertEquals("10",alu.fullAdder('1', '0', '1'));
    }
    @Test
    public void test98(){
    	ALU alu=new ALU();
    	assertEquals("10",alu.fullAdder('1', '1', '0'));
    }
    @Test
    public void test99(){
    	ALU alu=new ALU();
    	assertEquals("11",alu.fullAdder('1', '1', '1'));
    }
    @Test
    public void test100(){
    	ALU alu=new ALU();
    	assertEquals("00",alu.fullAdder('0', '0', '0'));
    }
    @Test
    public void test101(){
    	ALU alu=new ALU();
    	assertEquals("01011",alu.claAdder("1001", "0001", '1'));
    }
    @Test
    public void test102(){
    	ALU alu=new ALU();
    	assertEquals("10011",alu.claAdder("1001","1001",'1'));
    }
    @Test
    public void test103(){
    	ALU alu=new ALU();
    	assertEquals("10110",alu.claAdder("1101", "1001", '0'));
    }
    @Test
    public void test104(){
    	ALU alu=new ALU();
    	assertEquals("10001",alu.claAdder("1110", "0010", '1'));
    }
    @Test
    public void test105(){
    	ALU alu=new ALU();
    	assertEquals("11000",alu.claAdder("1100", "1100", '0'));
    }
    @Test
    public void test106(){
    	ALU alu=new ALU();
    	assertEquals("01110",alu.claAdder("0111", "0110", '1'));
    }
    @Test
    public void test107(){
    	ALU alu=new ALU();
    	assertEquals("00000",alu.claAdder("0000", "0000", '0'));
    }
    @Test
    public void test108(){
    	ALU alu=new ALU();
    	assertEquals("10011",alu.claAdder("1111", "0011", '1'));
    }
    @Test
    public void test109(){
    	ALU alu=new ALU();
    	assertEquals("11100",alu.claAdder("1110", "1110", '0'));
    }
    @Test
    public void test110(){
    	ALU alu=new ALU();
    	assertEquals("11111",alu.claAdder("1111", "1111", '1'));
    }
    @Test
    public void test111(){
    	ALU alu=new ALU();
    	assertEquals("000001010",alu.oneAdder("00001001"));
    }
    @Test
    public void test112(){
    	ALU alu=new ALU();
    	assertEquals("000011",alu.oneAdder("00010"));
    }
    @Test
    public void test113(){
    	ALU alu=new ALU();
    	assertEquals("01101",alu.oneAdder("1100"));
    }
    @Test
    public void test114(){
    	ALU alu=new ALU();
    	assertEquals("00000",alu.oneAdder("1111"));
    }
    @Test
    public void test115(){
    	ALU alu=new ALU();
    	assertEquals("000110",alu.oneAdder("00101"));
    }
    @Test
    public void test116(){
    	ALU alu=new ALU();
    	assertEquals("00100",alu.oneAdder("0011"));
    }
    @Test
    public void test117(){
    	ALU alu=new ALU();
    	assertEquals("01110",alu.oneAdder("1101"));
    }
    @Test
    public void test118(){
    	ALU alu=new ALU();
    	assertEquals("11000",alu.oneAdder("0111"));
    }
    @Test
    public void test119(){
    	ALU alu=new ALU();
    	assertEquals("000000",alu.oneAdder("11111"));
    }
    @Test
    public void test120(){
    	ALU alu=new ALU();
    	assertEquals("1100000",alu.oneAdder("011111"));
    }
    @Test
    public void test121(){
    	ALU alu=new ALU();
    	assertEquals("000000111",alu.adder("0100", "0011", '0', 8));
    }
    @Test
    public void test122(){
    	ALU alu=new ALU();
    	assertEquals("00101",alu.adder("0011", "0010", '0', 4));
    }
    @Test
    public void test123(){
    	ALU alu=new ALU();
    	assertEquals("11001",alu.adder("0111", "0010", '0', 4));
    }
    @Test
    public void test124(){
    	ALU alu=new ALU();
    	assertEquals("000110111",alu.adder("011011", "011011", '1', 8));
    }
    @Test
    public void test125(){
    	ALU alu=new ALU();
    	assertEquals("11110",alu.adder("0110", "0111", '1', 4));
    }
    @Test
    public void test126(){
    	ALU alu=new ALU();
    	assertEquals("01011",alu.adder("1100", "1110", '1', 4));
    }
    @Test
    public void test127(){
    	ALU alu=new ALU();
    	assertEquals("011110000",alu.adder("1010000", "0011111", '1', 8));
    }
    @Test
    public void test128(){
    	ALU alu=new ALU();
    	assertEquals("010110100",alu.adder("11000011", "11110000", '1', 8));
    }
    @Test
    public void test129(){
    	ALU alu=new ALU();
    	assertEquals("0111111011001",alu.adder("0001110", "1001011", '0', 12));
    }
    @Test
    public void test130(){
    	ALU alu=new ALU();
    	assertEquals("0111000000001",alu.adder("111100000000", "111100000000", '1', 12));
    }
    @Test
    public void test131(){
    	ALU alu=new ALU();
    	assertEquals("000000111",alu.integerAddition("0100", "0011", 8));
    }
    @Test
    public void test132(){
    	ALU alu=new ALU();
    	assertEquals("01111",alu.integerAddition("1100", "0011", 4));
    }
    @Test
    public void test133(){
    	ALU alu=new ALU();
    	assertEquals("11110",alu.integerAddition("0111", "0111", 4));
    }
    @Test
    public void test134(){
    	ALU alu=new ALU();
    	assertEquals("11000",alu.integerAddition("0011", "0101", 4));
    }
    @Test
    public void test135(){
    	ALU alu=new ALU();
    	assertEquals("00000",alu.integerAddition("0000", "0000", 4));
    }
    @Test
    public void test136(){
    	ALU alu=new ALU();
    	assertEquals("011111110",alu.integerAddition("111101", "000001",8));
    }
    @Test
    public void test137(){
    	ALU alu=new ALU();
    	assertEquals("011110000",alu.integerAddition("11111000", "11111000", 8));
    }
    @Test
    public void test138(){
    	ALU alu=new ALU();
    	assertEquals("001010111",alu.integerAddition("00011110", "00111001", 8));
    }
    @Test
    public void test139(){
    	ALU alu=new ALU();
    	assertEquals("000000000",alu.integerAddition("00000001", "11111111", 8));
    }
    @Test
    public void test140(){
    	ALU alu=new ALU();
    	assertEquals("0111111111110",alu.integerAddition("111111111111", "111111111111", 12));
    }
    @Test
    public void test141(){
    	ALU alu=new ALU();
    	assertEquals("000000001",alu.integerSubtraction("0100", "0011", 8));
    }
    @Test
    public void test142(){
    	ALU alu=new ALU();
    	assertEquals("01111",alu.integerSubtraction("0011", "0100", 4));
    }
    @Test
    public void test143(){
    	ALU alu=new ALU();
    	assertEquals("00101",alu.integerSubtraction("0001", "1100",4));
    }
    @Test
    public void test144(){
    	ALU alu=new ALU();
    	assertEquals("11001",alu.integerSubtraction("0111", "1110", 4));
    }
    @Test
    public void test145(){
    	ALU alu=new ALU();
    	assertEquals("00000",alu.integerSubtraction("0000", "0000", 4));
    }
    @Test
    public void test146(){
    	ALU alu=new ALU();
    	assertEquals("000000001",alu.integerSubtraction("0000100", "0000011", 8));
    }
    @Test
    public void test147(){
    	ALU alu=new ALU();
    	assertEquals("001111100",alu.integerSubtraction("01111111", "00000011", 8));
    }
    @Test
    public void test148(){
    	ALU alu=new ALU();
    	assertEquals("01001",alu.integerSubtraction("1100", "0011",4));
    }
    @Test
    public void test149(){
    	ALU alu=new ALU();
    	assertEquals("011111001",alu.integerSubtraction("11111100", "00000011", 8));
    }
    @Test
    public void test150(){
    	ALU alu=new ALU();
    	assertEquals("0001111111101",alu.integerSubtraction("010000000000", "000000000011", 12));
    }
    @Test
    public void test151(){
    	ALU alu=new ALU();
    	assertEquals("000001100",alu.integerMultiplication("0100", "0011", 8));
    }
    @Test
    public void test152(){
    	ALU alu=new ALU();
    	assertEquals("11100",alu.integerMultiplication("0100", "0011", 4));
    }
    @Test
    public void test153(){
    	ALU alu=new ALU();
    	assertEquals("11110",alu.integerMultiplication("0111", "0010", 4));
    }
    @Test
    public void test154(){
    	ALU alu=new ALU();
    	assertEquals("10100",alu.integerMultiplication("0011", "1100", 4));
    }
    @Test
    public void test155(){
    	ALU alu=new ALU();
    	assertEquals("00000",alu.integerMultiplication("0000", "0000", 4));
    }
    @Test
    public void test156(){
    	ALU alu=new ALU();
    	assertEquals("011101000",alu.integerMultiplication("1100", "0110", 8));
    }
    @Test
    public void test157(){
    	ALU alu=new ALU();
    	assertEquals("011111000",alu.integerMultiplication("0100", "1110", 8));
    }
    @Test
    public void test158(){
    	ALU alu=new ALU();
    	assertEquals("110101000",alu.integerMultiplication("01000100", "00001010", 8));
    }
    @Test
    public void test159(){
    	ALU alu=new ALU();
    	assertEquals("000110001",alu.integerMultiplication("0111", "0111", 8));
    }
    @Test
    public void test160(){
    	ALU alu=new ALU();
    	assertEquals("0111111111010",alu.integerMultiplication("0011", "1110", 12));
    }
    @Test
    public void test161(){
    	ALU alu=new ALU();
    	assertEquals("000010001",alu.integerDivision("0100", "0011", 4));
    }
    @Test
    public void test162(){
    	ALU alu=new ALU();
    	assertEquals("000100000",alu.integerDivision("0110", "0011", 4));
    }
    @Test
    public void test163(){
    	ALU alu=new ALU();
    	assertEquals("011101111",alu.integerDivision("1001", "0011", 4));
    }
    @Test
    public void test164(){
    	ALU alu=new ALU();
    	assertEquals("100000000",alu.integerDivision("0000", "0011", 4));
    }
    @Test
    public void test165(){
    	ALU alu=new ALU();
    	assertEquals("100011111",alu.integerDivision("1100", "1101", 4));
    }
    @Test
    public void test166(){
    	ALU alu=new ALU();
    	assertEquals("000100001",alu.integerDivision("0101", "0010", 4));
    }
    @Test
    public void test167(){
    	ALU alu=new ALU();
    	assertEquals("011000001",alu.integerDivision("1001", "0010", 4));
    }
    @Test
    public void test168(){
    	ALU alu=new ALU();
    	assertEquals("011100001",alu.integerDivision("1101", "0010", 4));
    }
    @Test
    public void test169(){
    	ALU alu=new ALU();
    	assertEquals("000100001",alu.integerDivision("0111", "0011",4));
    }
    @Test
    public void test170(){
    	ALU alu=new ALU();
    	assertEquals("01111100000000010",alu.integerDivision("11100010", "00000100", 8));
    }
    @Test
    public void test171(){
    	ALU alu=new ALU();
    	assertEquals("0100000111",alu.signedAddition("1100", "1011", 8));
    }
    @Test
    public void test172(){
    	ALU alu=new ALU();
    	assertEquals("010001",alu.signedAddition("11000", "00111", 4));
    }
    @Test
    public void test173(){
    	ALU alu=new ALU();
    	assertEquals("111100",alu.signedAddition("1110", "1110", 4));
    }
    @Test
    public void test174(){
    	ALU alu=new ALU();
    	assertEquals("010010",alu.signedAddition("0001", "1111", 4));
    }
    @Test
    public void test175(){
    	ALU alu=new ALU();
    	assertEquals("000011",alu.signedAddition("0111", "1100", 4));
    }
    @Test
    public void test176(){
    	ALU alu=new ALU();
    	assertEquals("000000",alu.signedAddition("0000", "0000", 4));
    }
    @Test
    public void test177(){
    	ALU alu=new ALU();
    	assertEquals("111110",alu.signedAddition("1111", "1111", 4));
    }
    @Test
    public void test178(){
    	ALU alu=new ALU();
    	assertEquals("101110",alu.signedAddition("0111", "0111", 4));
    }
    @Test
    public void test179(){
    	ALU alu=new ALU();
    	assertEquals("101110",alu.signedAddition("01111", "01111", 4));
    }
    @Test
    public void test180(){
    	ALU alu=new ALU();
    	assertEquals("0010000000",alu.signedAddition("111111111", "000000001", 8));
    }
    @Test
    public void test181(){
    	ALU alu=new ALU();
    	assertEquals("000111111101110000",alu.floatAddition("00111111010100000", "00111111001000000",8,8,8));
    }
    @Test
    public void test182(){
    	ALU alu=new ALU();
    	assertEquals("0001110010",alu.floatAddition("001100001", "001100011",4,4,4));
    }
    @Test
    public void test183(){
    	ALU alu=new ALU();
    	assertEquals("0000000000",alu.floatAddition("000010100", "100010100",4,4,4));
    }
    @Test
    public void test184(){
    	ALU alu=new ALU();
    	assertEquals("0001000011",alu.floatAddition("000110011", "001000111",4,4,4));
    }
    @Test
    public void test185(){
    	ALU alu=new ALU();
    	assertEquals("0000100100",alu.floatAddition("100010100", "101100011",4,4,4));
    }
    @Test
    public void test186(){
    	ALU alu=new ALU();
    	assertEquals("0101011111",alu.floatAddition("101100010", "000110011",4,4,4));
    }
    @Test
    public void test187(){
    	ALU alu=new ALU();
    	assertEquals("0001110001",alu.floatAddition("001101001", "001111011",4,4,4));
    }
    @Test
    public void test188(){
    	ALU alu=new ALU();
    	assertEquals("0010000111",alu.floatAddition("101111111", "110000011",4,4,4));
    }
    @Test
    public void test189(){
    	ALU alu=new ALU();
    	assertEquals("0011010011",alu.floatAddition("011000011", "011000011",4,4,4));
    }
    @Test
    public void test190(){
    	ALU alu=new ALU();
    	assertEquals("001000010000001010",alu.floatAddition("01000001100000011", "01000001000001111",8,8,4));
    }
    @Test
    public void test191(){
    	ALU alu=new ALU();
    	assertEquals("000111110010000000",alu.floatSubtraction("00111111010100000", "00111111001000000",8, 8, 4));
    }
    @Test
    public void test192(){
    	ALU alu=new ALU();
    	assertEquals("0000110000",alu.floatSubtraction("001100011", "001100001", 4, 4, 4));
    }
    @Test
    public void test193(){
    	ALU alu=new ALU();
    	assertEquals("0011110000",alu.floatSubtraction("100110100", "100110011", 4, 4, 4));
    }
    @Test
    public void test194(){
    	ALU alu=new ALU();
    	assertEquals("0000110000",alu.floatSubtraction("001100111", "001100101", 4, 4, 4));
    }
    @Test
    public void test195(){
    	ALU alu=new ALU();
    	assertEquals("0001110100",alu.floatSubtraction("001100001", "101010111", 4, 4, 4));
    }
    @Test
    public void test196(){
    	ALU alu=new ALU();
    	assertEquals("0001010100",alu.floatSubtraction("101000100", "001010010", 4, 4, 4));
    }
    @Test
    public void test197(){
    	ALU alu=new ALU();
    	assertEquals("0001000011",alu.floatSubtraction("000110011", "100110011", 4, 4, 4));
    }
    @Test
    public void test198(){
    	ALU alu=new ALU();
    	assertEquals("0001000101",alu.floatSubtraction("000110101", "100100001", 4, 4, 4));
    }
    @Test
    public void test199(){
    	ALU alu=new ALU();
    	assertEquals("0100101110",alu.floatSubtraction("100110001", "100100100", 4, 4, 4));
    }
    @Test
    public void test200(){
    	ALU alu=new ALU();
    	assertEquals("000001011100000000",alu.floatSubtraction("00001111100010011", "00001111100010010", 8, 8, 8));
    }
   
}
