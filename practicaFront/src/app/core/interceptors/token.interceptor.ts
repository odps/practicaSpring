import {HttpInterceptorFn} from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  // console.log("=== Entrada a token interceptor");
  const token = localStorage.getItem("token");

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
  }

  return next(req);
};
