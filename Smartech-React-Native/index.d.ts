declare module "smartech-react-native" {
  export function setIdentity(identity: string): Promise<boolean>;

  export function clearIdentity(): Promise<boolean>;

  export function login(identity: string): Promise<boolean>;

  export function logout(): Promise<boolean>;

  export function optOut(flag: boolean): Promise<boolean>;

  export function profile(details: any): Promise<boolean>;

  export function track(eventName: string, eventValue: any): void;

  export function setUserLocation(longitude: number, latitude: number): void;

  export function setPushToken(token: string): Promise<boolean>;

  export function getPushToken(): Promise<string>;

  export function getGUID(): Promise<string>;

  export function getNotificationsiOS(value: any): void;

  export function getNotifications(notificationCount: number): Promise<any>;
}
