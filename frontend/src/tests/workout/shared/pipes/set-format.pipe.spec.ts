import {SetFormatPipe} from '../../../../app/workout/shared/pipes/set-format.pipe';
import {SetFactory} from "../../../../app/workout/shared/set.factory";
import {Set} from "../../../../app/workout/shared/set";

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
