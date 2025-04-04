+ Object {

	stanh {
		arg steepness = 1, range = 1;
		var sig = this;
		var max_exponent = 100;
		var min_exponent = -100;
		var epsilon = 1.0e-15;
		var is_small, clamped_sig, exponent, exp_expo, numerator, denominator, result, output_range;

		// First, sanitize the input signal
		sig = sig.sanitize;

		// Pre-clip the input to reasonable range
		sig = sig.clip(-100, 100);

		// Sanitize steepness parameter as well
		steepness = steepness.sanitize.clip(0.001, 10);
		range = range.sanitize.clip(0.001, 100);

		// Handle zero input (epsilon threshold)
		is_small = abs(sig) < epsilon;
		clamped_sig = is_small * 0 + (1 - is_small) * sig;

		// Compute the exponent with clamping
		exponent = (steepness * 2 * clamped_sig).clip(min_exponent, max_exponent);

		// Calculate the exponential and sanitize immediately
		exp_expo = exp(exponent).sanitize;

		// Compute the numerator and denominator
		numerator = range * (exp_expo - 1);
		denominator = exp_expo + 1;

		// Ensure denominator is never zero
		denominator = denominator.max(1e-10);

		// Calculate the result and sanitize again
		result = (numerator / denominator).sanitize;

		// Define output range limits
		output_range = 100;

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