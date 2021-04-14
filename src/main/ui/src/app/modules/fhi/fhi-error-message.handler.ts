export interface ErrorMessageRule {
  find: string | string[];
  replace: string;
  url: string;
}

export class FhiErrorMessageHandler {

  public static process(response: any) {
    console.debug('FhiErrorMessageHandler process', response);
    if (response && response.error && typeof response.error.exception === 'string') {
      const technicalMsg: string = response.error.exception;
      const url: string = response.url;
      if (technicalMsg.length<1) {
        return null; // fallback
      }

      const foundUserMessage = FhiErrorMessageHandler.exceptionUserMessages().find( (element: ErrorMessageRule) =>
       FhiErrorMessageHandler.strIncludesAllCaseInsensitive(technicalMsg, element.find) && url.endsWith(element.url));
      if (foundUserMessage) {
        return foundUserMessage.replace;
      } else {
        if (response.error.error) {
          return response.error.error;
        }
      }
    }
    return null;
  }

  public static strIncludesAllCaseInsensitive(str: string, find: string | string[]) {
    if (!str || !find) {
      return false;
    }
    if (typeof find === 'string') {
      return str.toUpperCase().includes(find.toUpperCase());
    }
    if (!Array.isArray(find) || find.length < 1) {
      return false;
    }
    let allIncluded = true;
    find.forEach(searchText => {
      allIncluded = allIncluded && str.toUpperCase().includes(searchText.toUpperCase());
    });
    return allIncluded;
  }

  public static exceptionUserMessages(): ErrorMessageRule[] {
    return [
      {
        find: ['ORA-00001','UK_AZMBEZ'],
        replace: 'Ein Arbeitszeitmodell mit diesem Namen existiert bereits!',
        url: '/arbeitszeit/modell'
      }
    ];
  }

}
