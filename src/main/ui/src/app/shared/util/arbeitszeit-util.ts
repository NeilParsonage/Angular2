import { Arbeitszeitmodell } from 'src/app/modules/fhi/models/arbeitszeitmodell';

export class ArbeitszeitUtil {
  private static AZM_TYP_TEMPORARY = 9;

  static isTemporary(azm: Arbeitszeitmodell): boolean {
    return azm && azm.typ == ArbeitszeitUtil.AZM_TYP_TEMPORARY;
  }
}
