export interface Color {
  hex: string,
  rgb?: string,
  class?: string,
}

export class Colors {
  static transparent: Color ={ hex: 'transparent' };
  static Secondary_Deep: Color = { hex: '#003340' };
  static Secondary_Goose: Color = { hex: '#004355' };
  static Secondary_Nordic: Color = { hex: '#00566A' };
  static Secondary_Calypso: Color = { hex: '#00677F' };
  static Secondary_Racing: Color = { hex: '#007A93' };
  static Secondary_Azure: Color = { hex: '#5097AB' };
  static Secondary_Glacier: Color = { hex: '#79AEBF' };
  static Secondary_Sky: Color = { hex: '#79AEBF' };
  static Info_Offwhite: Color = { hex: '#DDD9D5' };
  static Visu_Pistachio: Color = { hex: '#826C39' };
  static Visu_Limepastel: Color = { hex: '#AD9252' };
  static Visu_Sand: Color = { hex: '#D3B264' };
  static Visu_Champange: Color = { hex: '#EAD8B2' };
}
