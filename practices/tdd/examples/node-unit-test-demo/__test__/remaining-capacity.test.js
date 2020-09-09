const MutableInteger = require('../src/mutable-integer').default
const RemainingCapacity = require('../src/remaining-capacity').default

test('admit party calls MutableInteger subtract', () => {
    //arrange
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract};
    let tested = new RemainingCapacity(mockMutableInteger);
    let partySize = 12;

    //act
    tested.admitParty(partySize);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
});

test('admit party if allowed entry rejects parties beyond capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(0);

    //act
    let actual = tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(actual).toBe(false);
});

test('rejecting a party does not reduce capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(0);

    //act
    tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(0);
});

test('admit party if allowed entry accepts parties within capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(15);

    //act
    let actual = tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(actual).toBe(true);
});

test('accepting a party reduces capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(15);

    //act
    tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
    expect(mockSubtract.mock.calls[0][0]).toBe(12);
});

test('enforce capacity removed excess occupants (a)', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    mockGetCurrentValue.mockReturnValueOnce(-12).mockReturnValueOnce(-12);

    //act
    tested.enforceCapacity_a();

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
    expect(mockSubtract.mock.calls[0][0]).toBe(-12);
});

test('enforce capacity removed excess occupants (b)', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockAdd = jest.fn();
    let mockMutableInteger = {add: mockAdd, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    mockGetCurrentValue.mockReturnValueOnce(-12).mockReturnValueOnce(-12);

    //act
    tested.enforceCapacity_b();

    //assert
    expect(mockAdd.mock.calls.length).toBe(1);
    expect(mockAdd.mock.calls[0][0]).toBe(12);
});

test('enforce capacity removed excess occupants (either)', () => {
    //arrange
    tested = new RemainingCapacity(new MutableInteger(-12));

    //act
    tested.enforceCapacity_a();
    let actual = tested.getRemainingCapacity();

    //assert
    expect(actual).toBe(0);
});
