+ Object {

	stanh {
		arg steepness = 1, range = 1;
		var sig = this;

		// Define limits for exponent to prevent overflow/underflow
		var max_exponent = 100;
		var min_exponent = -100;

		// Handle zero input (epsilon threshold)
		var epsilon = 1.0e-15;

		var is_small = abs(sig) < epsilon;
		var clamped_sig = is_small * 0 + (1 - is_small) * sig.clip(-1000, 1000);


		// Compute the exponent with clamping
		var exponent = (steepness * 2 * sig).clip(min_exponent, max_exponent);

		// Calculate the exponential
		var exp_expo = exp(exponent);

		// Compute the numerator and denominator
		var numerator = range * (exp_expo - 1);
		var denominator = exp_expo + 1;

		// Calculate the result
		var result = numerator / denominator;

		// Define output range limits if needed
		var output_range = 100;

		// Clamp the result to the desired output range
		result = result.clip(output_range.neg, output_range);

		^result
	}


	logist {
		arg k=1, l=1;
		var denominator=(1+exp(k*this.neg));
		var result=l/denominator;
		^result
	}

}