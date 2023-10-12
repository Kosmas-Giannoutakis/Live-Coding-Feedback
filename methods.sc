+ Object {

	stanh {
		arg steepness=1, range=1;
		var sig = this;
		var exponent = steepness*2*sig;
		var exp_expo = exp(exponent);
		var numerator = range*(exp_expo -1);
		var denominator = 1 + exp_expo;
		^numerator/denominator
	}

	logist {
		arg k=1, l=1;
		var denominator=(1+exp(k*this.neg));
		var result=l/denominator;
		^result
	}

}