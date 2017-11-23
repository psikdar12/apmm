package com.example;

import org.junit.Assert;
import org.junit.Test;

import com.example.exception.InvalidSalaryException;

public class PayrollServiceTest{
	
	@Test
	public void daShouldBeTenPercentOfSalary(){

		PayrollService payrollService = new PayrollService();
		
		try {
			double rs = payrollService.da(10000.00);
			Assert.assertEquals(1000.00,rs,2);	
		} catch (InvalidSalaryException e) {
			e.printStackTrace();
		}
	
	}

	@Test(expected=InvalidSalaryException.class)
	public void daShouldProduceExceptionIfNegativeSalary() throws InvalidSalaryException{

		PayrollService payrollService = new PayrollService();
		payrollService.da(-10000.00);
	
	}



	@Test
	public void hraShouldBeSixtyPercentOfSalary(){

		PayrollService payrollService = new PayrollService();
		
		try {
			double rs = payrollService.hra(10000.00);
			Assert.assertEquals(6000.00,rs,2);	
		} catch (InvalidSalaryException e) {
			e.printStackTrace();
		}
	
	}

	@Test(expected=InvalidSalaryException.class)
	public void hraShouldProduceExceptionIfNegativeSalary() throws InvalidSalaryException{

		PayrollService payrollService = new PayrollService();
		payrollService.hra(-10000.00);
	
	}


}