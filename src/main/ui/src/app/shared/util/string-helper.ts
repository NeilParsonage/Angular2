export class StringHelper {
  static emptyStringIfNotSet(value: number): string {
    if (!value) {
      return '';
    }
    return String(value);
  }
}
