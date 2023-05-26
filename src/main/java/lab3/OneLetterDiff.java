// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

/**
 * Returns true only if both words have the same length and differ in one
 * character.
 * (Does not need to handle variable length characters.)
 */
public class OneLetterDiff implements WordCriteria {

  @Override
  public boolean adjacent(String word1, String word2) {
    int wordSize1 = word1.length();
    int wordSize2 = word2.length();

    int same = 0;

    if (wordSize1 != wordSize2) return false;

    for (int i = 0; i < wordSize1; i++) {
      if (word1.charAt(i) == word2.charAt(i)) same++;
    }

    if (same == (wordSize1 - 1)) return true; else return false;
  }
}
