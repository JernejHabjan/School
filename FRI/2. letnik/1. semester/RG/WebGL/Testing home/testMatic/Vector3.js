function Vec3(x, y, z){
	this.x = x;
	this.y = y;
	this.z = z;

	this.length = function(){
		return Math.sqrt(
			this.x * this.x +
			this.y * this.y +
			this.z * this.z
		);
	}

	this.normalize = function(){
		len = this.length();
		this.x /= len;
		this.y /= len;
		this.z /= len;
	}
}