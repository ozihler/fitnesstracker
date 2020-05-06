import {SetFormatPipe} from './set-format.pipe';
import {SetFactory} from "../set.factory";
import {Set} from "../set";

describe('SetFormatPipe', () => {
  let pipe: SetFormatPipe;

  beforeEach(() => {
    pipe = new SetFormatPipe();
  });

  it('create an instance', () => {
    let set: Set = new Set(50, 'kg', 12, 45, 's', undefined);
    expect(pipe.transform(set)).toEqual("50 kg, 12 #, 45 s");
  });
});
