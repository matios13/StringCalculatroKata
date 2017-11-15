package co.uk.eclispegroup.string.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnZeroForEmptyString() throws Exception {
        String isEmpty = "";
        int result = StringCalculator.add(isEmpty);
        assertThat(result, is(0));
    }

    @Test
    public void shouldReturnIntNumberForString100() throws Exception {
        String input = "100";
        int result = StringCalculator.add(input);
        assertThat(result, is(100));
    }

    @Test
    public void shouldReturnIntNumberForString345() throws Exception {
        String input = "345";
        int result = StringCalculator.add(input);
        assertThat(result, is(345));
    }

    @Test
    public void shouldReturnIntSumForTwoNumbersSeperatedByComa() throws Exception {
        String input = "3,5";
        int result = StringCalculator.add(input);
        assertThat(result, is(8));
    }

    @Test
    public void shouldReturnIntSumForMoreThanTwoNumbersSeparatedByComa() throws Exception {
        String input = "3,5,10";
        int result = StringCalculator.add(input);
        assertThat(result, is(18));
    }

    @Test
    public void shouldReturnIntSumForMoreThanTwoNumbersSeparatedByComaOrNewLine() throws Exception {
        String input = "3,5\n10";
        int result = StringCalculator.add(input);
        assertThat(result, is(18));
    }

    @Test
    public void shouldReturnIntSumForMoreThanTwoNumbersSeparatedByCustomDelimiters() throws Exception {
        String input = "//;\n3;5;12";
        int result = StringCalculator.add(input);
        assertThat(result, is(20));
    }

    @Test
    public void shouldThrowExceptionWhenNegativeNumbers() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("negatives not allowed: -123,-1");
        String input = "-123,5,-1";
        StringCalculator.add(input);
    }

    @Test
    public void shouldThrowExceptionWithDifferentNegativeNumbers() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("negatives not allowed: -120,-65");
        String input = "100,-120,-65,10";
        StringCalculator.add(input);
    }

    @Test
    public void shouldIgnoreNumbersGreaterThanThousand() throws Exception {
        String input = "1100,120,65,10";
        int result = StringCalculator.add(input);
        assertThat(result, is(195));
    }

    @Test
    public void shouldReturnIntSumForNumbersSeparatedByCustomLongDelimiters() throws Exception {
        String input = "//[aaa]\n3aaa5aaa12";
        int result = StringCalculator.add(input);
        assertThat(result, is(20));
    }

    @Test
    public void shouldReturnIntSumForNumbersSeparatedByMultipleCustomLongDelimiters() throws Exception {
        String input = "//[aaa][bb]\n3aaa5bb12";
        int result = StringCalculator.add(input);
        assertThat(result, is(20));
    }

    @Test
    public void name() throws Exception {
       //given

        //when

        //then

    }


}