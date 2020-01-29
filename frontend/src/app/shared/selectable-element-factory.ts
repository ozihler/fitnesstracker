import {SelectableElement} from "./selectable-element";
import {Type} from "./type";

export class SelectableElementFactory {
  static from(name: string, nodeType: Type):SelectableElement {
    return {
      name: name,
      type: nodeType
    }
  }
}
