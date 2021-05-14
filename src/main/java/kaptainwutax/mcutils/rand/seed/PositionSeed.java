package kaptainwutax.mcutils.rand.seed;

public class PositionSeed {
	@SuppressWarnings("IntegerMultiplicationImplicitCastToLong")
	public static long getPositionSeed(int x,int y,int z){
		long i = (long)(x * 3129871) ^ (long)y * 116129781L ^ (long)z;
		i = i * i * 42317861L + i * 11L;
		return i >> 16;
	}
}