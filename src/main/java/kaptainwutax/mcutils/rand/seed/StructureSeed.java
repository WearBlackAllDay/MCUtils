package kaptainwutax.mcutils.rand.seed;


import kaptainwutax.mcutils.util.data.SeedIterator;

import java.util.Arrays;

public final class StructureSeed {

	/**
	 * Converts a structure seed to a pillar seed.
	 *
	 * @see PillarSeed#fromStructureSeed(long)
	 */
	public static long toPillarSeed(long structureSeed) {
		return PillarSeed.fromStructureSeed(structureSeed);
	}

	/**
	 * Appends the upper bits to the structure seed to create a world seed.
	 */
	public static long toWorldSeed(long structureSeed, long upperBits) {
		return upperBits << 48 | WorldSeed.toStructureSeed(structureSeed);
	}

	/**
	 * Returns the possible values the upper bits can take assuming the world seed
	 * was generated from a {@code nextLong()} call or, in other words, generated
	 * randomly after leaving the seed field empty.
	 */
	public static long[] toRandomWorldSeeds(long structureSeed) {
		//cant be more than 2
		long[] results = new long[2];
		int i = 0;

		//TODO still bruteforce.
		for(long upperBits = 0L; upperBits < 1L << 16; upperBits++) {
			if(WorldSeed.isRandom(toWorldSeed(structureSeed, upperBits))) {
				results[i++] = toWorldSeed(structureSeed, upperBits);
			}
		}
		return Arrays.copyOfRange(results, 0, i);
	}

	/**
	 * Returns an iterator generating all possible world seeds from a given structure
	 * seed by going through all possible upper 16 bits.
	 */
	public static SeedIterator getWorldSeeds(long structureSeed) {
		return new SeedIterator(0L, 1L << 16, upperBits -> toWorldSeed(structureSeed, upperBits));
	}

	/**
	 * Returns an iterator generating all possible structure seeds. Note that you should
	 * not expect this to terminate in a reasonable amount of times since the amount of values
	 * it can take is 2^48 (around 281 trillion).
	 */
	public static SeedIterator iterator() {
		return new SeedIterator(0L, 1L << 48);
	}

}
