import {WorkoutId} from "./workout-id";
import {WorkoutEntryRaw} from "./workout-entry-raw";
import {WorkoutEntriesRaw} from "./workout-entries-raw";
import {WorkoutEntry} from "./workout-entry";

export class WorkoutEntryFactory {
  static fromRaw(workoutSimpleRaw: WorkoutEntryRaw): WorkoutEntry {
    return new WorkoutEntry(
      WorkoutId.from(workoutSimpleRaw.workoutId),
      workoutSimpleRaw.title,
      new Date(workoutSimpleRaw.creationDate)
    );
  }

  static fromMultiple(workoutsSimpleRaw: WorkoutEntriesRaw): WorkoutEntry[] {
    return workoutsSimpleRaw.workouts.map(w => WorkoutEntryFactory.fromRaw(w));
  }
}
