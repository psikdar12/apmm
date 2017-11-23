package com.example;

import com.example.exception.InvalidSalaryException;

public class PayrollService{

	public double hra(double salary) throws InvalidSalaryException{
		if(salary <= 0) {
			throw new InvalidSalaryException("Salary can not be zero or lesser!!!!!");
		}
		return salary*.60;
	}


	public double da(double salary) throws InvalidSalaryException{
		if(salary <= 0) {
			throw new InvalidSalaryException("Salary can not be zero or lesser!!!!!");
		}
		return salary*.10;
	}

}