export class BooleanUtil {
  static getBoolean(booleanAsString: string): boolean {
    const value = booleanAsString?.toLowerCase() ?? 'false';
    switch (booleanAsString) {
      case 'true':
      case '1':
      case 'on':
      case 'yes':
        return true;
      default:
        return false;
    }
  }
}
