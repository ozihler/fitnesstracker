import {ReplacePipe} from '../../../../app/workout/shared/pipes/replace.pipe';

describe('A replace pipe', () => {
  it('replaces _ with \' \' correctly', () => {
    const replaced = new ReplacePipe().transform('MUSCLE_GROUP', '_', ' ');
    expect(replaced).toEqual('MUSCLE GROUP');
  });

  it('can replace with empty strings', () => {
    const replaced = new ReplacePipe().transform('MUSCLE_GROUP', '_', '');
    expect(replaced).toEqual('MUSCLEGROUP');
  });
});

