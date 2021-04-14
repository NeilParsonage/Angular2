export class KeypressFilterUtil {
  static acceptNumbersOnly($event: KeyboardEvent): boolean {
    let k = $event.charCode;  //         k = event.keyCode;  (Both can be used)
    const acceptBackspace: boolean = k == 8;
    const acceptNumber: boolean = (k >= 48 && k <= 57);
    return acceptBackspace
        || acceptNumber;
  }
}
