import {Type} from "../workout-details-view/button-group/button-node";
import {SelectableElement} from "./selectable-element";

export class SelectableElementFactory {
  static from(name: string, nodeType: Type):SelectableElement {
    return {
      name: name,
      type: nodeType
    }
  }
}
