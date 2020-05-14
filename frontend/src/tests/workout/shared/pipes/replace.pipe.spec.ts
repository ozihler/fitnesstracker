import {ReplacePipe} from "../../../../app/workout/shared/pipes/replace.pipe";

describe('A replace pipe', () => {
   it("replaces _ with ' ' correctly", () => {
    let replaced = new ReplacePipe().transform("MUSCLE_GROUP", "_", " ");
    expect(replaced).toEqual("MUSCLE GROUP");
  });
});

