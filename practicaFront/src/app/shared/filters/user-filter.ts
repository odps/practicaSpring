export interface UserFilter {
  firstName: {
    contains: "likeName",
    equals: "hasName"
  },
  lastName: {
    contains: "likeLastName",
    equals: "hasLastName"
  },
  email: {
    contains: "likeEmail",
    equals: "hasEmail"
  },
  createdAt: {
    dateAfter: "createdAfter",
    dateBefore: "createdBefore"
  }
}
