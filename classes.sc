Write {
	*ar {
		arg in, maxdelaytime=1;
		var numChannels=if(in.size==0, {1}, {in.size});
		var buf=numChannels.collect{LocalBuf(maxdelaytime*Server.default.sampleRate).clear};
		var wr=DelTapWr.ar(buf,in);
		^[buf, wr]
	}
}

FDLine {

	*ar {
		arg in, delTime=0, freq=200, rq=20, mul=1, add=0;
		var dline=DelTapRd.ar(in[0],in[1],delTime,4);
		var filtered=BPF.ar(dline, freq, rq);
		^(filtered*mul)+add
	}
}

Junction {
	*ar {
		arg in, steepness=1, range=1;
		var compressed=in.stanh(steepness, range);
		var cutDC=LeakDC.ar(compressed);
		^cutDC
	}
}