import java.math.BigDecimal;
import java.math.RoundingMode;

public class TheMath {
	
	BigDecimal number;
	
	TheMath(int otherCount, int checkedCount)
	{
		
		int totalNumber = otherCount + checkedCount;
    	System.out.println(checkedCount); 
    	System.out.println(totalNumber);
    	BigDecimal number1 = BigDecimal.valueOf(checkedCount); 
    	number1 = number1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    	BigDecimal number2 = BigDecimal.valueOf(totalNumber); 
    	number2 = number2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    	BigDecimal thisNumber = number1.divide(number2, RoundingMode.HALF_EVEN);
    	System.out.println(thisNumber);
    
    	number = thisNumber.scaleByPowerOfTen(2);
		
	}
	
	

}
