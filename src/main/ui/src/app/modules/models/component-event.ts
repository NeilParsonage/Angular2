export class ComponentEvent {
  type: string;
  data: any;

  constructor(type: string, data?: any) {
    this.type = type;
    this.data = data;
  }
}
export const EVENT_COARSE_FILTER = 'EVENT_COARSE_FILTER';
export const EVENT_SHOW_DETAILS = 'EVENT_SHOW_DETAILS';
export const EVENT_FINE_FILTER = 'EVENT_FINE_FILTER';
export const EVENT_TYPE_CHANGE = 'EVENT_TYPE_CHANGE';

export const EVENT_BACK = 'EVENT_BACK';

export const EVENT_DELETE = 'EVENT_DELETE';

export const EVENT_EDIT = 'EVENT_EDIT';
export const EVENT_NEW = 'EVENT_NEW';
export const EVENT_SAVE = 'EVENT_SAVE';
export const EVENT_SAVE_ALL = 'EVENT_SAVE_ALL';

export const EVENT_PREVIEW = 'EVENT_PREVIEW';

export const EVENT_SAVE_SPEC = 'EVENT_SAVE_SPEC';
export const EVENT_SAVE_FILTER = 'EVENT_SAVE_FILTER';
