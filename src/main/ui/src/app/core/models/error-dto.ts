export interface ErrorDto {
  error: string;
  message?: string;
  status?: number;
  timestamp?: Date;
  isDinaError?: boolean;
}
