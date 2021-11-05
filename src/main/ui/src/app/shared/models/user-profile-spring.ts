import { UserProfileElement } from './user-profile-element';

export interface UserProfileSpring {
  _embedded: {
    userProfiles: Array<UserProfileElement>;
  };
}
