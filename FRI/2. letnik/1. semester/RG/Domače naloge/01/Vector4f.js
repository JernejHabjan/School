function Vector4f(x, y, z, w){

		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	
	this.negateV4f = function(v){
		return new Vector4f(-v.x, -v.y, -v.z, -v.w);
	}

	this.addV4f = function(v1, v2){
		return new Vector4f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w);
	}

	this.scalarV4f = function(scalar, v){
		return new Vector4f(scalar * v.x, scalar * v.y, scalar * v.z, scalar * v.w);
	}

	this.dotProductV4f = function(v1, v2){
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w;
	}

	this.crossProductV4f = function(v1,v2){
		return new Vector4f(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
	}

	this.lengthV4f = function(v){
		return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z + v.w * v.w);
	}

	this.normalizeV4f = function(v){
		return this.scalarV4f(1/this.lengthV4f(v),v);
	}

	this.absoluteV4f = function(v){
		return new Vector4f(Math.abs(v.x), Math.abs(v.y), Math.abs(v.z), Math.abs(v.w));
	}

	this.projectV4f = function(v1, v2){
		var scalar = this.dotProductV4f(v1, v2) / (this.dotProductV4f(this.absoluteV4f(v2), this.absoluteV4f(v2)));
		return this.scalarV4f(scalar, v2);
	}

	this.cosPhiV4f = function(v1, v2){
		return this.dotProductV4f(v1,v2) / (this.lengthV4f(v1) * this.lengthV4f(v2));
	}


}


