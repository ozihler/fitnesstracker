import {SetFormatPipe} from '../../../../app/workout/shared/pipes/set-format.pipe';
import {Set} from '../../../../app/workout/shared/set';

describe('SetFormatPipe', () => {
  let pipe: SetFormatPipe;

  beforeEach(() => {
    pipe = new SetFormatPipe();
  });

  it('correctly displays all values in order', () => {
    const set: Set = new Set(50, 'kg', 12, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual('50 kg, 12 #, 45 s (600 kg)');
  });

  it('should not display undefined weight values', () => {
    const set: Set = new Set(undefined, 'kg', 12, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual('12 #, 45 s');

    const set2: Set = new Set(undefined, undefined, 12, 45, 's', undefined);
    expect(pipe.transform(set2)).toEqual('12 #, 45 s');
  });


  it('should display waiting time only if nothing else is defined', () => {
    const set: Set = new Set(undefined, 'kg', undefined, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual('45 s');

    const set2: Set = new Set(undefined, undefined, undefined, 45, undefined, undefined);
    expect(pipe.transform(set2)).toEqual('45 s');
  });

  it('should not display undefined repetition values', () => {
    const set: Set = new Set(50, 'kg', undefined, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual('50 kg, 45 s');
  });

  it('should not display undefined repetition values', () => {
    const set: Set = new Set(50, 'kg', 12, 0, 's', undefined);
    expect(pipe.transform(set)).toEqual('50 kg, 12 # (600 kg)');
  });

  it('should not display undefined waiting time values', () => {
    const set: Set = new Set(undefined, 'kg', 12, undefined, 's', undefined);
    expect(pipe.transform(set)).toEqual('12 #');

    const set2: Set = new Set(undefined, undefined, 12, undefined, undefined, undefined);
    expect(pipe.transform(set2)).toEqual('12 #');
  });

  it('should use default values for undefined weight units', () => {
    const set: Set = new Set(50, undefined, 12, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual('50 kg, 12 #, 45 s (600 kg)');
  });

  it('should use default values for undefined waiting time units display', () => {
    const set: Set = new Set(50, 'kg', 12, 45, undefined, undefined);
    expect(pipe.transform(set)).toEqual('50 kg, 12 #, 45 s (600 kg)');
  });

  it('should not display a comma after weight if nothing else is defined', () => {
    const set: Set = new Set(50, 'kg', undefined, undefined, undefined, undefined);
    expect(pipe.transform(set)).toEqual('50 kg');

    const set2: Set = new Set(50, undefined, undefined, undefined, undefined, undefined);
    expect(pipe.transform(set2)).toEqual('50 kg');
  });

  it('should not display a comma after number of repetitions if nothing else is defined', () => {
    const set: Set = new Set(undefined, undefined, 12, undefined, undefined, undefined);
    expect(pipe.transform(set)).toEqual('12 #');
  });
});
