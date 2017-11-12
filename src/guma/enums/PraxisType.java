package guma.enums;

import java.util.HashMap;
import java.util.Map;

import guma.arithmetic.Afairesis;
import guma.arithmetic.Diairesis;
import guma.arithmetic.Multiplication;
import guma.arithmetic.Praxis;
import guma.arithmetic.Prosthesis;

/**
 * 
 * @author tsofras
 *
 */
public enum PraxisType {
	
	

	ADDING{
		@Override
		public Praxis getPraxis(int telestis1,int telestis2) {
			return new Prosthesis(telestis1,telestis2);	
		}
		@Override
		public Praxis getPraxis(int maxNum) {
			return new Prosthesis(maxNum);	
		}
	},
	SUBSTRACTION{
		@Override
		public Praxis getPraxis(int telestis1,int telestis2) {
			return new Afairesis(telestis1,telestis2);	
		}
		@Override
		public Praxis getPraxis(int maxNum) {
			return new Afairesis(maxNum);	
		}
	},
	DIVISION{
		@Override
		public Praxis getPraxis(int telestis1,int telestis2) {
			return new Diairesis(telestis1,telestis2);	
		}
		@Override
		public Praxis getPraxis(int maxNum) {
			return new Diairesis(maxNum);	
		}
	},
	MULTIPLICATION{
		@Override
		public Praxis getPraxis(int telestis1,int telestis2) {
			return new Multiplication(telestis1,telestis2);	
		}
		@Override
		public Praxis getPraxis(int maxNum) {
			return new Multiplication(maxNum);	
		}
	};

	private PraxisType() {

	}
	
	
	private Praxis praxis;


	public Praxis getPraxis(int telestis1,int telestis2){
		return this.praxis;
	}
	public Praxis getPraxis(int maxNum){
		return this.praxis;
	}
	

}
